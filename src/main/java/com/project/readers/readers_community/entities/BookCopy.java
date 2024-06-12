package com.project.readers.readers_community.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

//all copy of one actual book. this table represents the track of all copies of one same book
@Entity
@Table(name="book_copies")
public class BookCopy {

	//auto generated Id
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	//book which is taken by the member for reading
	@ManyToOne
	@JoinColumn(name="book_id")
	private Book book;

	//user that currently possesses this book copy
	@ManyToOne
	@JoinColumn(name="holder_id")
	private User holder;

	//user to whom the holder will pass on this book copy. Null means book copy will be returned to the owner by holder
	@ManyToOne
	@JoinColumn(name="borrower_id")
	private User borrower;

	//OTP to confirm book transaction for this copy
	@Column(name = "otp")
	private String otp;

	//transactions involving this book copy
	@OneToMany(mappedBy = "bookCopy")
	@JsonIgnore
	private List<BookTransaction> transactions;

	//book borrow request involving this book copy
	@OneToMany(mappedBy = "bookCopy")
	@JsonIgnore
	private List<BorrowRequest> borrowRequests;

	public BookCopy() {
	}

	public BookCopy(int id, Book book, User holder, User borrower) {
		this.id = id;
		this.book = book;
		this.holder = holder;
		this.borrower = borrower;
		this.otp = null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
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

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public List<BookTransaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<BookTransaction> transactions) {
		this.transactions = transactions;
	}

	public List<BorrowRequest> getBorrowRequests() {
		return borrowRequests;
	}

	public void setBorrowRequests(List<BorrowRequest> borrowRequests) {
		this.borrowRequests = borrowRequests;
	}

	@Override
	public String toString() {
		return "BookCopy{" +
				"id=" + id +
				", book=" + book +
				", holder=" + holder +
				", borrower=" + borrower +
				'}';
	}
}
