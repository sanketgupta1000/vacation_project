package com.project.readers.readers_community.DTOs;

// represents the data that will be visible to the admin while viewing a book approval request
public class BookDTO
{

    private Long bookId;
    private String bookTitle;
    private String bookAuthor;
    private int bookPageCount;
    private int bookQuantity;
    private Long bookCategoryId;
    private String bookCategoryName;
    private String bookApprovalStatus;
    private Integer bookOwnerId;
    private String bookOwnerName;

    public BookDTO(Long bookId, String bookTitle, String bookAuthor, int bookPageCount, int bookQuantity, Long bookCategoryId, String bookCategoryName, String bookApprovalStatus, Integer bookOwnerId, String bookOwnerName) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookPageCount = bookPageCount;
        this.bookQuantity = bookQuantity;
        this.bookCategoryId = bookCategoryId;
        this.bookCategoryName = bookCategoryName;
        this.bookApprovalStatus = bookApprovalStatus;
        this.bookOwnerId = bookOwnerId;
        this.bookOwnerName = bookOwnerName;
    }

    public BookDTO(){}

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public int getBookPageCount() {
        return bookPageCount;
    }

    public void setBookPageCount(int bookPageCount) {
        this.bookPageCount = bookPageCount;
    }

    public int getBookQuantity() {
        return bookQuantity;
    }

    public void setBookQuantity(int bookQuantity) {
        this.bookQuantity = bookQuantity;
    }

    public Long getBookCategoryId() {
        return bookCategoryId;
    }

    public void setBookCategoryId(Long bookCategoryId) {
        this.bookCategoryId = bookCategoryId;
    }

    public String getBookCategoryName() {
        return bookCategoryName;
    }

    public void setBookCategoryName(String bookCategoryName) {
        this.bookCategoryName = bookCategoryName;
    }

    public String getBookApprovalStatus() {
        return bookApprovalStatus;
    }

    public void setBookApprovalStatus(String bookApprovalStatus) {
        this.bookApprovalStatus = bookApprovalStatus;
    }

    public Integer getBookOwnerId() {
        return bookOwnerId;
    }

    public void setBookOwnerId(Integer bookOwnerId) {
        this.bookOwnerId = bookOwnerId;
    }

    public String getBookOwnerName() {
        return bookOwnerName;
    }

    public void setBookOwnerName(String bookOwnerName) {
        this.bookOwnerName = bookOwnerName;
    }

    @Override
    public String toString() {
        return "BookApprovalRequestDTO{" +
                "bookId=" + bookId +
                ", bookTitle='" + bookTitle + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookPageCount=" + bookPageCount +
                ", bookQuantity=" + bookQuantity +
                ", bookCategoryId=" + bookCategoryId +
                ", bookCategoryName='" + bookCategoryName + '\'' +
                ", bookApprovalStatus='" + bookApprovalStatus + '\'' +
                ", bookOwnerId=" + bookOwnerId +
                ", bookOwnerName='" + bookOwnerName + '\'' +
                '}';
    }
}
