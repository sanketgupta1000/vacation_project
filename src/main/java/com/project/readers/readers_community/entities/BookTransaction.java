package com.project.readers.readers_community.entities;

import jakarta.persistence.*;

import java.util.Date;

// represents the transaction of a book copy that took place between two users. Can be used to track the history of each book copy.
@Entity
@Table(name="book_transactions")
public class BookTransaction {
    //auto generated Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //book that was part of this transaction
    @ManyToOne
    @JoinColumn(name = "book_copy_id")
    private BookCopy bookCopy;

    //user who gave the book
    @ManyToOne
    @JoinColumn(name = "book_giver_id")
    private User bookGiver;

    //user who received the book
    @ManyToOne
    @JoinColumn(name = "book_receiver_id")
    private User bookReceiver;

    //Date and time on which this transaction took place
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "transaction_date_time")
    private Date transactionDateTime;

    //constructors
    public BookTransaction() {
    }

    public BookTransaction(Long id, BookCopy bookCopy, User bookGiver, User bookReceiver, Date transactionDateTime) {
        this.id = id;
        this.bookCopy = bookCopy;
        this.bookGiver = bookGiver;
        this.bookReceiver = bookReceiver;
        this.transactionDateTime = transactionDateTime;
    }

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public void setBookCopy(BookCopy bookCopy) {
        this.bookCopy = bookCopy;
    }

    public User getBookGiver() {
        return bookGiver;
    }

    public void setBookGiver(User bookGiver) {
        this.bookGiver = bookGiver;
    }

    public User getBookReceiver() {
        return bookReceiver;
    }

    public void setBookReceiver(User bookReceiver) {
        this.bookReceiver = bookReceiver;
    }

    public Date getTransactionDateTime() {
        return transactionDateTime;
    }

    public void setTransactionDateTime(Date transactionDateTime) {
        this.transactionDateTime = transactionDateTime;
    }

    @Override
    public String toString() {
        return "BookTransaction{" +
                "id=" + id +
                ", bookCopy=" + bookCopy +
                ", bookGiver=" + bookGiver +
                ", bookReceiver=" + bookReceiver +
                ", transactionDateTime=" + transactionDateTime +
                '}';
    }
}
