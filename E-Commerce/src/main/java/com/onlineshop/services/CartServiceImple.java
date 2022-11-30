package com.onlineshop.services;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineshop.exception.CustomerException;
import com.onlineshop.exception.LoginException;
import com.onlineshop.exception.ProductException;
import com.onlineshop.model.Cart;
import com.onlineshop.model.CurrentUserSession;
import com.onlineshop.model.Customer;
import com.onlineshop.model.Products;
import com.onlineshop.repository.CurrentUserSessionRepo;
import com.onlineshop.repository.CustomerRepo;
import com.onlineshop.repository.ProductRepo;

@Service
public class CartServiceImple implements CartService {
	
	@Autowired
	private ProductRepo pRepo;
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private CurrentUserSessionRepo cusRepo;
	
	@Override
	public String addProductToCart(Integer pid, Integer cid, String key) throws CustomerException, LoginException {
		Optional<Products> Spopt= pRepo.findById(pid);
		Customer customer= customerRepo.findByCustomerId(cid);
		if(customer == null) {
			throw new CustomerException("No Customer found with ID " + cid);
		}  
		CurrentUserSession RunningSession = cusRepo.findByUuid(key);
			if (RunningSession == null) {
				throw new LoginException("Invalid Key");
			}
			if(RunningSession.getUserId()!=customer.getCustomerId()) {
				throw new LoginException("Login Required");
			}
		  
		 if(Spopt.isPresent()&&customer!=null) {
			 Products sproduct=Spopt.get();
			 Products p= new Products();
			 System.out.println("Inside");
			 p.setCategory(sproduct.getCategory());
			 p.setColor(sproduct.getColor());
			 p.setManufactuer(sproduct.getManufactuer());
			 p.setPrice(sproduct.getPrice());
			 p.setProductName(sproduct.getProductName());
			 p.setQuantity(1);
			 if(customer.getCart()==null) {
				Cart c=new Cart();
				customer.setCart(c);
				c.setCustomer(customer);
				p.setCart(c);
				customer.getCart().getProductList().add(p);
				customerRepo.save(customer);
			 }else {
				customer.getCart().setCustomer(customer);
				p.setCart(customer.getCart());
				customer.getCart().getProductList().add(p);
				customerRepo.save(customer);	
			}	
			return "Added to cart";
		  }
		throw new CustomerException("Invalid Credentials..."); 
	}
	
	@Override
	public Products removeProductFromCart(Integer pid, Integer cid, String key) throws CustomerException, LoginException, ProductException {
		 Optional<Customer> customerOptional = customerRepo.findById(cid);
		 if(customerOptional.isEmpty()) {
			 throw new CustomerException("No Customer found with ID " + cid);
		 }
		 CurrentUserSession runningSession = cusRepo.findByUuid(key);
			if (runningSession == null) {
				throw new LoginException("Invalid Key");
			}
			if(runningSession.getUserId() != customerOptional.get().getCustomerId()) {
				throw new LoginException("Login Required");
			}
		 if(customerOptional.isPresent()) {
             Customer customer = customerOptional.get();
			 Cart customerCart = customer.getCart();
			 List<Products> list = customerCart.getProductList();
			 boolean flag = false;
			 for(int i=0; i<list.size(); i++){
				 if(list.get(i).getProductId() == pid) {
					 list.remove(list.get(i));
					 flag = true;
					 break;
				 }
			 }
			 customerCart.setProductList(list);
			 Optional<Products> productOptional = pRepo.findById(pid);
			 if(productOptional.isPresent()) {
				Products prod1 = productOptional.get();
				pRepo.delete(prod1);
				return prod1;
			 }
			 else {
				throw new ProductException("No Product found ID" + pid);
			 }
		 }
		throw new CustomerException("Invalid Customer ID" + cid);
	}

	@Override
	public Products updateProductQuantity(Integer cid, Integer pid, Integer quantity, String key) throws CustomerException, LoginException{		
		Optional<Customer> customerOptional = customerRepo.findById(cid);
		if(customerOptional.isEmpty()) {
			throw new CustomerException("No Customer found with ID " + cid);
		}
		CurrentUserSession runningSession = cusRepo.findByUuid(key);
			if (runningSession == null) {
				throw new LoginException("Invalid Key");
			}
			if(runningSession.getUserId()!=customerOptional.get().getCustomerId()) {
				throw new LoginException("Login Required");
			}
		if(customerOptional.isPresent()) {
			Customer cur = customerOptional.get(); 
			Cart cart_cus = cur.getCart();
			List<Products> li = cart_cus.getProductList();
			boolean flag = false;
			Products p = null;
			for(int i=0;i<li.size();i++){
				 if(li.get(i).getProductId()==pid) {
					 li.get(i).setQuantity(li.get(i).getQuantity()+quantity);
					 p=li.get(i);
					 flag=true;
					 break;
				 }
			 }
			if(!flag) {
				throw new CustomerException("Product Not found");
			}
			cart_cus.setProductList(li);
			cur.setCart(cart_cus);
			customerRepo.save(cur);
			return p;
		 }
		throw new CustomerException("Invalid customerId");
	}
	
}