package com.capg.Exceptions;

import org.springframework.http.HttpStatus;

public class HandlingException extends Exception{
	
	private HttpStatus s ; 
	public HandlingException(String msg)
	{
	 
		super(msg);	
	}
	

}
