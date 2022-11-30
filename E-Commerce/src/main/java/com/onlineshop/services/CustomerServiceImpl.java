package com.onlineshop.services;

import java.util.Optional;


import javax.crypto.Cipher;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineshop.exception.CustomerException;
import com.onlineshop.exception.LoginException;
import com.onlineshop.model.Address;
import com.onlineshop.model.Cart;
import com.onlineshop.model.CurrentUserSession;
import com.onlineshop.model.Customer;
import com.onlineshop.model.Products;
import com.onlineshop.repository.AddressRepo;
import com.onlineshop.repository.CurrentUserSessionRepo;
import com.onlineshop.repository.CustomerRepo;
import com.onlineshop.repository.ProductRepo;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepo cusRepo;
	@Autowired
	private AddressRepo aRepo;
	@Autowired
	private ProductRepo pRepo;
	@Autowired
	private CurrentUserSessionRepo cusr;

	@Override
	public Customer createAccount(Customer customer) throws CustomerException {
		Customer customers = cusRepo.save(customer);
		return customers;
	}

	@Override
	public Address setAddress(Address add, Integer customerId,String key) throws CustomerException, LoginException {
		Optional<Customer> customers = cusRepo.findById(customerId);
		if (customers.isPresent()) {
			Customer c = customers.get();
			CurrentUserSession RunningSession = cusr.findByUuid(key);
			if (RunningSession == null) {
				throw new LoginException("Invalid Key");
			}
			if(c.getCustomerId()!=RunningSession.getUserId()) {
				throw new CustomerException("Login Required");
			}
			c.setAddress(add);
			Address address = aRepo.save(add);
			aRepo.save(address);
			return add;
		} else throw new CustomerException("Invalid Customer");
	}

	@Override
	public Customer updateCustomer(Customer customer, String key) throws LoginException, CustomerException {
		CurrentUserSession runningSession = cusr.findByUuid(key);
		if (runningSession == null) {
			throw new LoginException("Please provide a valid key");
		}
		if (customer.getCustomerId() == runningSession.getUserId()) {
			return cusRepo.save(customer);
		} else {
			throw new CustomerException("Please login first");
		}
	}

}
