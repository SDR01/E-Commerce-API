package com.onlineshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.onlineshop.model.Admin;


@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer>{
	
	public Admin findByUserName(String username);
	
	public Admin findByUserNameAndPassword(String username,String password);
}
