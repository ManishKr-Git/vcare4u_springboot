package com.vCare4u.services;

import java.math.BigInteger;
import java.util.List;

import org.springframework.http.ResponseEntity;

import com.vCare4u.Entity.LoginDetails;
import com.vCare4u.Entity.Reviews;
import com.vCare4u.Entity.User;

public interface UserServices {
	public List<User>getUsers();
	public User addUser(User user);
	public boolean alreadyExist(String email);
	public User userLogin(LoginDetails user);
	public ResponseEntity<String>activateAccount(String activationCode);
	public void addUserRating(BigInteger id,Reviews reviews);	
	
}
