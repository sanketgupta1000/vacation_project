package com.project.readers.readers_community.services;

import com.project.readers.readers_community.entities.BookCopy;
import com.project.readers.readers_community.entities.BookTransaction;
import com.project.readers.readers_community.repositories.BookCopyRepository;
import com.project.readers.readers_community.repositories.BookTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.project.readers.readers_community.entities.Book;
import com.project.readers.readers_community.entities.User;
import com.project.readers.readers_community.enums.Approval;
import com.project.readers.readers_community.repositories.BookRepository;
import org.springframework.web.server.ResponseStatusException;

import javax.xml.crypto.Data;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class BookService
{
	private final BookCopyRepository bookCopyRepository;
	private final BookRepository bookRepository;
	private final OtpService otpService;
	private final EmailService emailService;
	private final BookTransactionRepository bookTransactionRepository;

	public BookService(BookRepository bookRepository, OtpService otpService, EmailService emailService, BookCopyRepository bookCopyRepository, BookTransactionRepository bookTransactionRepository) {
		this.bookRepository = bookRepository;
		this.otpService = otpService;
		this.emailService = emailService;
		this.bookCopyRepository = bookCopyRepository;
		this.bookTransactionRepository = bookTransactionRepository;
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
}
