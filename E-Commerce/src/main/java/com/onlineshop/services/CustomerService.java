package com.onlineshop.services;

import org.springframework.stereotype.Service;

import com.onlineshop.exception.CustomerException;
import com.onlineshop.exception.LoginException;
import com.onlineshop.model.Address;
import com.onlineshop.model.Customer;


@Service
public interface CustomerService {
	
	public Customer createAccount(Customer customer) throws CustomerException;
	
	public Address setAddress(Address address,Integer id,String key) throws CustomerException,LoginException;
	
	public Customer updateCustomer(Customer customer,String key)throws LoginException,CustomerException;
}
