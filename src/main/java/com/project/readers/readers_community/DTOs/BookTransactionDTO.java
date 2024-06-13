package com.project.readers.readers_community.DTOs;

public class BookTransactionDTO
{
    private Long transactionId;
    private int bookCopyId;
    private Long bookId;
    private String bookTitle;
    private Integer bookGiverId;
    private String bookGiverFullName;
    private String bookGiverEmail;
    private Integer bookReceiverId;
    private String bookReceiverFullName;
    private String bookReceiverEmail;
    private String transactionDate;
    private String transactionTime;

    public BookTransactionDTO() {
    }

    public BookTransactionDTO(Long transactionId, int bookCopyId, Long bookId, String bookTitle, Integer bookGiverId, String bookGiverFullName, String bookGiverEmail, Integer bookReceiverId, String bookReceiverFullName, String bookReceiverEmail, String transactionDate, String transactionTime) {
        this.transactionId = transactionId;
        this.bookCopyId = bookCopyId;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookGiverId = bookGiverId;
        this.bookGiverFullName = bookGiverFullName;
        this.bookGiverEmail = bookGiverEmail;
        this.bookReceiverId = bookReceiverId;
        this.bookReceiverFullName = bookReceiverFullName;
        this.bookReceiverEmail = bookReceiverEmail;
        this.transactionDate = transactionDate;
        this.transactionTime = transactionTime;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public int getBookCopyId() {
        return bookCopyId;
    }

    public void setBookCopyId(int bookCopyId) {
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

    public Integer getBookGiverId() {
        return bookGiverId;
    }

    public void setBookGiverId(Integer bookGiverId) {
        this.bookGiverId = bookGiverId;
    }

    public String getBookGiverFullName() {
        return bookGiverFullName;
    }

    public void setBookGiverFullName(String bookGiverFullName) {
        this.bookGiverFullName = bookGiverFullName;
    }

    public String getBookGiverEmail() {
        return bookGiverEmail;
    }

    public void setBookGiverEmail(String bookGiverEmail) {
        this.bookGiverEmail = bookGiverEmail;
    }

    public Integer getBookReceiverId() {
        return bookReceiverId;
    }

    public void setBookReceiverId(Integer bookReceiverId) {
        this.bookReceiverId = bookReceiverId;
    }

    public String getBookReceiverFullName() {
        return bookReceiverFullName;
    }

    public void setBookReceiverFullName(String bookReceiverFullName) {
        this.bookReceiverFullName = bookReceiverFullName;
    }

    public String getBookReceiverEmail() {
        return bookReceiverEmail;
    }

    public void setBookReceiverEmail(String bookReceiverEmail) {
        this.bookReceiverEmail = bookReceiverEmail;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    @Override
    public String toString() {
        return "BookTransactionDTO{" +
                "transactionId=" + transactionId +
                ", bookCopyId=" + bookCopyId +
                ", bookId=" + bookId +
                ", bookTitle='" + bookTitle + '\'' +
                ", bookGiverId=" + bookGiverId +
                ", bookGiverFullName='" + bookGiverFullName + '\'' +
                ", bookGiverEmail='" + bookGiverEmail + '\'' +
                ", bookReceiverId=" + bookReceiverId +
                ", bookReceiverFullName='" + bookReceiverFullName + '\'' +
                ", bookReceiverEmail='" + bookReceiverEmail + '\'' +
                ", transactionDate='" + transactionDate + '\'' +
                ", transactionTime='" + transactionTime + '\'' +
                '}';
    }
}
