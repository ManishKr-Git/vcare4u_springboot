 package com.vCare4u.services;

import java.math.BigInteger;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.vCare4u.Entity.ApiResponse;
import com.vCare4u.Entity.Bookings;
import com.vCare4u.Entity.LoginDetails;
import com.vCare4u.Entity.Reviews;
import com.vCare4u.Entity.User;
import com.vCare4u.daoServices.BookingDao;
import com.vCare4u.daoServices.UserDao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.razorpay.*;
@Service
public class UserServicesImp implements UserServices {
	@Autowired
	private UserDao userServices;
	@Autowired
	private BookingDao bookingServices;
	public UserServicesImp(){
	}
	public List<User>getUsers(){
		return userServices.findAll();
	}
	public User addUser(User user){
		return userServices.save(user);
	}
	public User userLogin(LoginDetails user) {
		User registered_user = userServices.findByEmailAndPassword(user.getEmail(),user.getPassword());
		if(registered_user!=null &&registered_user.isActivated()) {
			return registered_user;
		}else if(registered_user==null){
			throw new RuntimeException("Invalid User Credentials!!!");
		}else {
			throw new RuntimeException("User is not activated!!!!!!");
		}
	}
	public ResponseEntity<String>activateAccount(String activationCode){
		List<User>list = userServices.findAll();
		Iterator<User>it = list.iterator();
		while(it.hasNext()) {
			User u = it.next();
			if(u.getActivationCode().equals(activationCode)) {
				u.setActivated(true);
				userServices.save(u);
				return ResponseEntity.status(HttpStatus.ACCEPTED).build();
			}
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
	}
	public void addUserRating(BigInteger id,Reviews reviews){
	}
	public boolean alreadyExist(String email) {
		User user = userServices.findByEmail(email);
		System.out.println(user);
		return user!=null;
	}
	public List<Bookings> getAllUserBookings(BigInteger userId){
		return bookingServices.findByUserId(userId);
	}
	public Bookings addUserBookings(Bookings booking) {
		booking.setBookedOn(new Date());
		try {
			RazorpayClient razorpayClient = new RazorpayClient("rzp_test_eLPfUeW6aBcJdA", "zDsob3G9Tt59Q10hi97g1bCK");
			JSONObject options = new JSONObject();
			options.put("amount", booking.getFees()*100);
			options.put("currency", "INR");
			options.put("receipt", "txn_123456");
			//creating_order			
			Order order = razorpayClient.Orders.create(options);
			System.out.println(order);
			
		} catch (RazorpayException e) {
			e.printStackTrace();
		}
		return bookingServices.save(booking);
	}
	public List<Bookings> getUserBookings(@PathVariable BigInteger userId){
		return bookingServices.findByUserId(userId);
	}
	public String createOrder(Bookings booking){
		try {
			RazorpayClient razorpayClient = new RazorpayClient("rzp_test_eLPfUeW6aBcJdA", "zDsob3G9Tt59Q10hi97g1bCK");
			JSONObject options = new JSONObject();
			options.put("amount", booking.getFees()*100);
			options.put("currency", "INR");
			options.put("receipt", "txn_123456");
			//creating_order			
			Order order = razorpayClient.Orders.create(options);
			ObjectMapper obj = new ObjectMapper();
			HashMap<String,String> result =
					obj.readValue(order.toString(), HashMap.class);
			System.out.println(result.get("id"));
			String orderId = result.get("id");
			booking.setOrderNo(orderId);
			booking.setBookedOn(new Date(System.currentTimeMillis()));
			bookingServices.save(booking);
			return order.toString();			
		} catch (RazorpayException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
