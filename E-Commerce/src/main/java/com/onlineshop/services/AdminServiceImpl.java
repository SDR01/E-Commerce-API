package com.onlineshop.services;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineshop.exception.AdminException;
import com.onlineshop.model.Admin;
import com.onlineshop.model.Products;
import com.onlineshop.repository.AdminRepo;
import com.onlineshop.repository.ProductRepo;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminRepo aRepo;
	
	@Autowired
	private ProductRepo pRepo;

	@Override
	public Admin registerAdmin(Admin admin) throws AdminException {
		Admin a1 = aRepo.findByUserName(admin.getUserName());
		if(a1 == null) {
			Admin ad = aRepo.save(admin);
			return ad;
		}
		else {
			throw new AdminException("Admin already Registered");
		}
	}

	@Override
	public Admin deleteAdmin(String username, String password, String key) throws AdminException {
		Admin a1 = aRepo.findByUserNameAndPassword(username, password);
		if(a1 == null) {
			throw new AdminException("Username or Password is Incorrect");
		}
		else aRepo.delete(a1);
		return a1;
	}

	@Override
	public Products addProduct(Products product, Integer id) throws AdminException {
		Optional<Admin> a1 = aRepo.findById(id);
		if(a1.isPresent()) {
			Admin admin = a1.get();
			admin.getList().add(product);
			product.setAdmin(admin);
			Products p1 = pRepo.save(product);
			return p1;
		}
		else throw new AdminException("Invalid ID");
	}

}
