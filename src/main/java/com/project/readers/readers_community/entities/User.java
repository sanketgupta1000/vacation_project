package com.project.readers.readers_community.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.project.readers.readers_community.embeddables.Address;
import com.project.readers.readers_community.enums.UserType;
import jakarta.persistence.*;

import java.util.Date;

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

    // is otp verified
    @Column(name = "is_otp_verified")
    private boolean isOtpVerified;

    // approval request
    @OneToOne(mappedBy = "member", cascade = CascadeType.ALL)
    private MemberApprovalRequest memberApprovalRequest;

    // embedded address
    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "houseNo", column = @Column(name = "house_no")),
            @AttributeOverride( name = "street", column = @Column(name = "street")),
            @AttributeOverride( name = "landmark", column = @Column(name = "landmark")),
            @AttributeOverride( name = "city", column = @Column(name = "city")),
            @AttributeOverride( name = "state", column = @Column(name = "state")),
            @AttributeOverride( name = "country", column = @Column(name = "country")),
    })
    private Address address;

    // date of birth
    @Temporal(TemporalType.DATE)
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

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
}
