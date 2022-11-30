package com.onlineshop.model;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Products {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int productId;
	
	private String productName;
	private int price;
	private String color;
	private String manufactuer;
	private String category;
	private int quantity;
	
	@JsonIgnore
	@ManyToOne
	private Admin admin;
	
	@JsonIgnore
	@ManyToOne(cascade = CascadeType.ALL)
	private Cart cart;
}
