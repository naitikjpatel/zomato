package com.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.email.EmailSenderService;
import com.email.OtpData;
import com.entity.CustomerEntity;
import com.repository.CustomerRepository;
import com.service.GeneralService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class SessionController {

	@Autowired
	CustomerRepository customerRepository;
	@Autowired
	GeneralService generalService;

	@Autowired
	private EmailSenderService service;
	
	public HashMap<String, OtpData> otpStore=new HashMap<>();
	

	@PostMapping("/customersignup")
	public CustomerEntity customerSignup(@RequestBody CustomerEntity customerEntity) {
		customerRepository.save(customerEntity);
		return customerEntity;
	}

	@GetMapping("/customerlogin/{email}/{password}")
	public boolean customerLogin(@PathVariable("email") String email, @PathVariable("password") String password) {

		CustomerEntity customer = customerRepository.findByEmailAndPassword(email, password);
		if (customer == null) {
			return false;
		} else {
			return true;
		}
	}

	@GetMapping("/custom-page")
	public String fpass() {
		System.out.println("customer-page");
		return "forgot password";
	}
/*
	@GetMapping("/forgotpassword/{emailId}/{newpassword}")
	public String forgotpassword(@PathVariable("emailId") String emailId,
			@PathVariable("newpassword") String password) {

		CustomerEntity customer = customerRepository.findByEmail(emailId);
		if (customer == null) {
			return "Please SignUp First...";
		} else {

			int otp = generalService.generateSixDigitNumber();
			service.sendEmail(emailId, "Form Nj Pvt.ltd", "" + otp);

			return "redirect:/custom-page";
		}

	}
*/
	@PostMapping("/forgotpassword/{emailId}")
	public String forgotPassword(@PathVariable("emailId") String emailId) {
		CustomerEntity customer = customerRepository.findByEmail(emailId);
		if (customer == null) {
			return "Please SignUp First...";
		} 

			int otp = generalService.generateSixDigitNumber();
			
			//make the object of the otpdata and put into the hashmap
			OtpData otpdata=new OtpData(emailId, otp);
			otpStore.put(emailId, otpdata);
			
			
//			service.sendEmail("njpatel20031011@gmail.com", "Form Nj Pvt.ltd", "" + otp);
			service.sendEmail(emailId, "Form Nj Pvt.ltd", "" + otp);
			return "Email Sent SuccessFully..."+otp;
	}
		
	@PostMapping("/resetpassword/{emailId}/{newpassword}/{otp}")
	public String resetPassword(@PathVariable("emailId") String emailId,@PathVariable("newpassword") String newpassword,@PathVariable("otp") Integer code) {
		OtpData otpdata=otpStore.get(emailId);
		int otp=otpdata.getOtp();
		if(otp!=code) {
			return "InValid OTP...";
		}
		CustomerEntity customer=customerRepository.findByEmail(emailId);
		customer.setPassword(newpassword);
		customerRepository.save(customer);
		return "Reset Password SuccessFully...";
	}
}
