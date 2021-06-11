package com.vCare4u.Entity;

import java.math.BigInteger;
import java.util.HashMap;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.MapKeyColumn;

@Entity
public class Expert {
	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	private BigInteger id;
	private String name;
	private String email;
	private String phone;
	private String aadhar;
	private String password;
	private String expertization;
	private String specification;
	private String description;
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	private String image;
	private double rating;
	private boolean isActivated;
	private String activationCode;
	private double priceWithoutDiscount;
	private double fees;
	public Expert() {
		super();
	}
	public Expert(BigInteger id, String name, String email, String phone, String aadhar, String password,
			String expertization, String specification, String description, String image, double rating,
			boolean isActivated, String activationCode, double priceWithoutDiscount, double fees) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.aadhar = aadhar;
		this.password = password;
		this.expertization = expertization;
		this.specification = specification;
		this.description = description;
		this.image = image;
		this.rating = rating;
		this.isActivated = isActivated;
		this.activationCode = activationCode;
		this.priceWithoutDiscount = priceWithoutDiscount;
		this.fees = fees;
	}
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
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
	public double getPriceWithoutDiscount() {
		return priceWithoutDiscount;
	}
	public void setPriceWithoutDiscount(double priceWithoutDiscount) {
		this.priceWithoutDiscount = priceWithoutDiscount;
	}
	public double getFees() {
		return fees;
	}
	public void setFees(double fees) {
		this.fees = fees;
	}
	
	
}
