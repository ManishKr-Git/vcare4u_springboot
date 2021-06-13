package com.vCare4u.services;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.vCare4u.Entity.Expert;
import com.vCare4u.Entity.LoginDetails;
import com.vCare4u.Entity.Reviews;

public interface ExpertServices {
	public List<Expert>getExperts();
	public Expert getExpert(BigInteger id);
	public Expert addExpert(Expert expert);
	public boolean alreadyExist(String email);
	public ResponseEntity<Expert> expertLogin(LoginDetails expert);
	public ResponseEntity<String>activateAccount(String activationCode);
	public void addExpertRating(BigInteger id,Reviews reviews);
	public List<Reviews>getExpertRating(BigInteger id);
}
