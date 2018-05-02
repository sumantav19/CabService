package org.fuber.cabservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class InvalidBookingIdException extends RuntimeException {
	public InvalidBookingIdException(String message){
		super(message);
	}
}
