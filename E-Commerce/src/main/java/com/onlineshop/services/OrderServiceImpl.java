package com.onlineshop.services;

import java.time.LocalDate;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineshop.exception.CustomerException;
import com.onlineshop.exception.LoginException;
import com.onlineshop.exception.OrderException;
import com.onlineshop.model.CurrentUserSession;
import com.onlineshop.model.Customer;
import com.onlineshop.model.Order;
import com.onlineshop.model.Products;
import com.onlineshop.repository.CartRepo;
import com.onlineshop.repository.CurrentUserSessionRepo;
import com.onlineshop.repository.CustomerRepo;
import com.onlineshop.repository.OrderRepo;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	CustomerRepo customerRepo;
	
	@Autowired
	OrderRepo orderRepo;
	
	@Autowired
	CurrentUserSessionRepo cusRepo;
	
	@Autowired
	CartRepo cartRepo;
	
	@Override
	public Order addOrder(Order order, String key)throws CustomerException,LoginException{
		Customer customer_relatedTo_order = loginValidation(key);
		Integer customerId = customer_relatedTo_order.getCustomerId();
		Customer fetchedCustomerDb = customerRepo.findById(customerId).orElseThrow(()-> new CustomerException("No such customer with the given id exists in the database"));
		List<Products> product_list = fetchedCustomerDb.getCart().getProductList();
		List<Products> newProductList = new ArrayList<>();
		for(Products ele : product_list) {
			ele.setCart(null);		
			newProductList.add(ele);
		}
		order.setListOfProducts(newProductList);
		order.setCustomer(fetchedCustomerDb);
		fetchedCustomerDb.getCart().setProductList(new ArrayList<>());
		fetchedCustomerDb.getOrderList().add(order);
		Order ord= orderRepo.save(order);
		customerRepo.save(fetchedCustomerDb);
		return ord;
	}
	
	public Customer loginValidation(String key) throws LoginException,CustomerException{
		CurrentUserSession checkCustomer = cusRepo.findByUuid(key);
		if(checkCustomer == null) throw new LoginException("Customer not logged in");
		Customer loggedCustomer  = customerRepo.findById(checkCustomer.getUserId()).orElseThrow(()-> new CustomerException("No Such Customer in Db"));
		return loggedCustomer;
	}
	
	@Override
	public Order updateOrder(Order updateOrder,String key) throws OrderException,CustomerException,LoginException{
		Customer fetchedCustomerFromKey = loginValidation(key);
		Order fetchedOrderDb = orderRepo.findById(updateOrder.getOrderId()).orElseThrow(()-> new OrderException("No such order to update in the DB"));
		Customer fetchedCustomerDb = customerRepo.findById(fetchedCustomerFromKey.getCustomerId()).orElseThrow(()-> new CustomerException("No such customer with the given id exists in the database"));
		List<Order> listOfOrders = fetchedCustomerFromKey.getOrderList();
		for(Order orderEle : listOfOrders) {
			if(orderEle.getOrderId() == updateOrder.getOrderId()) {
				orderEle.setOrderStatus(updateOrder.getOrderStatus());
				orderEle.setListOfProducts(updateOrder.getListOfProducts());
				orderEle.setCustomer(fetchedCustomerDb);
				break;
			}
		}
		orderRepo.save(updateOrder);
		return updateOrder;
	}

	@Override
	public Order removeOrder(Integer orderId) throws OrderException, CustomerException{
		Order fetchedOrderFromDb = orderRepo.findById(orderId).orElseThrow(()-> new OrderException("No such order int the database"));
		Customer fetchedCustomer = customerRepo.findById(fetchedOrderFromDb.getCustomer().getCustomerId()).orElseThrow(()-> new CustomerException("Customer in the order mismatch"));
		orderRepo.delete(fetchedOrderFromDb);
		return fetchedOrderFromDb;
	}

	@Override
	public Order viewOrder(Integer orderId) throws OrderException{
		Order fetchedOrder = orderRepo.findById(orderId).orElseThrow(()-> new OrderException("No such order exists in the Database"));
		Order orderDto = new Order(fetchedOrder.getOrderId(),fetchedOrder.getOrderDate(),fetchedOrder.getOrderStatus(),fetchedOrder.getOrderAmount(),fetchedOrder.getCustomer(),fetchedOrder.getListOfProducts(),fetchedOrder.getCustomer().getAddress(), fetchedOrder.getPayment());
		return orderDto;
	}

	@Override
	public List<Order> viewAllOrders(LocalDate date) throws OrderException{
		List<Order> listOfOrder = orderRepo.findAll();
		if(listOfOrder.isEmpty()) { 
			throw new OrderException("There are No orders in the database");
		}
		List<Order> OrderListByDate = new ArrayList<>();
		for(Order OrderEle : listOfOrder) {
			if(OrderEle.getOrderDate() == date) {
				OrderListByDate.add(OrderEle);
			}
		}
		return OrderListByDate;
	}


	@Override
	public List<Order> viewAllOrdersByUserId(Integer userid) throws CustomerException{
		Customer fetchCustomer = customerRepo.findById(userid).orElseThrow(()-> new CustomerException("no such user in DataBase"));
		return fetchCustomer.getOrderList();
	}

}
