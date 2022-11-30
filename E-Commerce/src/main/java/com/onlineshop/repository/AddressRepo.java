package com.onlineshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlineshop.model.Address;

@Repository
public interface AddressRepo extends JpaRepository<Address, Integer>{

	
}
