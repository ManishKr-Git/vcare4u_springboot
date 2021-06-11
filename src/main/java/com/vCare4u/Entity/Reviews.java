package com.vCare4u.Entity;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Reviews {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private BigInteger customerId;
	private BigInteger supplierId;
	private String raterName;
	private float stars;
	private String review;
	
	public Reviews() {
		super();
	}
	public Reviews(int id, BigInteger customerId, BigInteger supplierId, String raterName, float stars, String review) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.supplierId = supplierId;
		this.raterName = raterName;
		this.stars = stars;
		this.review = review;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigInteger getCustomer_id() {
		return customerId;
	}

	public void setCustomer_id(BigInteger customer_id) {
		this.customerId = customer_id;
	}

	public BigInteger getSupplier_id() {
		return supplierId;
	}

	public void setSupplier_id(BigInteger supplier_id) {
		this.supplierId = supplier_id;
	}

	public float getStars() {
		return stars;
	}

	public void setStars(float stars) {
		this.stars = stars;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}
	public BigInteger getCustomerId() {
		return customerId;
	}
	public void setCustomerId(BigInteger customerId) {
		this.customerId = customerId;
	}
	public BigInteger getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(BigInteger supplierId) {
		this.supplierId = supplierId;
	}
	public String getRaterName() {
		return raterName;
	}
	public void setRaterName(String raterName) {
		this.raterName = raterName;
	}	
}
