package com.onlineshop.controller;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onlineshop.exception.CustomerException;
import com.onlineshop.exception.LoginException;
import com.onlineshop.model.Address;
import com.onlineshop.model.Customer;
import com.onlineshop.services.CustomerService;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {
	@Autowired
	private CustomerService cs;

	@PostMapping("/create")
	public ResponseEntity<Customer> createAccount(@Valid @RequestBody Customer customer) throws CustomerException {
		Customer c1 = cs.createAccount(customer);
		return new ResponseEntity<Customer>(c1, HttpStatus.OK);
	}

	@PutMapping("/addAddress")
	public ResponseEntity<Address> putAddress(@RequestBody Address address, @RequestParam Integer id, @RequestParam String key) throws CustomerException, LoginException {
		Address ad = cs.setAddress(address, id, key);
		return new ResponseEntity<Address>(ad, HttpStatus.OK);
	}

}
