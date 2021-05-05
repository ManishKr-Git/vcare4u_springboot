package com.vCare4u.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Expert {
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String name;
	private String email;
	private String phone;
	private String aadhar;
	private String password;
	private String expertization;
	private String specification;
	private String description;
	private String imageUrl;
	public String getExpertization() {
		return expertization;
	}

	public void setExpertization(String expertization) {
		this.expertization = expertization;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImage(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	private double rating;
	private boolean isActivated;
	private String activationCode;
	
	public Expert() {
		super();
	}

	public Expert(long id, String name, String email, String phone, String aadhar, String password, double rating,
			boolean isActivated, String activationCode) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.aadhar = aadhar;
		this.password = password;
		this.rating = rating;
		this.isActivated = isActivated;
		this.activationCode = activationCode;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAadhar() {
		return aadhar;
	}

	public void setAadhar(String aadhar) {
		this.aadhar = aadhar;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public boolean isActivated() {
		return isActivated;
	}

	public void setActivated(boolean isActivated) {
		this.isActivated = isActivated;
	}

	public String getActivationCode() {
		return activationCode;
	}

	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}
	
}
