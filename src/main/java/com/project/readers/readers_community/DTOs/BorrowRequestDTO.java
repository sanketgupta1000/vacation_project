package com.project.readers.readers_community.DTOs;

public class BorrowRequestDTO
{

    private Integer borrowRequestId;
    private Integer bookCopyId;
    private String bookCopyName;
    private Integer requesterId;
    private String requesterName;
    private String requesterEmail;
    private String requesterProfilePhotoURL;
    private String status;

    public BorrowRequestDTO(Integer borrowRequestId, Integer bookCopyId, String bookCopyName, Integer requesterId, String requesterName, String requesterEmail, String requesterProfilePhotoURL, String status) {
        this.borrowRequestId = borrowRequestId;
        this.bookCopyId = bookCopyId;
        this.bookCopyName = bookCopyName;
        this.requesterId = requesterId;
        this.requesterName = requesterName;
        this.requesterEmail = requesterEmail;
        this.requesterProfilePhotoURL = requesterProfilePhotoURL;
        this.status = status;
    }

    public Integer getBorrowRequestId() {
        return borrowRequestId;
    }

    public void setBorrowRequestId(Integer borrowRequestId) {
        this.borrowRequestId = borrowRequestId;
    }

    public Integer getBookCopyId() {
        return bookCopyId;
    }

    public void setBookCopyId(Integer bookCopyId) {
        this.bookCopyId = bookCopyId;
    }

    public String getBookCopyName() {
        return bookCopyName;
    }

    public void setBookCopyName(String bookCopyName) {
        this.bookCopyName = bookCopyName;
    }

    public Integer getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(Integer requesterId) {
        this.requesterId = requesterId;
    }

    public String getRequesterName() {
        return requesterName;
    }

    public void setRequesterName(String requesterName) {
        this.requesterName = requesterName;
    }

    public String getRequesterEmail() {
        return requesterEmail;
    }

    public void setRequesterEmail(String requesterEmail) {
        this.requesterEmail = requesterEmail;
    }

    public String getRequesterProfilePhotoURL() {
        return requesterProfilePhotoURL;
    }

    public void setRequesterProfilePhotoURL(String requesterProfilePhotoURL) {
        this.requesterProfilePhotoURL = requesterProfilePhotoURL;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BorrowRequestDTO{" +
                "borrowRequestId=" + borrowRequestId +
                ", bookCopyId=" + bookCopyId +
                ", bookCopyName='" + bookCopyName + '\'' +
                ", requesterId=" + requesterId +
                ", requesterName='" + requesterName + '\'' +
                ", requesterEmail='" + requesterEmail + '\'' +
                ", requesterProfilePhotoURL='" + requesterProfilePhotoURL + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
