package com.project.readers.readers_community.services;

import com.project.readers.readers_community.entities.BookCopy;
import com.project.readers.readers_community.entities.BorrowRequest;
import com.project.readers.readers_community.repositories.BookCopyRepository;
import com.project.readers.readers_community.repositories.BorrowRequestRepository;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.project.readers.readers_community.entities.Book;
import com.project.readers.readers_community.entities.User;
import com.project.readers.readers_community.enums.Approval;
import com.project.readers.readers_community.repositories.BookRepository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class BookService
{

	private final BookRepository bookRepository;
	private final BookCopyRepository bookCopyRepository;
	private final EmailService emailService;
	private BorrowRequestRepository borrowRequestRepository;

	public BookService(BookRepository bookRepository, BookCopyRepository bookCopyRepository, EmailService emailService)
	{
		this.bookRepository = bookRepository;
		this.bookCopyRepository = bookCopyRepository;
		this.emailService = emailService;
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
	public String requestForBorrow(long bookCopyId, User user)
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
}
