package com.project.readers.readers_community.entities;

import com.project.readers.readers_community.enums.Approval;
import com.project.readers.readers_community.enums.BorrowRequestStatus;
import jakarta.persistence.*;

import java.util.Date;

// represents the request to borrow a physical book
@Entity
@Table(name = "borrow_requests")
public class BorrowRequest
{

    // id of the request
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // the physical book to borrow
    @ManyToOne
    @JoinColumn(name = "book_copy_id")
    private BookCopy bookCopy;

    // the member who has requested
    @ManyToOne
    @JoinColumn(name = "requester_id")
    private User requester;

    // status of the borrow request
    @Column(name = "status")
    private BorrowRequestStatus status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "request_date_time")
    private Date requestDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "response_date_time")
    private Date responseDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "receive_date_time")
    private Date receiveDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "return_date_time")
    private Date returnDateTime;

    public BorrowRequest() {
    }

    public BorrowRequest(Integer id, BookCopy bookCopy, User requester, BorrowRequestStatus status, Date requestDateTime, Date responseDateTime, Date receiveDateTime, Date returnDateTime) {
        this.id = id;
        this.bookCopy = bookCopy;
        this.requester = requester;
        this.status = status;
        this.requestDateTime = requestDateTime;
        this.responseDateTime = responseDateTime;
        this.receiveDateTime = receiveDateTime;
        this.returnDateTime = returnDateTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public void setBookCopy(BookCopy bookCopy) {
        this.bookCopy = bookCopy;
    }

    public User getRequester() {
        return requester;
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }

    public BorrowRequestStatus getStatus() {
        return status;
    }

    public void setStatus(BorrowRequestStatus status) {
        this.status = status;
    }

    public Date getRequestDateTime() {
        return requestDateTime;
    }

    public void setRequestDateTime(Date requestDateTime) {
        this.requestDateTime = requestDateTime;
    }

    public Date getResponseDateTime() {
        return responseDateTime;
    }

    public void setResponseDateTime(Date responseDateTime) {
        this.responseDateTime = responseDateTime;
    }

    public Date getReceiveDateTime() {
        return receiveDateTime;
    }

    public void setReceiveDateTime(Date receiveDateTime) {
        this.receiveDateTime = receiveDateTime;
    }

    public Date getReturnDateTime() {
        return returnDateTime;
    }

    public void setReturnDateTime(Date returnDateTime) {
        this.returnDateTime = returnDateTime;
    }

    @Override
    public String toString() {
        return "BorrowRequest{" +
                "id=" + id +
                ", bookCopy=" + bookCopy +
                ", requester=" + requester +
                ", status=" + status +
                ", requestDateTime=" + requestDateTime +
                ", responseDateTime=" + responseDateTime +
                ", receiveDateTime=" + receiveDateTime +
                ", returnDateTime=" + returnDateTime +
                '}';
    }
}
