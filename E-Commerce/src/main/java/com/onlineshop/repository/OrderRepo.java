package com.onlineshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlineshop.model.Order;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer>{

	
}
