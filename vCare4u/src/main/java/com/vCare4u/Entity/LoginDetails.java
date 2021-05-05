package com.vCare4u.Entity;

public class LoginDetails {
	private String email;
	private String password;
	public LoginDetails() {
		super();
	}
	
	public LoginDetails(String email, String password) {
		super();
		this.email = email;
		this.password = password;
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
	
}
