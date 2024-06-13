package com.project.readers.readers_community.DTOs;

public class BookCopyDTO
{

    private Integer bookCopyId;
    private Long bookId;
    private String bookTitle;
    private Integer holderId;
    private String holderName;
    private Integer borrowerId;
    private String borrowerName;
    private boolean requestable;

    public BookCopyDTO(Integer bookCopyId, Long bookId, String bookTitle, Integer holderId, String holderName, Integer borrowerId, String borrowerName, boolean requestable) {
        this.bookCopyId = bookCopyId;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.holderId = holderId;
        this.holderName = holderName;
        this.borrowerId = borrowerId;
        this.borrowerName = borrowerName;
        this.requestable = requestable;
    }

    public BookCopyDTO() {}

    public Integer getBookCopyId() {
        return bookCopyId;
    }

    public void setBookCopyId(Integer bookCopyId) {
        this.bookCopyId = bookCopyId;
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

    public Integer getHolderId() {
        return holderId;
    }

    public void setHolderId(Integer holderId) {
        this.holderId = holderId;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public Integer getBorrowerId() {
        return borrowerId;
    }

    public void setBorrowerId(Integer borrowerId) {
        this.borrowerId = borrowerId;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public boolean getRequestable() {
        return requestable;
    }

    public void setRequestable(boolean requestable) {
        this.requestable = requestable;
    }

    @Override
    public String toString() {
        return "BookCopyDTO{" +
                "bookCopyId=" + bookCopyId +
                ", bookId=" + bookId +
                ", bookTitle='" + bookTitle + '\'' +
                ", holderId=" + holderId +
                ", holderName='" + holderName + '\'' +
                ", borrowerId='" + borrowerId + '\'' +
                ", borrowerName='" + borrowerName + '\'' +
                ", requestable='" + requestable + '\'' +
                '}';
    }
}
