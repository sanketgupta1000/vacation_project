package com.project.readers.readers_community.controllers;

import java.util.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.project.readers.readers_community.entities.Book;
import com.project.readers.readers_community.services.BookService;

@RestController
@RequestMapping("/books")
public class BookController
{
	
	private final BookService bookService;

	public BookController(BookService bookService)
	{
		this.bookService = bookService;
	}
	
	//book controller for upload request from member 
	@PostMapping("/uploadBook")
	public String uploadBook(@RequestBody Book book)
	{
		bookService.upload_book(book);
		return "your request is send to admin";
	}
	
	
	//for admin
	@PostMapping("/requests/{bookId}/approveBook")
	public String approveBook(@PathVariable long bookId)
	{
		bookService.approvalOfBook(bookId);
		return "your book upload request is accepted";
	}
	
	//for admin
	@PostMapping("/requests/{bookId}/rejectBook")
	public String rejectBook(@PathVariable long bookId)
	{
		bookService.rejectionOfBook(bookId);
		return "your book upload request is rejected";
	}
	
	
	@GetMapping("/getAllBooks")
	public List<Book> getAllBook()
	{
		return bookService.getallbooks();
	}
	
	//for admin
	@GetMapping("/getAllRequests")
	public List<Book> getAllRequest()
	{
		return bookService.getAllRequests();
	}

	


}
