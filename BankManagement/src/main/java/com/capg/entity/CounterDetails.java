package com.capg.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.lang.NonNull;

@Entity
public class CounterDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int id;
	
	@NonNull 
	String name;
	@NonNull
	String availability;
	@NonNull
	String assigned_counter;
	@NonNull
	String status;
	@NonNull
	String mobileno;
	@NonNull
	int tokenno;
	
	@Column(nullable = false)
	private String date;
	
	@Temporal(TemporalType.TIME)
	@Column(nullable = false)
	private Date time;
	
	
	public CounterDetails() {
		super();
	}


	public CounterDetails(int id, String name, String availability, String assigned_counter, String status,
			String mobileno, int tokenno, String date, Date time) {
		super();
		this.id = id;
		this.name = name;
		this.availability = availability;
		this.assigned_counter = assigned_counter;
		this.status = status;
		this.mobileno = mobileno;
		this.tokenno = tokenno;
		this.date = date;
		this.time = time;
	}
	
	
	@PrePersist
	private void onCreate() {	   
	    time = new Date();
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAvailability() {
		return availability;
	}


	public void setAvailability(String availability) {
		this.availability = availability;
	}


	public String getAssigned_counter() {
		return assigned_counter;
	}


	public void setAssigned_counter(String assigned_counter) {
		this.assigned_counter = assigned_counter;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getMobileno() {
		return mobileno;
	}


	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}


	public int getTokenno() {
		return tokenno;
	}


	public void setTokenno(int tokenno) {
		this.tokenno = tokenno;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public Date getTime() {
		return time;
	}


	public void setTime(Date time) {
		this.time = time;
	}


	
	
	
}
