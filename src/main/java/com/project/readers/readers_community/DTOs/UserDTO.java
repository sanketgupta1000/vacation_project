package com.project.readers.readers_community.DTOs;

import java.util.Date;

import com.project.readers.readers_community.entities.User;
import com.project.readers.readers_community.enums.UserType;

public class UserDTO {

    private int userId;
    private String email;
    private String fullName;
    private String phoneNumber;
    private UserType userType;
    private String dateOfBirth;
    private String profilePhotoURL;
    private String houseNo;
    private String street;
    private String landmark;
    private String city;
    private String state;
    private String country;
    private int referrerId;
    private String referrerName;
    private  String referrerEmail;
    private  String referrerProfilePhotoURL;

    public UserDTO(int userId, String email, String fullName, String phoneNumber, UserType userType, String dateOfBirth, String profilePhotoURL, String houseNo, String street, String landmark, String city, String state, String country, int referrerId, String referrerName, String referrerEmail, String referrerProfilePhotoURL) {
        this.userId = userId;
        this.email = email;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.userType = userType;
        this.dateOfBirth = dateOfBirth;
        this.profilePhotoURL = profilePhotoURL;
        this.houseNo = houseNo;
        this.street = street;
        this.landmark = landmark;
        this.city = city;
        this.state = state;
        this.country = country;
        this.referrerId = referrerId;
        this.referrerName = referrerName;
        this.referrerEmail = referrerEmail;
        this.referrerProfilePhotoURL = referrerProfilePhotoURL;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getProfilePhotoURL() {
        return profilePhotoURL;
    }

    public void setProfilePhotoURL(String profilePhotoURL) {
        this.profilePhotoURL = profilePhotoURL;
    }

    public String getHouseNo() {
        return houseNo;
    }

    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getReferrerId() {
        return referrerId;
    }

    public void setReferrerId(int referrerId) {
        this.referrerId = referrerId;
    }

    public String getReferrerName() {
        return referrerName;
    }

    public void setReferrerName(String referrerName) {
        this.referrerName = referrerName;
    }

    public String getReferrerEmail() {
        return referrerEmail;
    }

    public void setReferrerEmail(String referrerEmail) {
        this.referrerEmail = referrerEmail;
    }

    public String getReferrerProfilePhotoURL() {
        return referrerProfilePhotoURL;
    }

    public void setReferrerProfilePhotoURL(String referrerProfilePhotoURL) {
        this.referrerProfilePhotoURL = referrerProfilePhotoURL;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", userType=" + userType +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", profilePhotoURL='" + profilePhotoURL + '\'' +
                ", houseNo='" + houseNo + '\'' +
                ", street='" + street + '\'' +
                ", landmark='" + landmark + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", referrerId=" + referrerId +
                ", referrerName='" + referrerName + '\'' +
                ", referrerEmail='" + referrerEmail + '\'' +
                ", referrerProfilePhotoURL='" + referrerProfilePhotoURL + '\'' +
                '}';
    }
}
