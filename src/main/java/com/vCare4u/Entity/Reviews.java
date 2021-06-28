package com.vCare4u.Entity;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
