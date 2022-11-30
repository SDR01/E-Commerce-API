package com.onlineshop.services;

import java.time.LocalDate;

import java.util.List;

import com.onlineshop.exception.CustomerException;
import com.onlineshop.exception.LoginException;
import com.onlineshop.exception.OrderException;
import com.onlineshop.model.Order;

public interface OrderService {
	
	public Order addOrder(Order order, String key)throws CustomerException,LoginException;
	
	public Order updateOrder(Order updateOrder, String Key)throws OrderException,CustomerException,LoginException;
	
	public Order removeOrder(Integer orderId)throws OrderException,CustomerException;
	
	public Order viewOrder(Integer orderId) throws OrderException;
	
	public List<Order> viewAllOrders(LocalDate date) throws OrderException;
	
	public List<Order> viewAllOrdersByUserId(Integer userid)throws CustomerException;
	

}
