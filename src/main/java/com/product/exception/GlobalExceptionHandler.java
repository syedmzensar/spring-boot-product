package com.product.exception;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<Object> resourceNotFoundException(ProductNotFoundException ex, WebRequest request) {
		ErrorResponse message = new ErrorResponse(ex.getMessage());
		Map<String, Object> body = new LinkedHashMap<String, Object>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", message);
		return new ResponseEntity<Object>(body, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(ProductAlreadyExistsException.class)
	public ResponseEntity<Object> resourceAlreadyExistsException(ProductAlreadyExistsException ex, WebRequest request) {
		ErrorResponse message = new ErrorResponse(ex.getMessage());
		Map<String, Object> body = new LinkedHashMap<String, Object>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", message);
		return new ResponseEntity<Object>(body, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> globalExceptionHandler(Exception ex, WebRequest request) {
		ErrorResponse message = new ErrorResponse(ex.getMessage());
		Map<String, Object> body = new LinkedHashMap<String, Object>();
		body.put("timestamp", LocalDateTime.now());
		body.put("message", message);
		body.put("global message", "Check the url");

		return new ResponseEntity<Object>(body, HttpStatus.BAD_GATEWAY);
	}
}
