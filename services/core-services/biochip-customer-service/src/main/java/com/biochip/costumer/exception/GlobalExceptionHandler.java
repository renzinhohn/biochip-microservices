package com.biochip.costumer.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
		ErrorDetails error = new ErrorDetails(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), LocalDateTime.now());
		
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> resourceNotFound(ResourceNotFoundException ex, WebRequest req) {
		ErrorDetails error = new ErrorDetails(ex.getMessage(), HttpStatus.NOT_FOUND.toString(), LocalDateTime.now());
		
		return new ResponseEntity<ErrorDetails>(error,HttpStatus.NOT_FOUND);
	}
}

//@ControllerAdvice
//public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
//
//	@ExceptionHandler(ResourceNotFoundException.class)
//	public ResponseEntity<ErrorDetails> resourceNotFound(ResourceNotFoundException ex, WebRequest req) {
//		ErrorDetails error = new ErrorDetails(ex.getMessage(), HttpStatus.NOT_FOUND.toString(), LocalDateTime.now());
//		
//		return new ResponseEntity<ErrorDetails>(error,HttpStatus.NOT_FOUND);
//	}
//}
