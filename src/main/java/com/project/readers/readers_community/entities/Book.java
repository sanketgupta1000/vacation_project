package com.project.readers.readers_community.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.readers.readers_community.enums.Approval;

import jakarta.persistence.*;

import java.util.List;

//represents the information about a book, not the actual physical book
@Entity
@Table(name = "books")
public class Book  
{
	//auto generated id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	//title of the boook
	@Column(name = "book_title")
	private String bookTitle;

	//name of the author of the book
	@Column(name = "author_name")
	private String authorName;

	//how many pages does the book have
	@Column(name = "page_count")
	private int pageCount;

	//how many copies of this book does the owner have
	@Column(name = "quantity")
	private int quantity;

	// Genre of the book
	@ManyToOne
	@JoinColumn(name = "category_id")
	private BookCategory category;

	//is it approved or rejected by the admin
	@Column(name = "adminApproval")
	private Approval adminApproval;

	//user that owns the book
	@ManyToOne
	@JoinColumn(name = "owner_id")
	private User owner;

	//all physical copies of this book
	@OneToMany(mappedBy = "book")
	@JsonIgnore
	private List<BookCopy> bookCopies;

	public Book() {
	}

	public Book(Long id, String bookTitle, String authorName, int pageCount, int quantity, BookCategory category, Approval adminApproval, User owner) {
		this.id = id;
		this.bookTitle = bookTitle;
		this.authorName = authorName;
		this.pageCount = pageCount;
		this.quantity = quantity;
		this.category = category;
		this.adminApproval = adminApproval;
		this.owner = owner;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BookCategory getCategory() {
		return category;
	}

	public void setCategory(BookCategory category) {
		this.category = category;
	}

	public Approval getAdminApproval() {
		return adminApproval;
	}

	public void setAdminApproval(Approval adminApproval) {
		this.adminApproval = adminApproval;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<BookCopy> getBookCopies() {
		return bookCopies;
	}

	public void setBookCopies(List<BookCopy> bookCopies) {
		this.bookCopies = bookCopies;
	}

	@Override
	public String toString() {
		return "Book{" +
				"id=" + id +
				", bookTitle='" + bookTitle + '\'' +
				", authorName='" + authorName + '\'' +
				", pageCount=" + pageCount +
				", quantity=" + quantity +
				", category=" + category +
				", adminApproval=" + adminApproval +
				", owner=" + owner +
				'}';
	}
	
}
		
		
		
		