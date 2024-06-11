package com.project.readers.readers_community.controllers;

import com.project.readers.readers_community.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.project.readers.readers_community.annotations.CurrentUser;
import com.project.readers.readers_community.services.BookService;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;



@RestController
@RequestMapping("/books")
public class BookController
{
	private final BookService bookService;

	public BookController(BookService bookService)
	{
		this.bookService = bookService;
	}
	
	//request for book upload 
	@PostMapping("/uploadBook")
	public String upload_boook(@RequestBody Book book)
	{
		bookService.bookUpload(book);
		return "your book upload request is sent to admin";
	}
	
	
	//method to get all unresponded request
	//method for admin
	@GetMapping("/getAllRequests")
	public List<Book> getAllRequests()
	{
		return bookService.getallrequests();
	}
	
	//to approve book upload request
	//for admin
	@PostMapping("/requests/{book_id}/approveBook")
	public String bookApproval(@PathVariable long book_id)
	{
		bookService.approve_book(book_id);
		return "your upload request is approved";
	}
	
	
	//to reject book upload request
	//for admin
	@PostMapping("/requests/{book_id}/rejectBook")
	public String bookRejection(@PathVariable long book_id)
	{
		bookService.reject_book(book_id);
		return "your upload request is rejected";
	}
	
	//to get all books
	@GetMapping("/getAllBooks")
	public List<Book> getallbooks()
	{
		return bookService.getAllRequest();
	}
	
	

	// method to get a book's data
	@GetMapping("/{book_id}")
	public Book getBook(@PathVariable("book_id") long bookId)
	{
		return bookService.getBook(bookId);
	}

	// method to get all book copies of a book
	@GetMapping("/{book_id}/view-copies")
	public List<BookCopy> getBookCopies(@PathVariable("book_id") long bookId)
	{
		return bookService.getBookCopies(bookId);
	}

	// method to request for a book copy (a physical book)
	@PostMapping("/{book_copy_id}/borrow-request")
	public String requestForBorrow(@PathVariable("book_copy_id") int bookCopyId, @CurrentUser User user)
	{
		return bookService.requestForBorrow(bookCopyId, user);
  }

	@PostMapping("/{book_copy_id}/initiate_handover")
	public String initiate_handover(@PathVariable("book_copy_id")BookCopy bookCopy, @CurrentUser User currentUser)
	{
		return bookService.initiate_handover(bookCopy, currentUser);
	}

	@PostMapping("/{book_copy_id}/handover")
	public String handoverBookCopy(@PathVariable("book_copy_id")BookCopy bookCopy, @CurrentUser User currentUser, @RequestParam("otp") String otp)
	{
		return bookService.handoverBookCopy(bookCopy, currentUser, otp);
	}

	@GetMapping("/{book_copy_id}/transaction_history")
	public List<BookTransaction> getBookTransactions(@PathVariable("book_copy_id")BookCopy bookCopy, @CurrentUser User currentUser)
	{
		return bookService.getBookTransactions(bookCopy, currentUser);
	}

}
