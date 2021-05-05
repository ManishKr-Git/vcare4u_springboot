package com.vCare4u.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.vCare4u.Entity.LoginDetails;
import com.vCare4u.Entity.User;

public interface UserServices {
	public List<User>getUsers();
	public User addUser(User user);
	public ResponseEntity<User> userLogin(LoginDetails user);
	public ResponseEntity<String>activateAccount(String activationCode);
}
