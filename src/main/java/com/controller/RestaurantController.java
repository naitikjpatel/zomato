package com.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.email.EmailSenderService;
import com.email.OtpData;
import com.entity.CustomerEntity;
import com.entity.RestaurantEntity;
import com.repository.RestaurantRepository;
import com.service.GeneralService;
import com.service.ServiceImplForRestaurant;

import jakarta.persistence.criteria.CriteriaBuilder.In;

@RestController
public class RestaurantController {

	@Autowired
	RestaurantRepository restaurantRepository;

	@Autowired
	ServiceImplForRestaurant service;
	
	@Autowired
	GeneralService generalService;

	@Autowired
	private EmailSenderService eservice;
	
	public HashMap<String, OtpData> otpStore=new HashMap<>();

	// get all restaurants
	@GetMapping("/restaurants")
	public List<RestaurantEntity> getAllRestaurants() {
		List<RestaurantEntity> list = restaurantRepository.findAll();
		return list;
	}

	// add the restairant
	@PostMapping("/addrestaurant")
	public RestaurantEntity addRestaurant(@RequestBody RestaurantEntity entity) {
		restaurantRepository.save(entity);
		return entity;
	}

	// login restaurant
	@GetMapping("/restaurantlogin/{email}/{password}")
	public boolean restaurantLogin(@PathVariable("email") String email, @PathVariable("password") String password) {
		RestaurantEntity entity = restaurantRepository.findByEmailAndPassword(email, password);
		if (entity == null) {
			return false;
		} else {
			return true;
		}
	}

	// Read Restaurant by Id
	@GetMapping("/getrestaurants/{rid}")
	public RestaurantEntity getRestaurantsById(@PathVariable("rid") Integer rid) {
		Optional<RestaurantEntity> op = restaurantRepository.findById(rid);
		if (op.isEmpty()) {
			return null;
		} else {
			return op.get();
		}
	}
	// Read All Restaurant By Active

	@GetMapping("/getrestaurantsbyactive/{active}")
	public List<RestaurantEntity> getResturantsByActive(@PathVariable("active") Integer active) {
		List<RestaurantEntity> list = service.getRestaurantByActive(active);
		return list;

	}

	// get the resturants which match with the pincode and active =1
	@GetMapping("/getabyactivepincode/{active}/{pincode}")
	public List<RestaurantEntity> getRestaurantByActiveAndPincode(@PathVariable("active") Integer active,
			@PathVariable("pincode") Integer pincode) {
		List<RestaurantEntity> list = service.getRestaurantByActiveAndPincode(active, pincode);
		return list;

	}
	
	@PostMapping("/rforgotpassword/{emailId}")
	public String forgotPassword(@PathVariable("emailId") String emailId) {
		RestaurantEntity restaurantEntity = restaurantRepository.findByEmail(emailId);
		if (restaurantEntity == null) {
			return "Please SignUp First...";
		} 

			int otp = generalService.generateSixDigitNumber();
			
			//make the object of the otpdata and put into the hashmap
			OtpData otpdata=new OtpData(emailId, otp);
			otpStore.put(emailId, otpdata);
			
			
//			service.sendEmail("njpatel20031011@gmail.com", "Form Nj Pvt.ltd", "" + otp);
			eservice.sendEmail(emailId, "Form Nj Pvt.ltd", "" + otp);
			return "Email Sent SuccessFully..."+otp;
	}
		
	@PostMapping("/rresetpassword/{emailId}/{newpassword}/{otp}")
	public String resetPassword(@PathVariable("emailId") String emailId,@PathVariable("newpassword") String newpassword,@PathVariable("otp") Integer code) {
		OtpData otpdata=otpStore.get(emailId);
		int otp=otpdata.getOtp();
		if(otp!=code) {
			return "InValid OTP...";
		}
		RestaurantEntity restaurantEntity=restaurantRepository.findByEmail(emailId);
		restaurantEntity.setPassword(newpassword);
		restaurantRepository.save(restaurantEntity);
		eservice.sendEmail(emailId, "About Password Updated..", "Your Password SuccessFully Changed...\nNot share your otp and pin with anybody...");
		return "Reset Password SuccessFully...";
	}
}
