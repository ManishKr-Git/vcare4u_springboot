package com.vCare4u.daoServices;

import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vCare4u.Entity.Bookings;
import com.vCare4u.Entity.User;

public interface BookingDao extends JpaRepository<Bookings, BigInteger>{
	public List<Bookings>findByUserId(BigInteger id);
	public List<Bookings>findByExpertId(BigInteger id);
}