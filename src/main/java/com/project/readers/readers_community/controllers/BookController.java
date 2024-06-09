package com.project.readers.readers_community.controllers;

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
@RequestMapping("/book")
public class BookController {
	
	private BookService bookservice;

	public BookService getBookservice() {
		return bookservice;
	}
	@Autowired
	public void setBookservice(BookService bookservice) {
		this.bookservice = bookservice;
	}

	@PostMapping("/uploadPost")
	public String Book_post(Book book)
	{
		bookservice.upload_book_post(book);
		return "book uploaded successfully";
	}
	
	@PostMapping("/BookRequest")
	public String Book_post_request(Book book , @CurrentUser User cur_user)
	{
		bookservice.managae_book_request(book,cur_user);
		return "book request sent to admin";
	}
	
	@PostMapping("/bookRequestAccept")
	public String Book_request_accept(Book book)
	{
		bookservice.approve_book_request(book);
		return "your book upload request is accepted.";
	}
	
	@DeleteMapping("/deleteBookPost/{bookid}")
	public String detele_bookPost(@PathVariable ("bookid") long bookid)
	{
		bookservice.deletePost(bookid);
		return "your post deleted successfully";
	}
}
