 package com.vCare4u.services;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vCare4u.Entity.Bookings;
import com.vCare4u.Entity.Expert;
import com.vCare4u.Entity.LoginDetails;
import com.vCare4u.Entity.Reviews;
import com.vCare4u.daoServices.BookingDao;
import com.vCare4u.daoServices.ExpertDao;
import com.vCare4u.daoServices.ReviewsDao;
@Service
public class ExpertServicesImp implements ExpertServices {
	@Autowired
	private ExpertDao expertServices;
	@Autowired
	private ReviewsDao reviewsServices;
	@Autowired
	private BookingDao bookings;
	public ExpertServicesImp(){
	}
	public List<Expert>getExperts(){
		return expertServices.findAllByIsActivatedTrue();
	}
	public Expert getExpert(BigInteger id){
		return expertServices.findById(id).orElse(null);
	}
	public Expert addExpert(Expert expert){
		System.out.println("Adding");
		return expertServices.save(expert);
	}
	public Expert expertLogin(LoginDetails expert) {
		Expert registered_expert = expertServices.findByEmailAndPassword(expert.getEmail(),expert.getPassword());
		if(registered_expert!=null &&registered_expert.isActivated()) {
			return registered_expert;
		}else if(registered_expert==null){
			throw new RuntimeException("Invalid Expert Credentials!!!");
		}else {
			throw new RuntimeException("Expert is not activated!!!!!!");
		}		
	}
	public ResponseEntity<String>activateAccount(String activationCode){
		List<Expert>list = expertServices.findAll();
		Iterator<Expert>it = list.iterator();
		while(it.hasNext()) {
			Expert e = it.next();
			if(e.getActivationCode().equals(activationCode)) {
				e.setActivated(true);
				expertServices.save(e);
				return ResponseEntity.status(HttpStatus.ACCEPTED).build();
			}
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
	}
	public void addExpertRating(BigInteger id,Reviews reviews){
		System.out.println(reviews);
		Reviews old = reviewsServices.findByCustomerIdAndSupplierId(reviews.getCustomerId(),id);
		System.out.println(old);
		if(old==null) {
			reviews.setSupplierId(id);
			reviewsServices.save(reviews);
		}else {
			old.setStars(reviews.getStars());
			old.setReview(reviews.getReview());
			reviewsServices.save(old);
		}
		List<Reviews> total_reviews = reviewsServices.findBySupplierId(id);
		float total_rating = 0;
		for(Reviews r:total_reviews) {
			total_rating+=r.getStars();
		}
		Expert e = expertServices.getOne(id);
		e.setRating(total_rating/total_reviews.size());
		expertServices.save(e);
	}
	public List<Reviews>getExpertRating(BigInteger id){
		return reviewsServices.findBySupplierId(id);
	}
	public boolean alreadyExist(String email) {
		Expert expert = expertServices.findByEmail(email);
		System.out.println(expert);
		return expert!=null;
	}
	public List<Bookings> getAllExpertBookings(BigInteger expertId){
		return bookings.findByExpertId(expertId);
	}
	public List<Expert>getSelectedCategoryExperts(String category){
		return expertServices.findByExpertization(category);
	}
}
