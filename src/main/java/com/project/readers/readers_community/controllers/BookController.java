package com.project.readers.readers_community.controllers;

import com.project.readers.readers_community.entities.BookCategory;
import com.project.readers.readers_community.entities.BookCopy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.project.readers.readers_community.annotations.CurrentUser;
import com.project.readers.readers_community.entities.Book;
import com.project.readers.readers_community.entities.User;
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
	public String requestForBorrow(@PathVariable("book_copy_id") long bookCopyId, @CurrentUser User user)
	{
		return bookService.requestForBorrow(bookCopyId, user);
	}

}
