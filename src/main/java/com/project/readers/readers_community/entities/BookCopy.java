package com.project.readers.readers_community.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
//all copy of one actual book. this table represents the track of all copies of one same book 
@Table(name="book_copies")
public class BookCopy {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@OneToMany
	//book which is taken by the member for reading
	@Column(name="taken_books")
	private Book takenBook;
	
	
	@Column(name="holder")
	//holder=current owner 
	@OneToOne
	private User holder;
	
	@Column(name="borrower")
	//borrower=person who has requested for that book->pending request person
	@OneToOne
	private User borrower;

	
	
	
	
	
	public int getId() {
		return id;
	}

	public void setId  (int id) {
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

	public BookCopy(int id, Book takenBook, User holder, User borrower) {
		super();
		this.id = id;
		this.takenBook = takenBook;
		this.holder = holder;
		this.borrower = borrower;
	}

	
}
