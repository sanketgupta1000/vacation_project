package com.project.readers.readers_community.utilities;

import com.project.readers.readers_community.embeddables.Address;

import java.util.Date;

public class UpdatableUserPersonalDetails
{
    private String fullName;
    private String phoneNumber;
    private Address address;
    private Date dateOfBirth;

    public UpdatableUserPersonalDetails() {
    }

    public UpdatableUserPersonalDetails(String fullName, String phoneNumber, Address address, Date dateOfBirth) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
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
        return "UpdatableUserPersonalDetails{" +
                "fullName='" + fullName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address=" + address +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
