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
import com.vCare4u.Entity.ApiResponse;
import com.vCare4u.Entity.Bookings;
import com.vCare4u.Entity.Expert;
import com.vCare4u.Entity.LoginDetails;
import com.vCare4u.Entity.Reviews;
import com.vCare4u.Entity.User;
import com.vCare4u.services.ExpertServices;
import com.vCare4u.services.UserServices;
//@CrossOrigin("https://vcare4u-uoh.herokuapp.com")
@CrossOrigin("http://localhost:3000")
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
	public ApiResponse addUser(@RequestBody User user) {
		System.out.println("Here");
		if(userServices.alreadyExist(user.getEmail()))
		{
			return new ApiResponse<User>(null, false, "User Already Exist!!!");
		}
		userServices.addUser(user);
		return new ApiResponse<User>(null, true, "Registration Successfull!!!");
	}
	@PostMapping("login/user-login")
	public ApiResponse<User> userLogin(@RequestBody LoginDetails user){
		return new ApiResponse<>(userServices.userLogin(user), true, "Registeration Successfull!!");
	}
	@GetMapping("/user-email-verification/activating-account/{activationCode}")
	public ResponseEntity<String> activateUserAccount(@PathVariable String activationCode){
		return userServices.activateAccount(activationCode);		
	}
	@PostMapping("user-ratings/{id}")
	public void addUserRating(@PathVariable BigInteger id,@RequestBody Reviews reviews){
		userServices.addUserRating(id,reviews);
	}
	@GetMapping("/user-bookings/{userId}")
	public ApiResponse<List<Bookings>> getAllUserBookings(@PathVariable BigInteger userId){
		return new ApiResponse<List<Bookings>>(userServices.getAllUserBookings(userId), true, "All bookings");		
	}
	@PostMapping("/add-bookings/")
	public ApiResponse<Bookings> addUserBookings(@RequestBody Bookings bookings){
		return new ApiResponse<Bookings>(userServices.addUserBookings(bookings), true, "Booking Has Been Added");		
	}
//////////////////////////////////////////////////////////////////////////////////////////	
//Experts'Functions
	@PostMapping("/experts")
	public ApiResponse<Expert> addExpert(@RequestParam("image") MultipartFile file,@RequestParam("expert") String expertString) throws Exception {
		
		try
		{
			ObjectMapper obj = new ObjectMapper();
			Expert expert = obj.readValue(expertString, Expert.class);
			if(expertServices.alreadyExist(expert.getEmail()))
			{
				throw new RuntimeException("Expert with same email id already exist!!!");
			}
			expert.setPriceWithoutDiscount(Math.floor((Math.random()*expert.getFees()/2)+expert.getFees()));
			expert.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
			expertServices.addExpert(expert);
			System.out.println(expert.getEmail());
			return new ApiResponse<Expert>(null, true, "Registeration Successfull!!");
		}catch(Exception e) {
			throw new RuntimeException("Some Error Occured!!!Try again later");
		}
		
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
	public ApiResponse<Expert> expertLogin(@RequestBody LoginDetails expert){
		return new ApiResponse<>(expertServices.expertLogin(expert), true, "Registeration Successfull!!");
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
	@GetMapping("/expert-bookings/{expertId}")
	public ApiResponse<List<Bookings>> getAllExpertBookings(@PathVariable BigInteger expertId){
		return new ApiResponse<List<Bookings>>(expertServices.getAllExpertBookings(expertId), true, "All bookings");		
	}
	@GetMapping("/selectedCategoryExperts/{category}")
	public ApiResponse<List<Expert>> getSelectedCategoryExperts(@PathVariable String category){
		return new ApiResponse<List<Expert>>(expertServices.getSelectedCategoryExperts(category), true, "All bookings");		
	}
	//Common Functions
	@PostMapping("/createOrder/")
	public String createOrder(@RequestBody Bookings orderData){
		return userServices.createOrder(orderData);	
	}
}
