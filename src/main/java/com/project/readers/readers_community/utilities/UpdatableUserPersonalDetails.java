package com.project.readers.readers_community.utilities;

import com.project.readers.readers_community.embeddables.Address;

import java.util.Date;

public class UpdatableUserPersonalDetails
{
    private String fullName;
    private String phoneNumber;
    private Address address;

    public UpdatableUserPersonalDetails() {
    }

    public UpdatableUserPersonalDetails(String fullName, String phoneNumber, Address address) {
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.address = address;
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

    @Override
    public String toString() {
        return "UpdatableUserPersonalDetails{" +
                "fullName='" + fullName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address=" + address +
                '}';
    }
}
