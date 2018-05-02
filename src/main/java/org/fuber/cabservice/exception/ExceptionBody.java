package org.fuber.cabservice.exception;

import org.springframework.http.HttpStatus;

public class ExceptionBody {
	public HttpStatus getCode() {
		return code;
	}
	public void setCode(HttpStatus code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	private HttpStatus code;
	private String message;
	public ExceptionBody(HttpStatus notFound, String message) {		
		this.code = notFound;
		this.message = message;
	}
	
	
}
