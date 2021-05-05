 package com.vCare4u.services;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vCare4u.Entity.Expert;
import com.vCare4u.Entity.LoginDetails;
import com.vCare4u.daoServices.ExpertDao;
@Service
public class ExpertServicesImp implements ExpertServices {
	@Autowired
	private ExpertDao services;
	public ExpertServicesImp(){
	}
	public List<Expert>getExperts(){
		return services.findAll();
	}
	public Expert addExpert(Expert expert){
		return services.save(expert);
	}
	public ResponseEntity<Expert> userLogin(LoginDetails user) {
		String email  = user.getEmail();
		String pass = user.getPassword();
		List<Expert>registered_experts = getExperts();
		Iterator<Expert>it = registered_experts.iterator();
		while(it.hasNext()) {
			Expert e = it.next();
			if(e.getEmail().equalsIgnoreCase(email) && e.getPassword().equals(pass)) {
				if(e.isActivated()) {
					return ResponseEntity.of(Optional.of(e));
				}else {
					return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
				}
			}
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	public ResponseEntity<String>activateAccount(String activationCode){
		List<Expert>list = services.findAll();
		Iterator<Expert>it = list.iterator();
		while(it.hasNext()) {
			Expert e = it.next();
			if(e.getActivationCode().equals(activationCode)) {
				e.setActivated(true);
				services.save(e);
				return ResponseEntity.status(HttpStatus.ACCEPTED).build();
			}
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); 
	}
}
