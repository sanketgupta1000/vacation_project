package com.project.readers.readers_community.controllers;

import com.project.readers.readers_community.DTOs.BookCopiesDTO;
import com.project.readers.readers_community.DTOs.BookCopyDTO;
import com.project.readers.readers_community.DTOs.BookDTO;
import com.project.readers.readers_community.DTOs.BookTransactionsDTO;
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
	@PostMapping
	public String uploadBook(@RequestBody Book book , @CurrentUser User currentUser)
	{
		return bookService.uploadBook(book,currentUser);
	}

	//to get all approved books
	@GetMapping
	public List<BookDTO> getAllBooks()
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
	@GetMapping("/{book_id}/bookCopies")
	public BookCopiesDTO getBookCopies(@PathVariable("book_id") long bookId, @CurrentUser User currentUser)
	{
		return bookService.getBookCopies(bookId, currentUser);
	}

	// method to request for a book copy (a physical book)
	@PostMapping("/{book_copy_id}/borrowRequest")
	public String requestForBorrow(@PathVariable("book_copy_id") int bookCopyId, @CurrentUser User user)
	{
		return bookService.requestForBorrow(bookCopyId, user);
	}

	@PostMapping("/{book_copy_id}/initiateHandover")
	public String initiateHandover(@PathVariable("book_copy_id")BookCopy bookCopy, @CurrentUser User currentUser)
	{
		return bookService.initiateHandover(bookCopy, currentUser);
	}

	@PostMapping("/{book_copy_id}/handover")
	public String handoverBookCopy(@PathVariable("book_copy_id")BookCopy bookCopy, @CurrentUser User currentUser, @RequestParam("otp") String otp)
	{
		return bookService.handoverBookCopy(bookCopy, currentUser, otp);
	}

	@GetMapping("/{book_copy_id}/transactions")
	public BookTransactionsDTO getBookCopyTransactions(@PathVariable("book_copy_id")BookCopy bookCopy, @CurrentUser User currentUser)
	{
		return bookService.getBookCopyTransactions(bookCopy, currentUser);
	}

	// method to get uploaded and approved books of the current user
	@GetMapping("/myUploadedBooks")
	public List<BookDTO> getMyUploadedBooks(@CurrentUser User user)
	{
		return bookService.getMyUploadedBooks(user);
	}

	// method to get the borrowed book copies of current user
	@GetMapping("/myBorrowedBookCopies")
	public List<BookCopyDTO> getMyBorrowedBookCopies(@CurrentUser User user)
	{
		return bookService.getMyBorrowedBookCopies(user);
	}

}
