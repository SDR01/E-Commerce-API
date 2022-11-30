package com.onlineshop.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.onlineshop.exception.LoginException;
import com.onlineshop.model.CurrentUserSession;
import com.onlineshop.model.Login;
import com.onlineshop.services.LoginServices;

@RestController
public class LoginController {

	@Autowired
	private LoginServices lService;
	
	@PostMapping("/login")
	public ResponseEntity<CurrentUserSession> loginCustomer(@RequestBody Login login) throws LoginException {
		CurrentUserSession cusr= lService.login(login);
		return new ResponseEntity<CurrentUserSession>(cusr,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/logout/{uuid}")
	public ResponseEntity<String> logoutCustomer(@PathVariable("uuid") String uuid) throws LoginException{
		String Str= lService.logout(uuid);
		return new ResponseEntity<String>(Str,HttpStatus.ACCEPTED);
	}
}
