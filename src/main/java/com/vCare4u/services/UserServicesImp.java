 package com.vCare4u.services;

import java.math.BigInteger;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vCare4u.Entity.Expert;
import com.vCare4u.Entity.LoginDetails;
import com.vCare4u.Entity.Reviews;
import com.vCare4u.Entity.User;
import com.vCare4u.daoServices.UserDao;
@Service
public class UserServicesImp implements UserServices {
	@Autowired
	private UserDao services;
	public UserServicesImp(){
	}
	public List<User>getUsers(){
		return services.findAll();
	}
	public User addUser(User user){
		return services.save(user);
	}
	public User userLogin(LoginDetails user) {
		User registered_user = services.findByEmailAndPassword(user.getEmail(),user.getPassword());
		if(registered_user!=null &&registered_user.isActivated()) {
			return registered_user;
		}else if(registered_user==null){
			throw new RuntimeException("Invalid User Credentials!!!");
		}else {
			throw new RuntimeException("User is not activated!!!!!!");
		}
	}
	public ResponseEntity<String>activateAccount(String activationCode){
		List<User>list = services.findAll();
		Iterator<User>it = list.iterator();
		while(it.hasNext()) {
			User u = it.next();
			if(u.getActivationCode().equals(activationCode)) {
				u.setActivated(true);
				services.save(u);
				return ResponseEntity.status(HttpStatus.ACCEPTED).build();
			}
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
	}
	public void addUserRating(BigInteger id,Reviews reviews){
	}
	public boolean alreadyExist(String email) {
		User user = services.findByEmail(email);
		System.out.println(user);
		return user!=null;
	}
}
