package com.project.readers.readers_community.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.readers.readers_community.entities.Book;
import com.project.readers.readers_community.repositories.BookRepository;

@Service
public class BookService {

	private BookRepository bookrepo;
	
	public BookRepository getBookrepo() {
		return bookrepo;
	}
	@Autowired
	public void setBookrepo(BookRepository bookrepo) {
		this.bookrepo = bookrepo;
	}

	public void upload_book_post(Book book) {
		
		bookrepo.save(book);
		
	}
	public void approve_book_request(Book book) {
		
		book.setApproval_status(true);
		bookrepo.save(book);
		
	}
	public void reject_book_request(Book book) {
		 book.setApproval_status(false);
		bookrepo.save(book);
		
	}
	public void deletePost(long bookid) {
		 Book book=bookrepo.findById(bookid).get();
		
		bookrepo.delete(book);
		
	}

}
