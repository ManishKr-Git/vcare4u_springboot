package com.vCare4u.controller;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vCare4u.Entity.Expert;
import com.vCare4u.Entity.LoginDetails;
import com.vCare4u.Entity.Reviews;
import com.vCare4u.Entity.User;
import com.vCare4u.services.ExpertServices;
import com.vCare4u.services.UserServices;
@CrossOrigin("https://vcare4u-uoh.herokuapp.com/")
//@CrossOrigin("http://localhost:3000")
@RestController
public class myController {
	@Autowired
	private UserServices userServices;
	@Autowired
	private ExpertServices expertServices;
	
/////////////////////////////////////////////////////////////////////////////////////
//User's Functions
	@GetMapping("/users")
	public List<User>getUsers(){
		return userServices.getUsers();
	}
	@PostMapping("/users")
	public User addUser(@RequestBody User user) {
		userServices.addUser(user);
		return user;
	}
	@PostMapping("login/user-login")
	public ResponseEntity<User> userLogin(@RequestBody LoginDetails user){
		return userServices.userLogin(user);
	}
	@GetMapping("/user-email-verification/activating-account/{activationCode}")
	public ResponseEntity<String> activateUserAccount(@PathVariable String activationCode){
		return userServices.activateAccount(activationCode);		
	}
	@PostMapping("user-ratings/{id}")
	public void addUserRating(@PathVariable BigInteger id,@RequestBody Reviews reviews){
		userServices.addUserRating(id,reviews);
	}
	
//////////////////////////////////////////////////////////////////////////////////////////	
//Experts'Functions
	@PostMapping("/experts")
	public void addExpert(@RequestParam("image") MultipartFile file,@RequestParam("expert") String expertString) throws IOException {
		ObjectMapper obj = new ObjectMapper();
		Expert expert = obj.readValue(expertString, Expert.class);
		expert.setPriceWithoutDiscount(Math.floor((Math.random()*500)+500));
		expert.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
		expertServices.addExpert(expert);
	}
	@GetMapping("/experts")
	public List<Expert>getExperts(){
		return expertServices.getExperts();
	}
	@GetMapping("/experts/{id}")
	public Expert getExpert(@PathVariable BigInteger id){
		return expertServices.getExpert((BigInteger) id);
	}
	@PostMapping("login/expert-login")
	public ResponseEntity<Expert> expertLogin(@RequestBody LoginDetails expert){
		return expertServices.expertLogin(expert);
	}
	@GetMapping("/expert-email-verification/activating-account/{activationCode}")
	public ResponseEntity<String> activateExpertAccount(@PathVariable String activationCode){
		return expertServices.activateAccount(activationCode);		
	}
	@PostMapping("expert-ratings/{id}")
	public void addExpertRating(@PathVariable BigInteger id,@RequestBody Reviews reviews){		
		expertServices.addExpertRating(id,reviews);
	}
	@GetMapping("expert-ratings/{id}")
	public List<Reviews> getExpertRating(@PathVariable BigInteger id){		
		return expertServices.getExpertRating(id);
	}
}
