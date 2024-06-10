package com.project.readers.readers_community.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class BorrowedBook {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@OneToMany
	//book which is taken by the member for reading
	@Column(name="taken_book")
	private Book takenBook;
	
	
	@Column(name="holder")
	//holder=current owner 
	private User holder;
	
	@Column(name="borrower")
	//borrower=person who has requested for that book->pending request person
	private User borrower;

	
	
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Book getTakenBook() {
		return takenBook;
	}

	public void setTakenBook(Book takenBook) {
		this.takenBook = takenBook;
	}

	public User getHolder() {
		return holder;
	}

	public void setHolder(User holder) {
		this.holder = holder;
	}

	public User getBorrower() {
		return borrower;
	}

	public void setBorrower(User borrower) {
		this.borrower = borrower;
	}

	public BorrowedBook(int id, Book takenBook, User holder, User borrower) {
		super();
		this.id = id;
		this.takenBook = takenBook;
		this.holder = holder;
		this.borrower = borrower;
	}
	
}
