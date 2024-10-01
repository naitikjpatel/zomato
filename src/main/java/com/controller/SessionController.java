package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.email.EmailSenderService;
import com.entity.CustomerEntity;
import com.repository.CustomerRepository;
import com.service.GeneralService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class SessionController {
	
	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	GeneralService generalService;
	
	@Autowired
	private EmailSenderService  service;
	
	
	@PostMapping("/customersignup")
	public CustomerEntity customerSignup(@RequestBody CustomerEntity customerEntity) {
		customerRepository.save(customerEntity);
		return customerEntity;
	}
	
	@GetMapping("/customerlogin/{email}/{password}")
	public boolean customerLogin(@PathVariable("email") String email,@PathVariable("password") String password) {

	CustomerEntity customer=customerRepository.findByEmailAndPassword(email, password);
	if(customer==null) {
		return false;
	}
	else {
		return true;
	}
	}
	
	
	@GetMapping("/custom-page")
	public String fpass() {
		System.out.println("customer-page");
		return "forgot password";
	}
	
	@GetMapping("/forgotpassword/{emailId}")
	public String forgotpassword(@PathVariable("emailId") String emailId) {
		
	CustomerEntity customer=customerRepository.findByEmail(emailId);
	if(customer==null) {
		return "Please SignUp First...";
	}
	else {

		int otp=generalService.generateSixDigitNumber();
		service.sendEmail("naitikj1011@gmail.com", "Java", ""+otp);
		return "redirect:/custom-page";
	}
		
	
	
	}
	

	
}
