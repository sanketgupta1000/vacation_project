package com.project.readers.readers_community.DTOs;

public class BorrowRequestDTO
{

    private Integer borrowRequestId;
    private Integer bookCopyId;
    private String bookCopyName;
    private Integer requesterId;
    private String requesterName;
    private String approvalStatus;
    private boolean approvable;

    public BorrowRequestDTO(Integer borrowRequestId, Integer bookCopyId, String bookCopyName, Integer requesterId, String requesterName, String approvalStatus, boolean approvable) {
        this.borrowRequestId = borrowRequestId;
        this.bookCopyId = bookCopyId;
        this.bookCopyName = bookCopyName;
        this.requesterId = requesterId;
        this.requesterName = requesterName;
        this.approvalStatus = approvalStatus;
        this.approvable = approvable;
    }

    public BorrowRequestDTO(){}

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

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public boolean isApprovable() {
        return approvable;
    }

    public void setApprovable(boolean approvable) {
        this.approvable = approvable;
    }

    @Override
    public String toString() {
        return "BorrowRequestDTO{" +
                "borrowRequestId=" + borrowRequestId +
                ", bookCopyId=" + bookCopyId +
                ", bookCopyName='" + bookCopyName + '\'' +
                ", requesterId=" + requesterId +
                ", requesterName='" + requesterName + '\'' +
                ", approvalStatus='" + approvalStatus + '\'' +
                ", approvable=" + approvable +
                '}';
    }
}
