package com.project.readers.readers_community.entities;

import com.project.readers.readers_community.enums.Approval;
import jakarta.persistence.*;

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
    @OneToOne
    @JoinColumn(name = "member_id")
    private User member;

    // approval of the actual owner
    @Column(name = "owner_approval")
    private Approval ownerApproval;

    // no args constructor
    public BorrowRequest()
    {

    }

    // all args
    public BorrowRequest(Integer id, BookCopy bookCopy, User member, Approval ownerApproval) {
        this.id = id;
        this.bookCopy = bookCopy;
        this.member = member;
        this.ownerApproval = ownerApproval;
    }

    // getters and setters

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

    public User getMember() {
        return member;
    }

    public void setMember(User member) {
        this.member = member;
    }

    public Approval getOwnerApproval() {
        return ownerApproval;
    }

    public void setOwnerApproval(Approval ownerApproval) {
        this.ownerApproval = ownerApproval;
    }

    // to string

    @Override
    public String toString() {
        return "BorrowRequest{" +
                "id=" + id +
                ", bookCopy=" + bookCopy +
                ", member=" + member +
                ", ownerApproval=" + ownerApproval +
                '}';
    }
}
