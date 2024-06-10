package com.project.readers.readers_community.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.readers.readers_community.entities.Book;
import com.project.readers.readers_community.entities.User;
import com.project.readers.readers_community.enums.Approval;
import com.project.readers.readers_community.repositories.BookRepository;

@Service
public class BookService {

	private BookRepository bookrepo;
	
	private BookApprovalRequest obj;
	
	
	public BookApprovalRequest getObj() {
		return obj;
	}
	@Autowired
	public void setObj(BookApprovalRequest obj) {
		this.obj = obj;
	}
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
		
		obj.setAdmin_approval(Approval.APPROVED);
		
		
		bookrepo.save(book);
		
	}
	public void reject_book_request(Book book) {
		
		obj.setAdmin_approval(Approval.REJECTED);
	
		bookrepo.save(book);
		
	}
	public void deletePost(long bookid) {
		 Book book=bookrepo.findById(bookid).get();
		
		bookrepo.delete(book);
		
	}
	public void managae_book_request(Book book, User cur_user) {
		
		
		
		
		obj.setMember(cur_user);
		obj.setBook(book);
		
	}

}
