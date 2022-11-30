package com.onlineshop.services;

import org.springframework.stereotype.Service;

import com.onlineshop.exception.CartException;
import com.onlineshop.exception.CustomerException;
import com.onlineshop.exception.LoginException;
import com.onlineshop.exception.ProductException;
import com.onlineshop.model.Products;

@Service
public interface CartService {
	
	public String addProductToCart(Integer pid, Integer cid, String key) throws CustomerException,LoginException;
	
	public Products removeProductFromCart(Integer pid, Integer cid, String key) throws CustomerException, LoginException, ProductException;
	
	public Products updateProductQuantity(Integer cid, Integer pid, Integer quantity, String key) throws CustomerException, LoginException;
	
	
}