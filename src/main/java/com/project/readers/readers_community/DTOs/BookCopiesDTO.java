package com.project.readers.readers_community.DTOs;
import java.util.List;

// to send the book data as well as all book copies data
public class BookCopiesDTO {

    private Long bookId;
    private String bookTitle;
    private String bookAuthor;
    private int bookPageCount;
    private int bookQuantity;
    private Long bookCategoryId;
    private String bookCategoryName;
    private String coverPhotoURL;
    private String bookApprovalStatus;
    private Integer bookOwnerId;
    private String bookOwnerName;
    private String bookOwnerEmail;
    private String bookOwnerProfilePhotoURL;
    private List<BookCopyDTO> bookCopies;

    // all args constructor

    public BookCopiesDTO(Long bookId, String bookTitle, String bookAuthor, int bookPageCount, int bookQuantity, Long bookCategoryId, String bookCategoryName, String coverPhotoURL, String bookApprovalStatus, Integer bookOwnerId, String bookOwnerName, String bookOwnerEmail, String bookOwnerProfilePhotoURL, List<BookCopyDTO> bookCopies) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookPageCount = bookPageCount;
        this.bookQuantity = bookQuantity;
        this.bookCategoryId = bookCategoryId;
        this.bookCategoryName = bookCategoryName;
        this.coverPhotoURL = coverPhotoURL;
        this.bookApprovalStatus = bookApprovalStatus;
        this.bookOwnerId = bookOwnerId;
        this.bookOwnerName = bookOwnerName;
        this.bookOwnerEmail = bookOwnerEmail;
        this.bookOwnerProfilePhotoURL = bookOwnerProfilePhotoURL;
        this.bookCopies = bookCopies;
    }

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

    public String getCoverPhotoURL() {
        return coverPhotoURL;
    }

    public void setCoverPhotoURL(String coverPhotoURL) {
        this.coverPhotoURL = coverPhotoURL;
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

    public String getBookOwnerEmail() {
        return bookOwnerEmail;
    }

    public void setBookOwnerEmail(String bookOwnerEmail) {
        this.bookOwnerEmail = bookOwnerEmail;
    }

    public String getBookOwnerProfilePhotoURL() {
        return bookOwnerProfilePhotoURL;
    }

    public void setBookOwnerProfilePhotoURL(String bookOwnerProfilePhotoURL) {
        this.bookOwnerProfilePhotoURL = bookOwnerProfilePhotoURL;
    }

    public List<BookCopyDTO> getBookCopies() {
        return bookCopies;
    }

    public void setBookCopies(List<BookCopyDTO> bookCopies) {
        this.bookCopies = bookCopies;
    }

    @Override
    public String toString() {
        return "BookCopiesDTO{" +
                "bookId=" + bookId +
                ", bookTitle='" + bookTitle + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookPageCount=" + bookPageCount +
                ", bookQuantity=" + bookQuantity +
                ", bookCategoryId=" + bookCategoryId +
                ", bookCategoryName='" + bookCategoryName + '\'' +
                ", coverPhotoURL='" + coverPhotoURL + '\'' +
                ", bookApprovalStatus='" + bookApprovalStatus + '\'' +
                ", bookOwnerId=" + bookOwnerId +
                ", bookOwnerName='" + bookOwnerName + '\'' +
                ", bookOwnerEmail='" + bookOwnerEmail + '\'' +
                ", bookOwnerProfilePhotoURL='" + bookOwnerProfilePhotoURL + '\'' +
                ", bookCopies=" + bookCopies +
                '}';
    }
}
