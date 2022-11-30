package com.onlineshop.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onlineshop.exception.CustomerException;
import com.onlineshop.exception.LoginException;
import com.onlineshop.model.Products;
import com.onlineshop.services.CartService;


@RestController
@RequestMapping(value = "/cart")
public class CartController {
	
	@Autowired
	private CartService cartservice;
	
	
	@PutMapping("/addProduct")
	public ResponseEntity<String> AddProductToCart(@RequestParam Integer pid,@RequestParam Integer custid,@RequestParam String key) throws CustomerException, LoginException{
		String s= cartservice.addProductToCart(pid, custid, key);
		return new ResponseEntity<String>(s,HttpStatus.ACCEPTED);
	}
	
	
	@PutMapping("/removeProduct")
	public ResponseEntity<Products> removeProductFromCart(@RequestParam Integer pid,@RequestParam String key,@RequestParam Integer cid) throws Exception{
		Products c= cartservice.removeProductFromCart(pid, cid, key);
		return new ResponseEntity<Products>(c,HttpStatus.ACCEPTED);
	}

	@PutMapping("/updateQuantity")
	public ResponseEntity<Products> UpdateProductQuantity(@RequestParam Integer cid,@RequestParam Integer pid,@RequestParam Integer quantity,@RequestParam String key) throws Exception{
		Products c= cartservice.updateProductQuantity(cid, pid, quantity, key);
		return new ResponseEntity<Products>(c,HttpStatus.ACCEPTED);
	}
	
}
