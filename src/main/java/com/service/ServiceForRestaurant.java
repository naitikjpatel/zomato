package com.service;

import java.util.List;

import com.entity.RestaurantEntity;

public interface ServiceForRestaurant {
	
	public List<RestaurantEntity> getRestaurantByActive(Integer active);
	public List<RestaurantEntity> getRestaurantByActiveAndPincode(Integer active,Integer pincode);
}
