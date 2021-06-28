package com.vCare4u.Entity;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Bookings {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private BigInteger id;
	private BigInteger userId;
	private BigInteger expertId;
	private String userName;
	private String expertName;
	private float userRating;
	private float expertRating;
	private float fees;
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Date bookedOn;
	private String orderNo;
}
