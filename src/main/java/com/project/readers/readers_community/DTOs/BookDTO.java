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
    private String coverPhotoURL;
    private String bookApprovalStatus;
    private Integer bookOwnerId;
    private String bookOwnerName;
    private String bookOwnerEmail;
    private String bookOwnerProfilePhotoURL;
    private String bookOwnerCity;
    private String bookRequestDate;
    private String bookRequestTime;
    private String bookUploadDate;
    private String bookUploadTime;

    public BookDTO(Long bookId, String bookTitle, String bookAuthor, int bookPageCount, int bookQuantity, Long bookCategoryId, String bookCategoryName, String coverPhotoURL, String bookApprovalStatus, Integer bookOwnerId, String bookOwnerName, String bookOwnerEmail, String bookOwnerProfilePhotoURL, String bookOwnerCity, String bookRequestDate, String bookRequestTime, String bookUploadDate, String bookUploadTime) {
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
        this.bookOwnerCity = bookOwnerCity;
        this.bookRequestDate = bookRequestDate;
        this.bookRequestTime = bookRequestTime;
        this.bookUploadDate = bookUploadDate;
        this.bookUploadTime = bookUploadTime;
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

    public String getBookOwnerCity() {
        return bookOwnerCity;
    }

    public void setBookOwnerCity(String bookOwnerCity) {
        this.bookOwnerCity = bookOwnerCity;
    }

    public String getBookRequestDate() {
        return bookRequestDate;
    }

    public void setBookRequestDate(String bookRequestDate) {
        this.bookRequestDate = bookRequestDate;
    }

    public String getBookRequestTime() {
        return bookRequestTime;
    }

    public void setBookRequestTime(String bookRequestTime) {
        this.bookRequestTime = bookRequestTime;
    }

    public String getBookUploadDate() {
        return bookUploadDate;
    }

    public void setBookUploadDate(String bookUploadDate) {
        this.bookUploadDate = bookUploadDate;
    }

    public String getBookUploadTime() {
        return bookUploadTime;
    }

    public void setBookUploadTime(String bookUploadTime) {
        this.bookUploadTime = bookUploadTime;
    }

    @Override
    public String toString() {
        return "BookDTO{" +
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
                ", bookOwnerCity='" + bookOwnerCity + '\'' +
                ", bookRequestDate='" + bookRequestDate + '\'' +
                ", bookRequestTime='" + bookRequestTime + '\'' +
                ", bookUploadDate='" + bookUploadDate + '\'' +
                ", bookUploadTime='" + bookUploadTime + '\'' +
                '}';
    }
}
