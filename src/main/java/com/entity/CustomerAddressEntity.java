package com.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "customerAddress")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class CustomerAddressEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer addressId;
//	Integer customerId;
	String title;
	String addressLine;
	Integer pincode;
	Float latitude;
	Float longitude;

//	Here we make the realtion between the CustomerAddressEntity and the CustomerEntity where we make the relation 1: m and m:1 
//	Example : one customer have the multiple Address

//	Here we want the foreign key

	@ManyToOne
	@JoinColumn(name = "customerId")
	CustomerEntity customer;

}
