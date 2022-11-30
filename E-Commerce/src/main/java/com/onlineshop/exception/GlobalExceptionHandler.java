package com.onlineshop.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(AddressException.class)
	public ResponseEntity<MyErrorDetails> AddressException(AddressException ex,WebRequest webRequest){
		
		MyErrorDetails err = new MyErrorDetails();
		err.setLocaldateTime(LocalDateTime.now());
		err.setMessage(ex.getMessage());
		err.setDescription(webRequest.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_GATEWAY);
	}
	
	@ExceptionHandler(AdminException.class)
	public ResponseEntity<MyErrorDetails> AdminException(AdminException ex,WebRequest webRequest){
		
		MyErrorDetails err = new MyErrorDetails();
		err.setLocaldateTime(LocalDateTime.now());
		err.setMessage(ex.getMessage());
		err.setDescription(webRequest.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_GATEWAY);
	}
	
	@ExceptionHandler(CartException.class)
	public ResponseEntity<MyErrorDetails> CartException(CartException ex,WebRequest webRequest){
		
		MyErrorDetails err = new MyErrorDetails();
		err.setLocaldateTime(LocalDateTime.now());
		err.setMessage(ex.getMessage());
		err.setDescription(webRequest.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_GATEWAY);
	}
	
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<MyErrorDetails> CustomerException(CustomerException ex,WebRequest webRequest){
		
		MyErrorDetails err = new MyErrorDetails();
		err.setLocaldateTime(LocalDateTime.now());
		err.setMessage(ex.getMessage());
		err.setDescription(webRequest.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_GATEWAY);
	}
	
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<MyErrorDetails> LoginException(LoginException ex,WebRequest webRequest){
		
		MyErrorDetails err = new MyErrorDetails();
		err.setLocaldateTime(LocalDateTime.now());
		err.setMessage(ex.getMessage());
		err.setDescription(webRequest.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_GATEWAY);
	}
	
	@ExceptionHandler(OrderException.class)
	public ResponseEntity<MyErrorDetails> OrderException(OrderException ex,WebRequest webRequest){
		
		MyErrorDetails err = new MyErrorDetails();
		err.setLocaldateTime(LocalDateTime.now());
		err.setMessage(ex.getMessage());
		err.setDescription(webRequest.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_GATEWAY);
	}
	
	@ExceptionHandler(ProductException.class)
	public ResponseEntity<MyErrorDetails> ProductException(ProductException ex,WebRequest webRequest){
		
		MyErrorDetails err = new MyErrorDetails();
		err.setLocaldateTime(LocalDateTime.now());
		err.setMessage(ex.getMessage());
		err.setDescription(webRequest.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_GATEWAY);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> Exception(Exception ex,WebRequest webRequest){
		
		MyErrorDetails err = new MyErrorDetails();
		err.setLocaldateTime(LocalDateTime.now());
		err.setMessage(ex.getMessage());
		err.setDescription(webRequest.getDescription(false));
		
		return new ResponseEntity<MyErrorDetails>(err,HttpStatus.BAD_GATEWAY);
	}
	
}
