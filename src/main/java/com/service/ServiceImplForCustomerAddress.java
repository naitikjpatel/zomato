package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.CustomerAddressDao;
import com.entity.CustomerAddressEntity;

@Service
public class ServiceImplForCustomerAddress implements ServiceForCustomerAddress {

	@Autowired
	CustomerAddressDao dao;
	
	@Override
	public List<CustomerAddressEntity> getCustomerAddressByCID(Integer customerId) {
		// TODO Auto-generated method stub
		return dao.getCustomerAddressByCID(customerId);
	}

}
