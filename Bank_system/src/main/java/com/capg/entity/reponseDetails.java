package com.capg.entity;

import java.util.Date;
import java.util.List;





public class reponseDetails {
	private int statusCode;
    private String message;
    private Date time;
    private List<EmployeeDetails> ob;
    
    
    public reponseDetails(int statusCode, String message, Date time, List<EmployeeDetails> details) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.time = time;
		this.ob = details;
	}
    
    
    public reponseDetails(int statusCode, String message, Date time) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.time = time;
	}
	
    
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public List<EmployeeDetails> getOb() {
		return ob;
	}
	public void setOb(List<EmployeeDetails> ob) {
		this.ob = ob;
	}
	
    
}
