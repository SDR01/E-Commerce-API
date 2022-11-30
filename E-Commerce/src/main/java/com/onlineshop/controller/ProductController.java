package com.onlineshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onlineshop.exception.ProductException;
import com.onlineshop.model.Products;
import com.onlineshop.services.ProductServices;

@RestController
public class ProductController {
	
	@Autowired
	ProductServices productServices;
	
	@GetMapping("/product")
    public ResponseEntity<List<Products>> viewProductsList() throws ProductException{
    	List<Products> allProducts = productServices.viewAllProducts();
    	return new ResponseEntity<List<Products>>(allProducts, HttpStatus.OK);
    }
	
    @PutMapping("/product/update/")
    public ResponseEntity<Products> updateProduct(@RequestBody Products product, @RequestParam Integer sid) throws ProductException{
    	Products updatedProduct = productServices.updateProduct(product,sid);
    	return new ResponseEntity<Products>(updatedProduct, HttpStatus.ACCEPTED);
    }
    
    @GetMapping("/product/id/{productId}")
    public ResponseEntity<Products> viewProductByID(@PathVariable("productId") Integer productId) throws ProductException{
    	Products existingProduct = productServices.viewProductById(productId);
    	return new ResponseEntity<Products>(existingProduct, HttpStatus.OK);
    }
    
    @GetMapping("/product/category/{category}")
    public ResponseEntity<List<Products>> viewProductByCategory(@PathVariable("category") String category) throws ProductException{
    	List<Products> existingProduct = productServices.viewProductByCategory(category);
    	return new ResponseEntity<List<Products>>(existingProduct, HttpStatus.OK);
    }
    
    @DeleteMapping("/product/delete/{productId}")
    public ResponseEntity<Products> deleteProductByID(@PathVariable("productId") Integer productId) throws ProductException{
    	Products deletedProduct = productServices.removeProduct(productId);
    	return new ResponseEntity<Products>(deletedProduct, HttpStatus.OK);
    }
}
