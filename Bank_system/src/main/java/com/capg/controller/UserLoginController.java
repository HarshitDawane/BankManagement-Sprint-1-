package com.capg.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.Exceptions.HandlingException;
import com.capg.Repository.UserDetailRepository;

import com.capg.entity.UserDetail;

import com.capg.entity.UserLogin;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user")
public class UserLoginController {
	


	@Autowired
	private UserDetailRepository userRepo ;
 
	LocalDateTime instant;
	
	@PostMapping("/userLogin")
	public Map<String,String> loginUser(@RequestBody UserDetail user) throws HandlingException
	{
		 
		 UserDetail detail = userRepo.findByEmailAndPassword(user.getEmail(), user.getPassword());
		 
		 if(detail !=null)
		{
			         DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
					 instant = LocalDateTime.now();
					 String currentTime =instant.format(myFormatObj);
				   
				     Map<String,String> map=new HashMap<String, String>();
				     map.put("Status Code","202");
					 return map ;
		}
		 else 
		 { 
			throw new HandlingException("Login credentials are incorrect");
		 }
	
		

	  
	}
	

}
