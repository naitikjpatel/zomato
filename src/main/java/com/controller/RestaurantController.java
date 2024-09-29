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

import jakarta.persistence.criteria.CriteriaBuilder.In;

@RestController
public class RestaurantController {

	@Autowired
	RestaurantRepository restaurantRepository;
	
	
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
	public String getMethodName(@RequestParam String param) {
		return new String();
	}
	
}
