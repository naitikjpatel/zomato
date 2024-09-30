package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.RestaurantDao;
import com.entity.RestaurantEntity;

@Service
public class ServiceImplForRestaurant implements ServiceForRestaurant {

	@Autowired
	RestaurantDao dao;
	
	@Override
	public List<RestaurantEntity> getRestaurantByActive(Integer active) {
		// TODO Auto-generated method stub
		return dao.getRestaurantByActive(active);
	}

	@Override
	public List<RestaurantEntity> getRestaurantByActiveAndPincode(Integer active, Integer pincode) {
		// TODO Auto-generated method stub
		return dao.getRestaurantByActiveAndPincode(active, pincode);
	}

}
