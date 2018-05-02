package org.fuber.cabservice.fubercabservice.controller;

import org.fuber.cabservice.exception.ExceptionBody;
import org.fuber.cabservice.exception.InvalidBookingIdException;
import org.fuber.cabservice.exception.NoCabFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandlerController {

	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = InvalidBookingIdException.class)
	public ExceptionBody handleException(InvalidBookingIdException e){
		return new ExceptionBody(HttpStatus.NOT_FOUND, e.getMessage());
	}
	
	@ResponseStatus(value=HttpStatus.OK)
	@ExceptionHandler(value = NoCabFoundException.class)
	public ExceptionBody handleException(NoCabFoundException e){
		return new ExceptionBody(HttpStatus.OK, e.getMessage());
	}
	
	
	
}
