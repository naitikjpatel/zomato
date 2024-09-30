package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.repository.CustomerAddressRepository;
import com.service.ServiceForCustomerAddress;
import com.service.ServiceImplForCustomerAddress;
import com.entity.*;

@RestController
public class CustomerAddressController {

	@Autowired
	CustomerAddressRepository customerAddressRepository;
	
	@Autowired
	ServiceImplForCustomerAddress service;
	
	@PostMapping("/customeraddress")
	public CustomerAddressEntity CustomerAddressAdd(@RequestBody CustomerAddressEntity customerAddressEntity ) {
		
		customerAddressRepository.save(customerAddressEntity);
		return customerAddressEntity;
	}
	
	
	//input : customerId and return the all addresss which have the customerId matched
	
	@GetMapping("/myaddress/{c_id}")
	public List<CustomerAddressEntity> getAllAddressByCID(@PathVariable("c_id") Integer customerId){
		return service.getCustomerAddressByCID(customerId);
	}
	
}
