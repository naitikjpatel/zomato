package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.entity.CustomerEntity;
import com.repository.CustomerRepository;

import jakarta.persistence.criteria.CriteriaBuilder.In;

@RestController
public class CustomerController {

	@Autowired
	CustomerRepository repository;
	
	
	//read all customers
	@GetMapping("/customers")
	public List<CustomerEntity>	getAllCustomers(){
	List<CustomerEntity>customers=repository.findAll();
	return customers;
	}
	
	
	//read customer by id
	@GetMapping("/customers/{customerId}")
	public CustomerEntity getCustomerById(@PathVariable Integer customerId) {
		Optional<CustomerEntity>op=repository.findById(customerId);
		if(op.isEmpty()) {
			return null;
		}
		else {
			return op.get();
		}
		
	}
	
	
	
	//delete customer by id
	@DeleteMapping("/customer/{customerId}")
	public CustomerEntity deleteCustomerById(@PathVariable Integer customerId) {
		Optional<CustomerEntity>op=repository.findById(customerId);
		
		if(op.isEmpty()) {
			return null;
		}
		else {
			CustomerEntity entity=op.get();
			repository.deleteById(customerId);
			return entity;
		}
	}
	
	//updatecustomer profile baki
}
