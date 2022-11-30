package com.onlineshop.services;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineshop.exception.ProductException;
import com.onlineshop.model.Products;
import com.onlineshop.repository.AdminRepo;
import com.onlineshop.repository.ProductRepo;

@Service
public class ProductServicesImpl implements ProductServices{

	@Autowired
	private ProductRepo pRepo;
	
	@Autowired
	private AdminRepo adminRepo;
	
	@Override
	public List<Products> viewAllProducts() throws ProductException {
		List<Products> allProducts = pRepo.findAll();
		if(allProducts.size() != 0) {			
			return allProducts;
		}
		else throw new ProductException("No Products found");
	}

	@Override
	public Products updateProduct(Products product, Integer sid) throws ProductException {
		Optional<Products> findProduct = pRepo.findById(product.getProductId());
		if(findProduct.isPresent()) {
			product.setAdmin(adminRepo.findById(sid).get());
			Products updatedProduct = pRepo.save(product);
			return updatedProduct;
		}
		else throw new ProductException("Invalid Product Details");
	}

	@Override
	public Products viewProductById(Integer productId) throws ProductException {
		return pRepo.findById(productId).orElseThrow(()-> new ProductException("No Product found with this ID: "+productId));
	}

	@Override
	public List<Products> viewProductByCategory(String category) throws ProductException {
		List<Products> products = pRepo.findByCategory(category);
		if(products.size() != 0) {
			return products;
		}
		else throw new ProductException("No Products found in this Category: "+category);
	}

	@Override
	public Products removeProduct(Integer productId) throws ProductException {
		Optional<Products> findOptional = pRepo.findById(productId);
		if(findOptional.isPresent()) {
			Products sProducts = pRepo.getById(productId);
			pRepo.delete(sProducts);
			return sProducts;
		}
		else {
			throw new ProductException("No Products found with ID: "+productId);
		}
	}

}
