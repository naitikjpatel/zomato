package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.entity.RestaurantEntity;

public interface RestaurantRepository extends JpaRepository<RestaurantEntity, Integer> {

}
