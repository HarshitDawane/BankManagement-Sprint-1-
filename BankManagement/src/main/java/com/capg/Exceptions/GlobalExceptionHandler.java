package com.capg.Exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(HandlingException.class)
	public ResponseEntity<ErrorDetails> handleRecordNotFoundExeption(HandlingException ex,WebRequest request)
	{
	
		
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), HttpStatus.FORBIDDEN);

		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.FORBIDDEN);
	
	}
}