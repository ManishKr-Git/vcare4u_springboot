package com.vCare4u.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.vCare4u.Entity.Expert;
import com.vCare4u.Entity.LoginDetails;

public interface ExpertServices {
	public List<Expert>getExperts();
	public Expert addExpert(Expert expert);
	public ResponseEntity<Expert> userLogin(LoginDetails expert);
	public ResponseEntity<String>activateAccount(String activationCode);
}
