package com.project.readers.readers_community.services;

import com.project.readers.readers_community.DTOs.*;
import com.project.readers.readers_community.entities.BookCopy;
import com.project.readers.readers_community.enums.BorrowRequestStatus;
import com.project.readers.readers_community.repositories.*;
import com.project.readers.readers_community.entities.BorrowRequest;
import com.project.readers.readers_community.entities.BookTransaction;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.project.readers.readers_community.entities.Book;
import com.project.readers.readers_community.entities.BookCategory;
import com.project.readers.readers_community.entities.User;
import com.project.readers.readers_community.enums.Approval;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class BookService {
    private final BookCopyRepository bookCopyRepository;
    private final BookRepository bookRepository;
    private final OtpService otpService;
    private final EmailService emailService;
    private final BookCategoryRepository bookCategoryRepository;
    private final BookTransactionRepository bookTransactionRepository;
    private final BorrowRequestRepository borrowRequestRepository;
    private final Mapper mapper;
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository, OtpService otpService, EmailService emailService, BookCopyRepository bookCopyRepository, BookTransactionRepository bookTransactionRepository, BorrowRequestRepository borrowRequestRepository, BookCategoryRepository bookCategoryRepository, Mapper mapper, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.otpService = otpService;
        this.emailService = emailService;
        this.bookCopyRepository = bookCopyRepository;
        this.bookTransactionRepository = bookTransactionRepository;
        this.borrowRequestRepository = borrowRequestRepository;
        this.bookCategoryRepository = bookCategoryRepository;
        this.mapper = mapper;
        this.userRepository = userRepository;
    }

    @Transactional
    public String uploadBook(Book book, User cureentUser) {

        book.setId(0L);
        book.setOwner(cureentUser);
        book.setAdminApproval(Approval.UNRESPONDED);
        book.setBookCopies(null);
        long bookcategory_id = book.getCategory().getId();

        Optional<BookCategory> bookCategoryOptional = bookCategoryRepository.findById(bookcategory_id);
        if(bookCategoryOptional.isEmpty())
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book category not found");
        }

        BookCategory bc = bookCategoryOptional.get();

        book.setCategory(bc);

        bookRepository.save(book);

        return "your book upload request is sent to admin";

    }

    @Transactional
    public List<BookDTO> getAllBooks() {
        return bookRepository.findByAdminApproval(Approval.APPROVED)
                .stream()
                .map(mapper::bookToBookDTO)
                .toList();
    }

    // method to get a book by id
    @Transactional
    public BookDTO getBook(long bookId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);

        if (bookOptional.isPresent()) {
            return mapper.bookToBookDTO(bookOptional.get());
        }

        // did not find the book
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
    }

    // method to get book copies of a book
    @Transactional
    public BookCopiesDTO getBookCopies(long bookId, User currentUser) {
        // first, will get the book
        Optional<Book> bookOptional = bookRepository.findById(bookId);

        if (bookOptional.isPresent() && bookOptional.get().getAdminApproval() == Approval.APPROVED) {

            // current user can only borrow if he/she has no current borrow request, need to take care of this while deciding requestable
            boolean canCurrentUserRequest = (currentUser.getCurrentBorrowRequest()==null);

            return mapper.bookToBookCopiesDTO(bookOptional.get(), canCurrentUserRequest);
        }
        // not found
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
    }

    // method to create a borrow request for a book copy
    @Transactional
    public String requestForBorrow(int bookCopyId, User user)
    {

        // checking if this is person eligible to request for a book
        boolean canRequest = true;
        String message = null;
        BorrowRequest currentBorrowRequest = user.getCurrentBorrowRequest();
        if(currentBorrowRequest!=null)
        {
            // if the user already requested any book
            if(currentBorrowRequest.getStatus()==BorrowRequestStatus.UNRESPONDED)
            {
                canRequest = false;
                message = "You have already requested for a book. Cannot request for multiple books";
            }

            // if the user is already about to borrow a book
            else if(currentBorrowRequest.getStatus()==BorrowRequestStatus.APPROVED)
            {
                canRequest = false;
                message = "One borrow request of yours is already accepted. Please borrow that book and read it";
            }

            // if the user already has a borrowed book
            else if(currentBorrowRequest.getStatus()==BorrowRequestStatus.RECEIVED)
            {
                canRequest = false;
                message = "You already have a book borrowed. Please return it to request for another";
            }
        }

        if(!canRequest)
        {
            // cannot request
            throw new ResponseStatusException(HttpStatus.CONFLICT, message);
        }

        Optional<BookCopy> bookCopyOptional = bookCopyRepository.findById(bookCopyId);

        if (bookCopyOptional.isEmpty()) {
            // no book copy found
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "BookCopy not found");
        }

        BookCopy bookCopy = bookCopyOptional.get();

        // checking if borrower is other than owner, i.e., is the book going to be passed on to someone already?
        if (!bookCopy.getBorrower().equals(bookCopy.getBook().getOwner())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Borrower already exists.");
        }

        BorrowRequest borrowRequest = new BorrowRequest();
        borrowRequest.setId(0);
        borrowRequest.setRequester(user);
        borrowRequest.setBookCopy(bookCopy);
        borrowRequest.setStatus(BorrowRequestStatus.UNRESPONDED);

        // save
        borrowRequest = borrowRequestRepository.save(borrowRequest);

        // also set the user's current borrow request
        user.setCurrentBorrowRequest(borrowRequest);
        userRepository.save(user);

        // send mail to owner
        emailService.sendEmail(
                bookCopy.getBook().getOwner().getEmail(),
                "New Borrow Request Received",
                "You have received a new borrow request from " + user.getFullName() + " to borrow " + bookCopy.getBook().getBookTitle() + ". Please check the website to respond."
        );

        return "Request sent to the owner";
    }

    @Transactional
    public String initiateHandover(BookCopy bookCopy, User currentUser) {
        //getting both parties taking part in handover
        User holder = bookCopy.getHolder();
        User borrower = bookCopy.getBorrower();

        //Check: current user is the holder of the book
        if (!currentUser.equals(holder)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User is not the holder of the book");
        }

        /*
         * If book copy is with owner and no borrower is approved, owner will be the holder and borrower himself. In this case handover can not be done.
         * In any other case, book will be handed over from holder to borrower. Note that owner still can be a holder or a borrower but not both.
         * If owner is the holder then the book is entering the cycle.
         * If owner is the borrower then the book is exiting the cycle by returning back to the owner.
         */
        //Check: book copy has a valid borrower
        if (borrower.equals(holder)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Book copy currently doesn't have a borrower.");
        }

        //Generating OTP
        String otp = otpService.generateOtp(6);

        //saving OTP
        bookCopy.setOtp(otp);
        bookCopyRepository.save(bookCopy);

        //sending OTP to borrower
        String to = borrower.getEmail();
        String subject = "OTP for book transaction";
        String message = String.format("""
                Here is your OTP for book transaction: %s
                NOTE!!!!:
                ONLY SHARE IT UPON RECEIVING THE BOOK WITH THE OTHER PARTY. SHARING THIS OTP WILL MEAN THAT YOU HAVE RECEIVED THE BOOK AND FROM HERE ON YOU TAKE ITS RESPONSIBILITY!""", otp);
        emailService.sendEmail(to, subject, message);

        //success
        return "Handover has initiated.An otp is sent to the borrower.";
    }

    @Transactional
    public String handoverBookCopy(BookCopy bookCopy, User currentUser, String otp) {
        //getting both parties taking part in handover
        User holder = bookCopy.getHolder();
        User borrower = bookCopy.getBorrower();
        User owner = bookCopy.getBook().getOwner();

        //Check: current user is the holder of the book
        if (!currentUser.equals(holder)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User is not the holder of the book");
        }

        //Check: handover is initiated
        if (bookCopy.getOtp() == null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Book handover is not initiated yet.");
        }

        //check: otp is correct
        if (!otp.equals(bookCopy.getOtp())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid OTP");
        }

        //handover book

        //set OTP to null
        bookCopy.setOtp(null);
        //borrower is now holder of the book copy
        bookCopy.setHolder(borrower);
        //book copy is supposed to return to owner until new borrower is approved by owner
        bookCopy.setBorrower(owner);
        bookCopyRepository.save(bookCopy);

        // modifying borrow requests' statuses

        BorrowRequest holderBorrowRequest = holder.getCurrentBorrowRequest();
        BorrowRequest borrowerBorrowRequest = borrower.getCurrentBorrowRequest();

        // holder borrow request can be null, in the case when it is just uploaded (by default, owner himself is the holder for newly uploaded books)
        // in that case, no need to do anything
        // also need to check if the current borrow requested book copy is same as the one being handed over
        // this is because if owner himself has the book initially, and owner has requested the book elsewhere, then it will be a different borrow request
        if((holderBorrowRequest!=null) && (holderBorrowRequest.getBookCopy().getId()==bookCopy.getId()))
        {
            // mark as completed
            holderBorrowRequest.setStatus(BorrowRequestStatus.COMPLETED);
            // also unset the current borrow request
            holder.setCurrentBorrowRequest(null);
            borrowRequestRepository.save(holderBorrowRequest);
            userRepository.save(holder);
        }

        // borrower borrow request can be null, in the case when the book is returned to the owner himself (by default, owner is the borrower for a book copy)
        // in that case, no need to do anything
        // also need to check if the current borrow requested book copy is same as the one being handed over
        // this is because if owner himself is about to receive the book, and owner has requested the book elsewhere, then it will be a different borrow request
        if((borrowerBorrowRequest!=null) && (borrowerBorrowRequest.getBookCopy().getId()==bookCopy.getId()))
        {
            // mark as received
            borrowerBorrowRequest.setStatus(BorrowRequestStatus.RECEIVED);
            borrowRequestRepository.save(borrowerBorrowRequest);
        }

        //record the transaction
        BookTransaction bookTransaction = new BookTransaction();
        bookTransaction.setBookCopy(bookCopy);
        bookTransaction.setBookGiver(holder);
        bookTransaction.setBookReceiver(borrower);
        bookTransaction.setTransactionDateTime(new Date());
        bookTransactionRepository.save(bookTransaction);

        //send emails

        //notify former holder
        String to = holder.getEmail();
        String subject = "Book copy transaction";
        String message = String.format("""
                        Dear user,
                        Book copy : %s, has been successfully transferred to following user
                        Name: %s
                        Email: %s
                        """,
                bookCopy.getBook().getBookTitle(),
                borrower.getFullName(),
                borrower.getEmail());
        emailService.sendEmail(to, subject, message);

        //notify new holder
        to = borrower.getEmail();
        message = String.format("""
                        Dear user,
                        Book copy : %s, has been successfully received from following user
                        Name: %s
                        Email: %s
                        """,
                bookCopy.getBook().getBookTitle(),
                holder.getFullName(),
                holder.getEmail());

        //success
        return "Book has been successfully handover";
    }

    @Transactional
    public BookTransactionsDTO getBookCopyTransactions(BookCopy bookCopy, User currentUser) {
        User owner = bookCopy.getBook().getOwner();

        // can the current user request for borrow
        boolean canRequest = (!owner.equals(currentUser))
                && (currentUser.getCurrentBorrowRequest()==null)
                && (bookCopy.getBorrower().equals(owner));

        //check: request is coming from owner
        if (!owner.equals(currentUser)) {

            // request not coming from the owner, so will not send transactions array in BookTransactionsDTO
            return mapper.bookCopyToBookTransactionsDTO(bookCopy, canRequest, false, (txn1, txn2)->0);

        }

        return mapper.bookCopyToBookTransactionsDTO
                (bookCopy,
                canRequest,
                true,
                // comparator to sort transactions with most recent ones being on top of the list
                new Comparator<BookTransaction>() {
                @Override
                    public int compare(BookTransaction o1, BookTransaction o2) {
                        return o1.getTransactionDateTime().compareTo(o2.getTransactionDateTime());
                    }
                }.reversed());

        //get transactions
//        List<BookTransaction> transactions = bookCopy.getTransactions();
//
//        //sort transactions with most recent ones being on top of the list
//        transactions.sort(new Comparator<BookTransaction>() {
//            @Override
//            public int compare(BookTransaction o1, BookTransaction o2) {
//                return o1.getTransactionDateTime().compareTo(o2.getTransactionDateTime());
//            }
//        }.reversed());
//
//        //return transactions
//        return transactions
//                .stream()
//                .map(mapper::bookTransactionToBookTransactionDTO)
//                .toList();
    }

    // to find the current user's approved books
    public List<BookDTO> getMyUploadedBooks(User user)
    {
        return bookRepository.findByOwnerAndAdminApproval(user, Approval.APPROVED)
                .stream()
                .map(mapper::bookToBookDTO)
                .toList();
    }

    // method to get the borrowed book copies of current user
    public List<BookCopyDTO> getMyBorrowedBookCopies(User user)
    {
        return user.getBorrowedBookCopies()
                .stream()
                .filter(bookCopy -> !(bookCopy.getBook().getOwner()).equals(user))
                .map(bookCopy -> mapper.bookCopyToBookCopyDTO(bookCopy, false))
                .toList();
    }

}
