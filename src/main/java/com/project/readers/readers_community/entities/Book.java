package com.project.readers.readers_community.entities;

import jakarta.persistence.*;

@Entity
public class Book {

	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		
		
		@ManyToOne
		private User book_owner;
		private String book_author;
		private byte[] book_image;
		private int page_no;
		private String book_name;
		private int quantity;
		private String catagory;
		private boolean approval_status;
	
		
		
		
		public Book(Long id, User book_owner, String book_author, byte[] book_image, int page_no, String book_name,
				int quantity, String catagory,boolean approval_status) {
			super();
			this.id = id;
			this.book_owner = book_owner;
			this.book_author = book_author;
			this.book_image = book_image;
			this.page_no = page_no;
			this.book_name = book_name;
			this.quantity = quantity;
			this.catagory = catagory;
			this.approval_status=false;
		}

	public Book() {

	}

	public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public User getBook_owner() {
			return book_owner;
		}
		public void setBook_owner(User book_owner) {
			this.book_owner = book_owner;
		}
		public String getBook_author() {
			return book_author;
		}
		public void setBook_author(String book_author) {
			this.book_author = book_author;
		}
		public byte[] getBook_image() {
			return book_image;
		}
		public void setBook_image(byte[] book_image) {
			this.book_image = book_image;
		}
		public int getPage_no() {
			return page_no;
		}
		public void setPage_no(int page_no) {
			this.page_no = page_no;
		}
		public String getBook_name() {
			return book_name;
		}
		public void setBook_name(String book_name) {
			this.book_name = book_name;
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
		public boolean isApproval_status() {
			return approval_status;
		}
		public void setApproval_status(boolean approval_status) {
			this.approval_status = approval_status;
		}
		
}
