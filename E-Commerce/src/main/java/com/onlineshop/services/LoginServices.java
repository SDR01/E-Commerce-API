package com.onlineshop.services;

import com.onlineshop.exception.LoginException;
import com.onlineshop.model.CurrentUserSession;
import com.onlineshop.model.Login;

public interface LoginServices {

	public CurrentUserSession login(Login log) throws LoginException;
	
	public String logout(String uuid) throws LoginException;
}
