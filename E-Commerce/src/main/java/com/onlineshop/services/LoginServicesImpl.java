package com.onlineshop.services;

import java.time.LocalDateTime;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineshop.exception.LoginException;
import com.onlineshop.model.CurrentUserSession;
import com.onlineshop.model.Customer;
import com.onlineshop.model.Login;
import com.onlineshop.repository.CurrentUserSessionRepo;
import com.onlineshop.repository.CustomerRepo;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginServicesImpl implements LoginServices{

	@Autowired
	private CustomerRepo cRepo;
	
	@Autowired
	private CurrentUserSessionRepo currentUserRepo;
	
	@Override
	public CurrentUserSession login(Login log) throws LoginException{
		Customer currentCustomer= cRepo.findByEmail(log.getEmail());
		if(currentCustomer==null) { 
			throw new LoginException("Incorrect Email ID"); 
		}
		Optional<CurrentUserSession> opt= currentUserRepo.findById(currentCustomer.getCustomerId());
		if(opt.isPresent()) {
			throw new LoginException("User Already Logged In");
		}
		String key=RandomString.make(5);
		CurrentUserSession cSession = new CurrentUserSession(currentCustomer.getCustomerId(), LocalDateTime.now(), key);
		return currentUserRepo.save(cSession);
	}

	@Override
	public String logout(String uuid) throws LoginException {
		CurrentUserSession activeUserSession= currentUserRepo.findByUuid(uuid);
		if(activeUserSession==null) throw new LoginException("Incorrect uuid or User already logged out");
		currentUserRepo.delete(activeUserSession);
		return "Logged Out";
	}
	
}
