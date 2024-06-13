package com.project.readers.readers_community.controllers;

import com.project.readers.readers_community.DTOs.BookCopyDTO;
import com.project.readers.readers_community.DTOs.BookDTO;
import com.project.readers.readers_community.entities.*;
import org.springframework.web.bind.annotation.*;
import com.project.readers.readers_community.annotations.CurrentUser;
import com.project.readers.readers_community.services.BookService;

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
	public String upload_boook(@RequestBody Book book , @CurrentUser User currentUser)
	{
		return bookService.bookUpload(book,currentUser);
		
	}
	
	
	//method to get all unresponded request
	//method for admin
	@GetMapping("/getAllRequests")
	public List<BookDTO> getAllRequests()
	{
		return bookService.getAllRequests();
	}
	
	//to approve book upload request
	//for admin
	@PostMapping("/requests/{book_id}/approveBook")
	public String bookApproval(@PathVariable long book_id)
	{
		return bookService.approve_book(book_id);
		
	}

	//to reject book upload request
	//for admin
	@PostMapping("/requests/{book_id}/rejectBook")
	public String bookRejection(@PathVariable long book_id)
	{
		return bookService.reject_book(book_id);
		
	}
	
	//to get all books
	@GetMapping("/getAllBooks")
	public List<BookDTO> getallbooks()
	{
		return bookService.getAllBooks();
	}
	
	

	// method to get a book's data
	@GetMapping("/{book_id}")
	public BookDTO getBook(@PathVariable("book_id") long bookId)
	{
		return bookService.getBook(bookId);
	}

	// method to get all book copies of a book
	@GetMapping("/{book_id}/view-copies")
	public List<BookCopyDTO> getBookCopies(@PathVariable("book_id") long bookId)
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
