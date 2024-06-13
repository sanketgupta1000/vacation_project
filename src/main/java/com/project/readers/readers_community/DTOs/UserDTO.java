package com.project.readers.readers_community.DTOs;

import java.util.Date;

import com.project.readers.readers_community.entities.User;

public class UserDTO {

	private int userId;
	private String email;
	private String fullName;
	  private String phoneNumber;
	  private String referrerName;
	  	//address field
	    private String houseNo;
	    private String street;
	    private String landmark;
	    private String city;
	    private String state;
	    private String country;
	  
	    private Date dateOfBirth;

		public int getUserId() {
			return userId;
		}

		public String getEmail() {
			return email;
		}

		public String getFullName() {
			return fullName;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public String getReferrerName() {
			return referrerName;
		}

		public String getHouseNo() {
			return houseNo;
		}

		public String getStreet() {
			return street;
		}

		public String getLandmark() {
			return landmark;
		}

		public String getCity() {
			return city;
		}

		public String getState() {
			return state;
		}

		public String getCountry() {
			return country;
		}

		public Date getDateOfBirth() {
			return dateOfBirth;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public void setFullName(String fullName) {
			this.fullName = fullName;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public void setReferrerName(String referrerName) {
			this.referrerName = referrerName;
		}

		public void setHouseNo(String houseNo) {
			this.houseNo = houseNo;
		}

		public void setStreet(String street) {
			this.street = street;
		}

		public void setLandmark(String landmark) {
			this.landmark = landmark;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public void setState(String state) {
			this.state = state;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public void setDateOfBirth(Date dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
		}

		public UserDTO(int userId, String email, String fullName, String phoneNumber, String referrerName,
				String houseNo, String street, String landmark, String city, String state, String country,
				Date dateOfBirth) {
			super();
			this.userId = userId;
			this.email = email;
			this.fullName = fullName;
			this.phoneNumber = phoneNumber;
			this.referrerName = referrerName;
			this.houseNo = houseNo;
			this.street = street;
			this.landmark = landmark;
			this.city = city;
			this.state = state;
			this.country = country;
			this.dateOfBirth = dateOfBirth;
		}
	    
	    
}
