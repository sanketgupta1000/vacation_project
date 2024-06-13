package com.project.readers.readers_community.entities;

import com.project.readers.readers_community.embeddables.Address;
import com.project.readers.readers_community.enums.UserType;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="users")
public class User {
    // auto generated id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // email
    @Column(name = "email")
    private String email;

    // password
    @Column(name = "password")
    private String password;

    // full name
    @Column(name = "full_name")
    private String fullName;

    // role or user_type
    @Column(name = "user_type")
    private UserType userType;

    // phone number
    @Column(name = "phone_number")
    private String phoneNumber;

    // person who referred this person
    @ManyToOne
    @JoinColumn(name = "referrer_id")
    private User referrer;

    // otp for verification, since REST, need to store in DB
    @Column(name = "otp")
    private String otp;

    // is otp verified
    @Column(name = "is_otp_verified")
    private boolean isOtpVerified;

    // approval request
    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private MemberApprovalRequest memberApprovalRequest;

    // embedded address
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "houseNo", column = @Column(name = "house_no")),
            @AttributeOverride(name = "street", column = @Column(name = "street")),
            @AttributeOverride(name = "landmark", column = @Column(name = "landmark")),
            @AttributeOverride(name = "city", column = @Column(name = "city")),
            @AttributeOverride(name = "state", column = @Column(name = "state")),
            @AttributeOverride(name = "country", column = @Column(name = "country")),
    })
    private Address address;

    // date of birth
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    //books uploaded by user
    @OneToMany(mappedBy = "owner")
    private List<Book> uploadedBooks;

    //book copy currently borrowed by user
    @OneToMany(mappedBy = "holder")
    private List<BookCopy> borrowedBookCopies;

    //book copy that will be borrowed by user next
    @OneToMany(mappedBy = "borrower")
    private List<BookCopy> nextBorrowBookCopies;

    //transaction in which this user acted as book giver
    @OneToMany(mappedBy = "bookGiver")
    private List<BookTransaction> bookGivingTransactions;

    //transaction in which this user acted as book receiver
    @OneToMany(mappedBy = "bookReceiver")
    private List<BookTransaction> bookReceivingTransactions;

    //this user's current borrow request
    @OneToOne(mappedBy = "requester")
    private BorrowRequest currentBorrowRequest;

    // TODO: add profile photo too

    // constructor
    public User(User referrer, String phoneNumber, UserType userType, String fullName, String password, String email, Integer id, String otp, MemberApprovalRequest memberApprovalRequest, boolean isOtpVerified) {
        this.referrer = referrer;
        this.phoneNumber = phoneNumber;
        this.userType = userType;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.id = id;
        this.otp = otp;
        this.memberApprovalRequest = memberApprovalRequest;
        this.isOtpVerified = isOtpVerified;
    }

    // no args
    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public User getReferrer() {
        return referrer;
    }

    public void setReferrer(User referrer) {
        this.referrer = referrer;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public MemberApprovalRequest getMemberApprovalRequest() {
        return memberApprovalRequest;
    }

    public void setMemberApprovalRequest(MemberApprovalRequest memberApprovalRequest) {
        this.memberApprovalRequest = memberApprovalRequest;
    }

    public boolean isOtpVerified() {
        return isOtpVerified;
    }

    public void setOtpVerified(boolean isOtpVerified) {
        this.isOtpVerified = isOtpVerified;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Book> getUploadedBooks() {
        return uploadedBooks;
    }

    public void setUploadedBooks(List<Book> uploadedBooks) {
        this.uploadedBooks = uploadedBooks;
    }

    public List<BookCopy> getBorrowedBookCopies() {
        return borrowedBookCopies;
    }

    public void setBorrowedBookCopies(List<BookCopy> borrowedBookCopies) {
        this.borrowedBookCopies = borrowedBookCopies;
    }

    public List<BookCopy> getNextBorrowBookCopies() {
        return nextBorrowBookCopies;
    }

    public void setNextBorrowBookCopies(List<BookCopy> nextBorrowBookCopies) {
        this.nextBorrowBookCopies = nextBorrowBookCopies;
    }

    public List<BookTransaction> getBookGivingTransactions() {
        return bookGivingTransactions;
    }

    public void setBookGivingTransactions(List<BookTransaction> bookGivingTransactions) {
        this.bookGivingTransactions = bookGivingTransactions;
    }

    public List<BookTransaction> getBookReceivingTransactions() {
        return bookReceivingTransactions;
    }

    public void setBookReceivingTransactions(List<BookTransaction> bookReceivingTransactions) {
        this.bookReceivingTransactions = bookReceivingTransactions;
    }

    public BorrowRequest getCurrentBorrowRequest() {
        return currentBorrowRequest;
    }

    public void setCurrentBorrowRequest(BorrowRequest currentBorrowRequest) {
        this.currentBorrowRequest = currentBorrowRequest;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                ", userType=" + userType +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", referrer=" + referrer +
                ", otp='" + otp +
                ", memberApprovalRequest=" + memberApprovalRequest +
                ", isOtpVerified=" + isOtpVerified +
                ", address=" + address +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }
}
