package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.CustomerEntity;

public interface CustomerRepository extends JpaRepository<CustomerEntity,Integer> {

	public CustomerEntity findByEmailAndPassword(String email,String password);
	
	public CustomerEntity findByEmail(String email);
}
