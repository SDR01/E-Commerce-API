package com.onlineshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlineshop.model.Products;

@Repository
public interface ProductRepo extends JpaRepository<Products, Integer>{

	public Products findByProductId(Integer i);
	
	public List<Products> findByCategory(String category);
}
