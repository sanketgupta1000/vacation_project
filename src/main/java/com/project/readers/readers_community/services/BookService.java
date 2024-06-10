package com.project.readers.readers_community.services;

import java.util.*;
import org.springframework.stereotype.Service;
import com.project.readers.readers_community.entities.Book;
import com.project.readers.readers_community.enums.Approval;
import com.project.readers.readers_community.repositories.BookRepository;

@Service
public class BookService
{

	private BookRepository bookRepository;

	public void upload_book(Book book) {
		
		bookRepository.save(book);
	}

	public void approvalOfBook(long bookId) {
	
		Book book=bookRepository.findById(bookId).get();
		book.setAdminApproval(Approval.APPROVED);
		bookRepository.save(book);
		
	}
	
	public void rejectionOfBook(long bookId) {
		
		Book book=bookRepository.findById(bookId).get();
		book.setAdminApproval(Approval.REJECTED);
		bookRepository.save(book);
		
	}
    
	
	
	
	public List<Book> getallbooks() {
		
		List<Book> allBooks=bookRepository.executeMyOwnQuery();
		return allBooks;
	}
	
	
	public List<Book> getAllRequests()
	{
		return bookRepository.findByadminApproval(0);
	}
	
	


}
