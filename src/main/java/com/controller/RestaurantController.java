package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.entity.RestaurantEntity;
import com.repository.RestaurantRepository;
import com.service.ServiceImplForRestaurant;

import jakarta.persistence.criteria.CriteriaBuilder.In;

@RestController
public class RestaurantController {

	@Autowired
	RestaurantRepository restaurantRepository;
	
	@Autowired
	ServiceImplForRestaurant service;
	
	//get all restaurants
	@GetMapping("/restaurants")
	public List<RestaurantEntity> getAllRestaurants(){
		List<RestaurantEntity> list=restaurantRepository.findAll();
		return list;
	}
	
	//add the restairant
	@PostMapping("/addrestaurant")
	public RestaurantEntity addRestaurant(@RequestBody RestaurantEntity entity) {
		restaurantRepository.save(entity);
		return entity;
	}
	
	// Read Restaurant by Id
	@GetMapping("/getrestaurants/{rid}")
	public RestaurantEntity getRestaurantsById(@PathVariable("rid") Integer rid) {
		Optional<RestaurantEntity> op=restaurantRepository.findById(rid);
		if (op.isEmpty()) {
			return null;
		}
		else {
			return op.get();
		}
	}
	// Read All Restaurant By Active
	
	@GetMapping("/getrestaurantsbyactive/{active}")
	public List<RestaurantEntity> getResturantsByActive(@PathVariable("active") Integer active){
		List<RestaurantEntity> list=service.getRestaurantByActive(active);
		return list;
		
	}
	
	
	//get the resturants which match with the pincode and active =1
	@GetMapping("/getabyactivepincode/{active}/{pincode}")
	public List<RestaurantEntity> getRestaurantByActiveAndPincode(@PathVariable("active") Integer active,@PathVariable("pincode") Integer pincode){
		List<RestaurantEntity> list=service.getRestaurantByActiveAndPincode(active, pincode);
		return list;
		
	}
}
