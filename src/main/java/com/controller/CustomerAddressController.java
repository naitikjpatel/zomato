package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.repository.CustomerAddressRepository;
import com.entity.*;

@RestController
public class CustomerAddressController {

	@Autowired
	CustomerAddressRepository customerAddressRepository;
	
	@PostMapping("/customeraddress")
	public CustomerAddressEntity CustomerAddressAdd(@RequestBody CustomerAddressEntity customerAddressEntity ) {
		
		customerAddressRepository.save(customerAddressEntity);
		return customerAddressEntity;
	}
	
}
