package com.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name="Restaurants")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RestaurantEntity {

	Integer restaurantId;
	String title;
	String category;
	String description;
	String timings;
	String address;
	String contactNum;
	Float latitude;
	Float longitude;
	Integer pincode;
	Integer online;
	String email;
	String password;
	Integer active;
	String restaurantImagePath;
}
