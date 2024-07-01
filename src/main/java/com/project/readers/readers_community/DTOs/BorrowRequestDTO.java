package com.project.readers.readers_community.DTOs;

public class BorrowRequestDTO
{

    private Integer borrowRequestId;
    private Integer bookCopyId;
    private String bookCopyName;
    private String coverPhotoURL;
    private Integer requesterId;
    private String requesterName;
    private String requesterEmail;
    private String requesterProfilePhotoURL;
    private String requestDate;
    private String requestTime;
    private String responseDate;
    private String responseTime;
    private String receiveDate;
    private String receiveTime;
    private String returnDate;
    private String returnTime;
    private String status;

    public BorrowRequestDTO(Integer borrowRequestId, Integer bookCopyId, String bookCopyName, String coverPhotoURL, Integer requesterId, String requesterName, String requesterEmail, String requesterProfilePhotoURL, String requestDate, String requestTime, String responseDate, String responseTime, String receiveDate, String receiveTime, String returnDate, String returnTime, String status) {
        this.borrowRequestId = borrowRequestId;
        this.bookCopyId = bookCopyId;
        this.bookCopyName = bookCopyName;
        this.coverPhotoURL = coverPhotoURL;
        this.requesterId = requesterId;
        this.requesterName = requesterName;
        this.requesterEmail = requesterEmail;
        this.requesterProfilePhotoURL = requesterProfilePhotoURL;
        this.requestDate = requestDate;
        this.requestTime = requestTime;
        this.responseDate = responseDate;
        this.responseTime = responseTime;
        this.receiveDate = receiveDate;
        this.receiveTime = receiveTime;
        this.returnDate = returnDate;
        this.returnTime = returnTime;
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

    public String getCoverPhotoURL() {
        return coverPhotoURL;
    }

    public void setCoverPhotoURL(String coverPhotoURL) {
        this.coverPhotoURL = coverPhotoURL;
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

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(String responseDate) {
        this.responseDate = responseDate;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    public String getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
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
                ", coverPhotoURL='" + coverPhotoURL + '\'' +
                ", requesterId=" + requesterId +
                ", requesterName='" + requesterName + '\'' +
                ", requesterEmail='" + requesterEmail + '\'' +
                ", requesterProfilePhotoURL='" + requesterProfilePhotoURL + '\'' +
                ", requestDate='" + requestDate + '\'' +
                ", requestTime='" + requestTime + '\'' +
                ", responseDate='" + responseDate + '\'' +
                ", responseTime='" + responseTime + '\'' +
                ", receiveDate='" + receiveDate + '\'' +
                ", receiveTime='" + receiveTime + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", returnTime='" + returnTime + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
