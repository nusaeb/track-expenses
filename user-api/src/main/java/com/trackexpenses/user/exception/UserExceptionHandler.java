package com.trackexpenses.user.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler{

	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers,
			HttpStatus status, WebRequest request){
		
		Map<String, Object> bodyOfResponse = new HashMap<>();
		
		bodyOfResponse.put("timestamp", LocalDateTime.now());
		bodyOfResponse.put("status", status.toString());
		
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult()
		.getFieldErrors()
		.forEach(fieldError -> 
			errors.put(fieldError.getField(), fieldError.getDefaultMessage()));
				
		bodyOfResponse.put("errors", errors);
		
		return new ResponseEntity<>(bodyOfResponse, headers, status);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	protected ResponseEntity<Object> handleUserNotFound(
			UserNotFoundException ex){
		
		Map<String, Object> bodyOfResponse = new HashMap<>();
		
		bodyOfResponse.put("timestamp", LocalDateTime.now());
		bodyOfResponse.put("status", HttpStatus.NOT_FOUND.toString());
		bodyOfResponse.put("message", ex.getMessage());
		
		return new ResponseEntity<>(bodyOfResponse, HttpStatus.NOT_FOUND);
	}
	
}