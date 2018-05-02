package org.fuber.cabservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.OK)
public class NoCabFoundException extends RuntimeException {
	public NoCabFoundException(String message){
		super(message);
	}
}
