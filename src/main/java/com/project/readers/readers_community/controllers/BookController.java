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
