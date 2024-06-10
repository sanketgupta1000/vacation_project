package com.project.readers.readers_community.entities;

import com.project.readers.readers_community.enums.Approval;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;


@Entity
public class BookApprovalRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@OneToOne
	private Book book;
	
	//whose book request is this
	 @ManyToOne
	 @JoinColumn(name = "member_id")
	 private User member;
		 
		 //approval of admin 
	 private Approval admin_approval;
		 
	
	
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public BookApprovalRequest(long id, User member, Approval admin_approval,Book book) {
		super();
		this.id = id;
		this.member = member;
		this.admin_approval = admin_approval;
		this.book=book;
	}

	public BookApprovalRequest() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getMember() {
		return member;
	}

	public void setMember(User member) {
		this.member = member;
	}

	public Approval getAdmin_approval() {
		return admin_approval;
	}

	public void setAdmin_approval(Approval admin_approval) {
		this.admin_approval = admin_approval;
	}

	

	
}
