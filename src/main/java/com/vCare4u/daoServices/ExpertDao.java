package com.vCare4u.daoServices;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vCare4u.Entity.Expert;

public interface ExpertDao extends JpaRepository<Expert, BigInteger>{
	public List<Expert>findAllByIsActivatedTrue();
	public Expert findByEmailAndPassword(String email, String password);
	public Expert findByEmail(String email);
}
