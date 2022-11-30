package com.onlineshop.services;

import java.util.*;

import com.onlineshop.exception.ProductException;
import com.onlineshop.model.Products;

public interface ProductServices {
	
	public List<Products> viewAllProducts() throws ProductException;

	public Products updateProduct(Products product, Integer sellerID) throws ProductException;
	
	public Products viewProductById(Integer productId) throws ProductException;
	
	public List<Products> viewProductByCategory(String category) throws ProductException;
	
	public Products removeProduct(Integer productId) throws ProductException;

}
