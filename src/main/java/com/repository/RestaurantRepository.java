package com.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.CustomerEntity;
import com.entity.RestaurantEntity;

public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Integer> {

	RestaurantEntity findByEmailAndPassword(String email,String password);

	
	public RestaurantEntity findByEmail(String email);
}
