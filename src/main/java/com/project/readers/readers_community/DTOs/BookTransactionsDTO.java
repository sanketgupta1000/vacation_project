package com.project.readers.readers_community.DTOs;

import java.util.List;

// to send book copy's data as well as the list of transactions
public class BookTransactionsDTO
{

    private Integer bookCopyId;
    private Long bookId;
    private String bookTitle;
    private String coverPhotoURL;
    private Integer holderId;
    private String holderName;
    private String holderEmail;
    private String holderProfilePhotoURL;
    private Integer borrowerId;
    private String borrowerName;
    private String borrowerEmail;
    private String borrowerProfilePhotoURL;
    private boolean requestable;
    private boolean canHandover;
    private List<BookTransactionDTO> bookCopyTransactions;

    public BookTransactionsDTO(Integer bookCopyId, Long bookId, String bookTitle, String coverPhotoURL, Integer holderId, String holderName, String holderEmail, String holderProfilePhotoURL, Integer borrowerId, String borrowerName, String borrowerEmail, String borrowerProfilePhotoURL, boolean requestable, boolean canHandover, List<BookTransactionDTO> bookCopyTransactions) {
        this.bookCopyId = bookCopyId;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.coverPhotoURL = coverPhotoURL;
        this.holderId = holderId;
        this.holderName = holderName;
        this.holderEmail = holderEmail;
        this.holderProfilePhotoURL = holderProfilePhotoURL;
        this.borrowerId = borrowerId;
        this.borrowerName = borrowerName;
        this.borrowerEmail = borrowerEmail;
        this.borrowerProfilePhotoURL = borrowerProfilePhotoURL;
        this.requestable = requestable;
        this.canHandover = canHandover;
        this.bookCopyTransactions = bookCopyTransactions;
    }

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

    public String getCoverPhotoURL() {
        return coverPhotoURL;
    }

    public void setCoverPhotoURL(String coverPhotoURL) {
        this.coverPhotoURL = coverPhotoURL;
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

    public String getHolderEmail() {
        return holderEmail;
    }

    public void setHolderEmail(String holderEmail) {
        this.holderEmail = holderEmail;
    }

    public String getHolderProfilePhotoURL() {
        return holderProfilePhotoURL;
    }

    public void setHolderProfilePhotoURL(String holderProfilePhotoURL) {
        this.holderProfilePhotoURL = holderProfilePhotoURL;
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

    public String getBorrowerEmail() {
        return borrowerEmail;
    }

    public void setBorrowerEmail(String borrowerEmail) {
        this.borrowerEmail = borrowerEmail;
    }

    public String getBorrowerProfilePhotoURL() {
        return borrowerProfilePhotoURL;
    }

    public void setBorrowerProfilePhotoURL(String borrowerProfilePhotoURL) {
        this.borrowerProfilePhotoURL = borrowerProfilePhotoURL;
    }

    public boolean isRequestable() {
        return requestable;
    }

    public void setRequestable(boolean requestable) {
        this.requestable = requestable;
    }

    public boolean isCanHandover() {
        return canHandover;
    }

    public void setCanHandover(boolean canHandover) {
        this.canHandover = canHandover;
    }

    public List<BookTransactionDTO> getBookCopyTransactions() {
        return bookCopyTransactions;
    }

    public void setBookCopyTransactions(List<BookTransactionDTO> bookCopyTransactions) {
        this.bookCopyTransactions = bookCopyTransactions;
    }

    @Override
    public String toString() {
        return "BookTransactionsDTO{" +
                "bookCopyId=" + bookCopyId +
                ", bookId=" + bookId +
                ", bookTitle='" + bookTitle + '\'' +
                ", coverPhotoURL='" + coverPhotoURL + '\'' +
                ", holderId=" + holderId +
                ", holderName='" + holderName + '\'' +
                ", holderEmail='" + holderEmail + '\'' +
                ", holderProfilePhotoURL='" + holderProfilePhotoURL + '\'' +
                ", borrowerId=" + borrowerId +
                ", borrowerName='" + borrowerName + '\'' +
                ", borrowerEmail='" + borrowerEmail + '\'' +
                ", borrowerProfilePhotoURL='" + borrowerProfilePhotoURL + '\'' +
                ", requestable=" + requestable +
                ", canHandover=" + canHandover +
                ", bookCopyTransactions=" + bookCopyTransactions +
                '}';
    }
}
