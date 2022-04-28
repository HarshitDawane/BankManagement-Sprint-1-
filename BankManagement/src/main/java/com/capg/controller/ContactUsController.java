package com.capg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.capg.Exceptions.HandlingException;
import com.capg.Repository.ContactUsRepository;
import com.capg.entity.ContactUs;

@Controller
@RequestMapping("/contactUs")
public class ContactUsController {


	@Autowired
	private ContactUsRepository contactUsRepository ; 
	
	//name check validations
	
	@PostMapping("/savecontact")
    public ResponseEntity<ContactUs> savedata(@RequestBody ContactUs con) throws HandlingException
    {
        String emailPattern="^[A-Za-z0-9._%+-]+@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}$";
        String mobilePattern="(^$|[6-9][0-9]{9})";
        if(!con.getEmail().matches(emailPattern))
        {
           
            throw new HandlingException("Email is invalid");
        }
        if(!con.getContactNumber().toString().matches(mobilePattern))
        {
           
            throw new HandlingException("please give valid Contact number");
        }

        if(con.getMessage().isEmpty())
        {
            
            throw new HandlingException("Message field is empty");
        }
        ContactUs contactUs=contactUsRepository.save(con);
        return new ResponseEntity<ContactUs>(contactUs ,HttpStatus.CREATED);
    }
	
	
	@PostMapping("/contactUsDetails")
    public List<ContactUs> contactUsDetails(@RequestParam String accessBy ) throws HandlingException
    {
		if(accessBy.equals("Admin")) {
        List<ContactUs> lst = contactUsRepository.findAll();
        return lst;
		}
		else
		{
			throw new HandlingException(" only admin can access the details.");
		}

    }
	
}
