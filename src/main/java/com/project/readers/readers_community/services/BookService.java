package com.project.readers.readers_community.services;

import com.project.readers.readers_community.entities.BookCopy;
import com.project.readers.readers_community.repositories.BookCategoryRepository;
import com.project.readers.readers_community.repositories.BookCopyRepository;
import com.project.readers.readers_community.entities.BorrowRequest;
import com.project.readers.readers_community.repositories.BorrowRequestRepository;
import com.project.readers.readers_community.entities.BookTransaction;
import com.project.readers.readers_community.repositories.BookTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.project.readers.readers_community.entities.Book;
import com.project.readers.readers_community.entities.BookCategory;
import com.project.readers.readers_community.repositories.BookRepository;
import com.project.readers.readers_community.entities.User;
import com.project.readers.readers_community.enums.Approval;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;

@Service
public class BookService
{
	private final BookCopyRepository bookCopyRepository;
	private final BookRepository bookRepository;
	private final OtpService otpService;
	private final EmailService emailService;
	private final BookCategoryRepository bookCategoryRepository;
	private final BookTransactionRepository bookTransactionRepository;
  	private final BorrowRequestRepository borrowRequestRepository;

	public BookService(BookRepository bookRepository, OtpService otpService, EmailService emailService, BookCopyRepository bookCopyRepository, BookTransactionRepository bookTransactionRepository, BorrowRequestRepository borrowRequestRepository, BookCategoryRepository bookCategoryRepository) {
		this.bookRepository = bookRepository;
		this.otpService = otpService;
		this.emailService = emailService;
		this.bookCopyRepository = bookCopyRepository;
		this.bookTransactionRepository = bookTransactionRepository;
    	this.borrowRequestRepository = borrowRequestRepository;
		this.bookCategoryRepository = bookCategoryRepository;
	}
  
  // method to get a book by id
	public Book getBook(long bookId)
	{
		Optional<Book> bookOptional = bookRepository.findById(bookId);

		if(bookOptional.isPresent())
		{
			return bookOptional.get();
		}

		// did not find the book
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
	}

	// method to get book copies of a book
	public List<BookCopy> getBookCopies(long bookId)
	{
		// first, will get the book
		Optional<Book> bookOptional = bookRepository.findById(bookId);

		if(bookOptional.isPresent())
		{
			return bookOptional.get().getBookCopies();
		}
		// not found
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found");
	}

	// method to create a borrow request for a book copy
	public String requestForBorrow(int bookCopyId, User user)
	{
		Optional<BookCopy> bookCopyOptional = bookCopyRepository.findById(bookCopyId);

		if(bookCopyOptional.isEmpty())
		{
			// no book copy found
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "BookCopy not found");
		}

		BookCopy bookCopy = bookCopyOptional.get();

		// checking if borrower is other than owner, i.e., is the book going to be passed on to someone already?
		if(!bookCopy.getBorrower().equals(bookCopy.getBook().getOwner()))
		{
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Borrower already exists.");
		}

		BorrowRequest borrowRequest = new BorrowRequest();
		borrowRequest.setId(0);
		borrowRequest.setRequester(user);
		borrowRequest.setBookCopy(bookCopy);
		borrowRequest.setOwnerApproval(Approval.UNRESPONDED);

		// save
		borrowRequestRepository.save(borrowRequest);

		// send mail to owner
		emailService.sendEmail(
				bookCopy.getBook().getOwner().getEmail(),
				"New Borrow Request Received",
				"You have received a new borrow request from "+user.getFullName()+" to borrow "+bookCopy.getBook().getBookTitle()+". Please check the website to respond."
		);

