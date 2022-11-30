package com.onlineshop.services;

import com.onlineshop.exception.AdminException;
import com.onlineshop.model.Admin;
import com.onlineshop.model.Products;

public interface AdminService {
	
	public Admin registerAdmin(Admin admin) throws AdminException ;
	
	public Admin deleteAdmin(String username, String password, String key) throws AdminException ;
	
	public Products addProduct(Products product,Integer id) throws AdminException;

}
