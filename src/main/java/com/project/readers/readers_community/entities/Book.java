package com.project.readers.readers_community.entities;

import com.project.readers.readers_community.enums.Approval;

import jakarta.persistence.*;

@Entity
public class Book {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		
		@ManyToOne
		@Column(name="book_owner")
		private User bookOwner ;
		
		@Column(name="book_author")
		private String bookAuthor ;
		
		@Column(name="page_no")
		private int pageNo;
		
		@Column(name="book_name")
		private String bookName;
		
		@Column(name="quantity")
		private int quantity;
		
		@Column(name="catagory")
		private String catagory;
		
		@Column(name="approval_status")
		private  Approval approvalStatus;
		
		
		

		public Book(Long id, User bookOwner, String bookAuthor, int pageNo, String bookName, int quantity,
				String catagory, Approval approvalStatus) {
			super();
			this.id = id;
			this.bookOwner = bookOwner;
			this.bookAuthor = bookAuthor;
			this.pageNo = pageNo;
			this.bookName = bookName;
			this.quantity = quantity;
			this.catagory = catagory;
			this.approvalStatus = approvalStatus;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public User getBookOwner() {
			return bookOwner;
		}

		public void setBookOwner(User bookOwner) {
			this.bookOwner = bookOwner;
		}

		public String getBookAuthor() {
			return bookAuthor;
		}

		public void setBookAuthor(String bookAuthor) {
			this.bookAuthor = bookAuthor;
		}

		public int getPageNo() {
			return pageNo;
		}

		public void setPageNo(int pageNo) {
			this.pageNo = pageNo;
		}

		public String getBookName() {
			return bookName;
		}

		public void setBookName(String bookName) {
			this.bookName = bookName;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		public String getCatagory() {
			return catagory;
		}

		public void setCatagory(String catagory) {
			this.catagory = catagory;
		}

		public Approval getApprovalStatus() {
			return approvalStatus;
		}

		public void setApprovalStatus(Approval approvalStatus) {
			this.approvalStatus = approvalStatus;
		}
	
		
		
		
		