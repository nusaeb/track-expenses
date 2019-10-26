package com.trackexpenses.userexpense.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserInfo {
	
	@Id
	private String userId;
	private String firstName;
	private String lastName;
	private String email;
	private String mobileNumber;
	
	public UserInfo() {
		
	}
	
	public UserInfo(String userId, String firstName, String lastName, String email, String mobileNumber) {
		
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobileNumber = mobileNumber;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	
}
