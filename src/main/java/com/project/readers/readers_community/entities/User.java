package com.project.readers.readers_community.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.readers.readers_community.enums.UserType;
import jakarta.persistence.*;

@Entity
@Table(name="users")
public class User
{
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
    @Column(name="user_type")
    private UserType userType;

    // phone number
    @Column(name = "phone_number")
    private String phoneNumber;

    // person who referred this person
    @ManyToOne
    @JoinColumn(name = "referrer_id")
    @JsonIgnoreProperties("referrer")
    private User referrer;

    // otp for verification, since REST, need to store in DB
    @Column(name = "otp")
    private String otp;

    // approval request
    @OneToOne(mappedBy = "member")
    @Column(name = "request_id")
    private MemberApprovalRequest memberApprovalRequest;

    // constructor
    public User(User referrer, String phoneNumber, UserType userType, String fullName, String password, String email, Integer id, String otp, MemberApprovalRequest memberApprovalRequest) {
        this.referrer = referrer;
        this.phoneNumber = phoneNumber;
        this.userType = userType;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.id = id;
        this.otp = otp;
        this.memberApprovalRequest = memberApprovalRequest;
    }

    // no args
    public User() {}

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
                '}';
    }
}