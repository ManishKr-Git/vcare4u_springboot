package com.vCare4u.daoServices;


import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vCare4u.Entity.Reviews;

public interface ReviewsDao extends JpaRepository<Reviews, Integer> {
	public Reviews findByCustomerIdAndSupplierId(BigInteger bigInteger,BigInteger id);
	public List<Reviews>findBySupplierId(BigInteger id);
}
