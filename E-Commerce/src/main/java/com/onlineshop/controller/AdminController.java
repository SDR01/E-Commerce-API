package com.onlineshop.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onlineshop.exception.AdminException;
import com.onlineshop.model.Admin;
import com.onlineshop.model.Products;
import com.onlineshop.services.AdminService;



@RestController
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	private AdminService ad;
	
	@PostMapping("/CreateAdmin")
	public ResponseEntity<Admin> createAccount(@Valid @RequestBody Admin admin) throws AdminException{
		Admin adm = ad.registerAdmin(admin);
		return new ResponseEntity<Admin>(adm,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteAdmin")
	public ResponseEntity<Admin> deleteAccount(@RequestParam String email,@RequestParam String password,@RequestParam(required = false) String Uuid) throws AdminException{
		Admin adm = ad.deleteAdmin(email, password, Uuid);
		return new ResponseEntity<Admin>(adm,HttpStatus.OK);
	}
	@PostMapping("/addProduct")
	public ResponseEntity<Products> addProd(@RequestBody Products product ,@RequestParam Integer adminId) throws AdminException{
		Products pro = ad.addProduct(product,adminId);
		return new ResponseEntity<Products>(pro,HttpStatus.OK); 
	}
}
