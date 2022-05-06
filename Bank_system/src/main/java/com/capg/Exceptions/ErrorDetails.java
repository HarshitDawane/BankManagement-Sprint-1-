package com.capg.Exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;

public class ErrorDetails {

	private Date timestamp;
    private String message;
    
    private HttpStatus status;

    public ErrorDetails(Date timestamp, String message, HttpStatus status) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.status = status;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
         return message;
    }

    public HttpStatus getStatus() {
         return status;
    }
}
