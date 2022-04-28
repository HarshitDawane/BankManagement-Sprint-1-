package com.capg.entity;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ContactUs {

	@Id 
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int Id;
    private String name;
    private String email;
    private String message;
    private String contactNumber;
    
    
    
	public int getId() {
		return Id;
	}
	public ContactUs() {
		super();
	}
	public ContactUs(int id, String name, String email, String message, String contactNumber) {
		super();
		Id = id;
		this.name = name;
		this.email = email;
		this.message = message;
		this.contactNumber = contactNumber;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
    
    
}
