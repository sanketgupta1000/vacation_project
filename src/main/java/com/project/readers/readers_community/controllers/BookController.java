package com.project.readers.readers_community.controllers;

import com.project.readers.readers_community.entities.BookCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.readers.readers_community.annotations.CurrentUser;
import com.project.readers.readers_community.entities.Book;
import com.project.readers.readers_community.entities.User;
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



}
