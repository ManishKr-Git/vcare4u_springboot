package com.vCare4u.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vCare4u.Entity.Expert;
import com.vCare4u.Entity.LoginDetails;
import com.vCare4u.Entity.User;
import com.vCare4u.services.ExpertServices;
import com.vCare4u.services.UserServices;
import com.vCare4u.uploadFile.FileUploader;
//@CrossOrigin("https://vcare4-u.herokuapp.com")
@CrossOrigin("http://localhost:3000")
@RestController
public class myController {
	@Autowired
	private UserServices userServices;
	@Autowired
	private ExpertServices expertServices;
	@Autowired
	private FileUploader fileUploader;
	
	@GetMapping("/email-verification/activating-account/{activationCode}")
	public ResponseEntity<String> activateAccount(@PathVariable String activationCode){
		return userServices.activateAccount(activationCode);
	}
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
		System.out.println(user.getPassword());		
		return userServices.userLogin(user);
	}
	//Experts'Functions
	@PostMapping("/experts")
	public void addExpert(@RequestParam("image") MultipartFile file,@RequestParam("expert") String expertString) throws JsonMappingException, JsonProcessingException {
		ObjectMapper obj = new ObjectMapper();
		Expert expert = obj.readValue(expertString, Expert.class);
		String url = fileUploader.uploadFile(file).toString().split(",")[1];	
		expert.setImage(url);
		expertServices.addExpert(expert);
	}
	@GetMapping("/experts")
	public List<Expert>getExperts(){
		return expertServices.getExperts();
	}
	
//	@PostMapping("login/expert-login")
//	public void expertLogin(@RequestBody LoginDetails user){
//		services.expertLogin(user);
//	}
}
