package com.vCare4u.uploadFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Component
public class FileUploader {
//	Hello
//	public final String path = new ClassPathResource("/static/images/").getFile().getAbsolutePath();
	public final String path = "C:/Users/manis/git/vcare4u_springboot/src/main/resources/";
	public FileUploader() throws IOException{
		System.out.println(path);
	}
	public ResponseEntity<String> uploadFile(MultipartFile file) {
		try {
			Files.copy(file.getInputStream(),Paths.get(path+File.separator+file.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
			return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath().path("/images/").path(file.getOriginalFilename()).toUriString());
		}catch(Exception e) {
		    System.out.println("Error in file");
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File Error");
	}
	
}
