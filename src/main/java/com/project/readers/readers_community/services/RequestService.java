package com.project.readers.readers_community.services;

import com.project.readers.readers_community.DTOs.BookDTO;
import com.project.readers.readers_community.DTOs.BorrowRequestDTO;
import com.project.readers.readers_community.DTOs.Mapper;
import com.project.readers.readers_community.DTOs.MemberApprovalRequestDTO;
import com.project.readers.readers_community.entities.*;
import com.project.readers.readers_community.enums.Approval;
import com.project.readers.readers_community.enums.BorrowRequestStatus;
import com.project.readers.readers_community.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class RequestService
{

    private final BorrowRequestRepository borrowRequestRepository;
    private final EmailService emailService;
    private final BookCopyRepository bookCopyRepository;
    private final Mapper mapper;
    private final MemberApprovalRequestRepository memberApprovalRequestRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public RequestService(BorrowRequestRepository borrowRequestRepository, EmailService emailService, BookCopyRepository bookCopyRepository, Mapper mapper, MemberApprovalRequestRepository memberApprovalRequestRepository, BookRepository bookRepository, UserRepository userRepository) {
        this.borrowRequestRepository = borrowRequestRepository;
        this.emailService = emailService;
        this.bookCopyRepository = bookCopyRepository;
        this.mapper = mapper;
        this.memberApprovalRequestRepository = memberApprovalRequestRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public Map<String,List<MemberApprovalRequestDTO>> getAllMemberApprovalRequests()
    {
        Map<String, List<MemberApprovalRequestDTO>> requestMap = new HashMap<>();
        requestMap.put("unresponded", memberApprovalRequestRepository.findByAdminApproval(Approval.UNRESPONDED).stream().map(mapper::memberApprovalRequestToMemberApprovalRequestDTO).toList());
        requestMap.put("approved", memberApprovalRequestRepository.findByAdminApproval(Approval.APPROVED).stream().map(mapper::memberApprovalRequestToMemberApprovalRequestDTO).toList());
        requestMap.put("rejected", memberApprovalRequestRepository.findByAdminApproval(Approval.REJECTED).stream().map(mapper::memberApprovalRequestToMemberApprovalRequestDTO).toList());
        return requestMap;
    }

    @Transactional
    public String approveMemberApprovalRequestFromReference(MemberApprovalRequest request, User currentUser)
    {

        User reference = request.getMember().getReferrer();
        if(!currentUser.getEmail().equals(reference.getEmail()))
        {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User does not have access to this member approval request");
        }

        if( request.getReferrerApproval() != Approval.UNRESPONDED )
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Member approval request has already been responded to");
        }

        request.setReferrerApproval(Approval.APPROVED);

        memberApprovalRequestRepository.save(request);

        String to = request.getMember().getEmail();
        String subject = "Member approval request status";
        String message = "Your member approval request has been approved from your reference's side. Please wait for the admin's response.";
        emailService.sendEmail(to, subject, message);

        return "Member approval request has been approved from the reference side";
    }

    @Transactional
    public String rejectMemberApprovalRequestFromReference(MemberApprovalRequest request, User currentUser)
    {

        User reference = request.getMember().getReferrer();
        if(!currentUser.getEmail().equals(reference.getEmail()))
        {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User does not have access to this member approval request");
        }

        if( request.getReferrerApproval() != Approval.UNRESPONDED )
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Member approval request has already been responded to");
        }

        request.setReferrerApproval(Approval.REJECTED);

        memberApprovalRequestRepository.save(request);

        String to = request.getMember().getEmail();
        String subject = "Member approval request status";
        String message = "Your member approval request has been rejected from your reference's side. Please wait for the admin's response.";
        emailService.sendEmail(to, subject, message);

        return "Member approval request has been rejected from the reference side";
    }

    @Transactional
    public String approveMemberApprovalRequestFromAdmin(MemberApprovalRequest request)
    {

        if( request.getAdminApproval() != Approval.UNRESPONDED )
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Member approval request has already been responded to");
        }

        request.setAdminApproval(Approval.APPROVED);

        memberApprovalRequestRepository.save(request);

        String to = request.getMember().getEmail();
        String subject = "Member approval request status";
        String message = "Congratulations! Your member approval request has been approved by the admin. Please login to the site with the email and password used at the time of registration.";
        emailService.sendEmail(to, subject, message);

        return "Member approval request has been approved from the admin side";
    }

    @Transactional
    public String rejectMemberApprovalRequestFromAdmin(MemberApprovalRequest request)
    {


        if( request.getAdminApproval() != Approval.UNRESPONDED )
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Member approval request has already been responded to");
        }

        request.setAdminApproval(Approval.REJECTED);

        memberApprovalRequestRepository.save(request);

        String to = request.getMember().getEmail();
        String subject = "Member approval request status";
        String message = "Sorry, user! Your member approval request has been rejected by the admin. All your personal details has been removed from the site.";
        emailService.sendEmail(to, subject, message);

        return "Member approval request has been rejected from the admin side";
    }

    @Transactional
    public List<BookDTO> getAllBookUploadRequests() {

        return bookRepository.findByAdminApproval(Approval.UNRESPONDED)
                .stream()
                .map(mapper::bookToBookDTO)
                .toList();
    }

    @Transactional
    public String approveBookUploadRequest(long book_id) {


        Optional<Book> book = bookRepository.findById(book_id);
        if (book.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");

        }

        if (book.get().getAdminApproval() == Approval.UNRESPONDED) {
            book.get().setAdminApproval(Approval.APPROVED);
            bookRepository.save(book.get());

            //create copy of books equals to quantity
            int quantity = book.get().getQuantity();
            for (int i = 1; i <= quantity; i++) {
                BookCopy bookcopy = new BookCopy();
                bookcopy.setBook(book.get());
                bookcopy.setBorrower(book.get().getOwner());
                bookcopy.setHolder(book.get().getOwner());
                bookCopyRepository.save(bookcopy);
            }

            Book bookObj = book.get();

            // send mail to owner
            emailService.sendEmail(
                    bookObj.getOwner().getEmail(),
                    "Book upload request approved",
                    "Congratulations! Your upload request for the book "+bookObj.getBookTitle()+" was approved!"
            );

            return "Book upload request approved";

        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Book upload request already responded to");

        }
    }

    @Transactional
    public String rejectBookUploadRequest(long book_id) {
        Optional<Book> book = bookRepository.findById(book_id);
        if (book.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");

        }
        if (book.get().getAdminApproval() == Approval.UNRESPONDED) {
            book.get().setAdminApproval(Approval.REJECTED);

            bookRepository.save(book.get());

            Book bookObj = book.get();

            // send mail to owner
            emailService.sendEmail(
                    bookObj.getOwner().getEmail(),
                    "Book upload request rejected",
                    "Unfortunately, your upload request for the book "+bookObj.getBookTitle()+" was rejected"
            );

            return "Book upload request rejected";

        } else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Book upload request already responded to");

        }

    }

    // method to get all borrow requests of the current user
    // TODO: implement a query in a custom repository for this
    @Transactional
    public List<BorrowRequestDTO> getAllBorrowRequests(User user)
    {
        List<BorrowRequest> borrowRequests = new ArrayList<BorrowRequest>();

        for(Book b: user.getUploadedBooks())
        {
            for(BookCopy bookCopy: b.getBookCopies())
            {
                borrowRequests.addAll(bookCopy.getBorrowRequests());
            }
        }

        return borrowRequests
                .stream()
                .map(mapper::borrowRequestToBorrowRequestDTO)
                .toList();
    }

    // method to let the book owner approve a borrow request
    @Transactional
    public String approveBorrowRequest(int borrowRequestId, User user)
    {

        Optional<BorrowRequest> borrowRequestOptional = borrowRequestRepository.findById(borrowRequestId);

        if(borrowRequestOptional.isEmpty())
        {
            // not found
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Borrow Request Not Found");
        }

        BorrowRequest borrowRequest = borrowRequestOptional.get();

        // checking if it is a request on one of the current user's books
        if(!borrowRequest.getBookCopy().getBook().getOwner().equals(user))
        {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not allowed to approve this request");
        }

        // checking if already responded
        if(borrowRequest.getStatus()!= BorrowRequestStatus.UNRESPONDED)
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Request is already responded to");
        }

        // checking if is already meant to be passed on to somebody else
        if(!borrowRequest.getBookCopy().getBorrower().equals(borrowRequest.getBookCopy().getBook().getOwner()))
        {
            // already meant to be passed on
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Borrower already assigned");
        }

        // now can approve
        borrowRequest.setStatus(BorrowRequestStatus.APPROVED);

        // set requester as next borrower
        BookCopy bookCopy = borrowRequest.getBookCopy();
        bookCopy.setBorrower(borrowRequest.getRequester());

        // save
        borrowRequestRepository.save(borrowRequest);
        bookCopyRepository.save(bookCopy);

        // send emails

        // email to the requester
        emailService.sendEmail(
                borrowRequest.getRequester().getEmail(),
                "Borrow Request Approved",
                "Congratulations! Your borrow request on book "+borrowRequest.getBookCopy().getBook().getBookTitle()+" has been approved."
        );

        // email to the holder
        emailService.sendEmail(
                borrowRequest.getBookCopy().getHolder().getEmail(),
                "Instruction to forward your borrowed book",
                "You are instructed by the owner to pass on your borrowed book "+borrowRequest.getBookCopy().getBook().getBookTitle()+" to "+borrowRequest.getRequester().getFullName()
        );

        // now reject all other active borrow requests of the book copy
        List<BorrowRequest> brl = bookCopy.getBorrowRequests();
        List<BorrowRequest> active = new ArrayList<>();
        List<User> obsoleteBorrowers = new ArrayList<>();
        for(BorrowRequest br: brl)
        {
            if((br.getStatus()==BorrowRequestStatus.UNRESPONDED) && (br.getId()!=borrowRequestId))
            {
                br.setStatus(BorrowRequestStatus.REJECTED);
                active.add(br);
                // since will reject, set current borrow request to null
                br.getRequester().setCurrentBorrowRequest(null);
                obsoleteBorrowers.add(br.getRequester());
            }
        }

        // save all
        borrowRequestRepository.saveAll(active);
        userRepository.saveAll(obsoleteBorrowers);

        // TODO: send emails to all those whose requests are rejected

        return "Borrow Request Approved";

    }

    // method to let the book owner reject a borrow request
    @Transactional
    public String rejectBorrowRequest(int borrowRequestId, User user)
    {
        Optional<BorrowRequest> borrowRequestOptional = borrowRequestRepository.findById(borrowRequestId);

        if(borrowRequestOptional.isEmpty())
        {
            // not found
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Borrow Request Not Found");
        }

        BorrowRequest borrowRequest = borrowRequestOptional.get();

        // checking if it is a request on one of the current user's books
        if(!borrowRequest.getBookCopy().getBook().getOwner().equals(user))
        {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not allowed to approve this request");
        }

        // checking if already responded
        if(borrowRequest.getStatus()!=BorrowRequestStatus.UNRESPONDED)
        {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Request is already responded to");
        }

        // can reject
        borrowRequest.setStatus(BorrowRequestStatus.REJECTED);

        // also set the requester's current borrow request to null
        User requester = borrowRequest.getRequester();
        requester.setCurrentBorrowRequest(null);

        // save
        borrowRequestRepository.save(borrowRequest);
        userRepository.save(requester);

        // send email to requester
        emailService.sendEmail(
                borrowRequest.getRequester().getEmail(),
                "Borrow request rejected",
                "Sorry! Your borrow request for book "+borrowRequest.getBookCopy().getBook().getBookTitle()+" has been rejected."
        );

        return "Borrow Request Rejected";

    }

    public List<BorrowRequestDTO> getMyBorrowRequest(User user)
    {
        return user.getBorrowRequests()
                .stream()
                .map(mapper::borrowRequestToBorrowRequestDTO)
                .toList();
    }

    public List<BookDTO> getMyUploadRequests(User user) {

        return user.getUploadedBooks().stream().map(mapper::bookToBookDTO).toList();

    }
}