		return "Request sent to the owner";
	}

	public String initiate_handover(BookCopy bookCopy, User currentUser)
	{
		//getting both parties taking part in handover
		User holder = bookCopy.getHolder();
		User borrower = bookCopy.getBorrower();

		//Check: current user is the holder of the book
		if(!currentUser.equals(holder))
		{
			throw new ResponseStatusException(HttpStatus.FORBIDDEN,  "User is not the holder of the book");
		}

		/*
		* If book copy is with owner and no borrower is approved, owner will be the holder and borrower himself. In this can handover can not be done.
		* In any other case, book will be handed over from holder to borrower. Note that owner still can be a holder or a borrower but not both.
		* If owner is the holder then the book is entering the cycle.
		* If owner is the borrower then the book is exiting the cycle by returning back to the owner.
		*/
		//Check: book copy has a valid borrower
		if(borrower == holder)
		{
			throw  new ResponseStatusException(HttpStatus.CONFLICT, "Book copy currently doesn't have a borrower.");
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
                ONLY SHARE IT UPON RECEIVING THE BOOK WITH THE OTHER PARTY. SHARING THIS OTP WILL MEAN THAT YOU HAVE RECEIVED THE BOOK AND FROM HERE ON YOU TAKE IT's RESPONSIBILITY!""", otp);
		emailService.sendEmail(to, subject, message);

		//success
		return "Handover has initiated.An otp is sent to the borrower.";
	}

	public String handoverBookCopy(BookCopy bookCopy, User currentUser, String otp)
	{
		//getting both parties taking part in handover
		User holder = bookCopy.getHolder();
		User borrower = bookCopy.getBorrower();
		User owner = bookCopy.getBook().getOwner();

		//Check: current user is the holder of the book
		if(!currentUser.equals(holder))
		{
			throw new ResponseStatusException(HttpStatus.FORBIDDEN,  "User is not the holder of the book");
		}

		//Check: handover is initiated
		if(bookCopy.getOtp() == null)
		{
			throw new ResponseStatusException(HttpStatus.CONFLICT, "Book handover is not initiated yet.");
		}

		//check: otp is correct
		if(!otp.equals(bookCopy.getOtp()))
		{
			throw  new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid OTP");
		}

		//handover book

		//set OTP to null
		bookCopy.setOtp(null);
		bookCopyRepository.save(bookCopy);

		//borrower is now holder of the book copy
		bookCopy.setHolder(borrower);
		//book copy is supposed to return to owner until new borrower is approved by owner
		bookCopy.setBorrower(owner);

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
		emailService.sendEmail(to,subject,message);

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

	public List<BookTransaction> getBookTransactions(BookCopy bookCopy, User currentUser)
	{
		User owner = bookCopy.getBook().getOwner();

		//check: request is coming from owner
		if(!owner.equals(currentUser))
		{
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User does not have access to this book's history");
		}
    
    //get transactions
		List<BookTransaction> transactions = bookCopy.getTransactions();

		//sort transactions with most recent ones being on top of the list
		transactions.sort(new Comparator<BookTransaction>() {
            @Override
            public int compare(BookTransaction o1, BookTransaction o2) {
                return o1.getTransactionDateTime().compareTo(o2.getTransactionDateTime());
            }
        }.reversed());

		//return transactions
		return transactions;
  }

	public String bookUpload(Book book,User cureentUser) {
		
		book.setId(0L);
		book.setOwner(cureentUser);
		
		long bookcategory_id=book.getCategory().getId();
		
		BookCategory bc=bookCategoryRepository.findById(bookcategory_id).get();
		
		book.setCategory(bc);
		
		bookRepository.save(book);
		return "your book upload request is sent to admin";
		
	}

	public List<Book> getAllRequests() {
		
		return bookRepository.findByAdminApproval(Approval.UNRESPONDED);
	}

	public String approve_book(long book_id) {
		
		
		
		Book book=bookRepository.findById(book_id).get();
		if(book==null)
		{
			return "no book found with this id ";
		}
		if(book.getAdminApproval()==Approval.UNRESPONDED)
		{
		book.setAdminApproval(Approval.APPROVED);
		bookRepository.save(book);
		
		//create copy of books equals to quantity
		int quantity=book.getQuantity(); 
		for(int i=1;i<=quantity;i++)
		{
			BookCopy bookcopy=new BookCopy();
			bookcopy.setBook(book);
			bookcopy.setBorrower(book.getOwner());
			bookcopy.setHolder(book.getOwner());
			bookCopyRepository.save(bookcopy);
		}
		return "your request is accepted";
		}
		else 
		{
			return "already responded";
		}
	}

	public String reject_book(long book_id) {
		Book book=bookRepository.findById(book_id).get();
		if(book==null)
		{
			return "no book found with this id ";
		}
		if(book.getAdminApproval()==Approval.UNRESPONDED)
		{
		book.setAdminApproval(Approval.REJECTED);
		
		bookRepository.save(book);
		return "your book request is rejected";
		}
		else 
		{
			return "already responded";
		}

	}

	public List<Book> getAllBooks() {
			return bookRepository.findByAdminApproval(Approval.APPROVED);
	}
}
