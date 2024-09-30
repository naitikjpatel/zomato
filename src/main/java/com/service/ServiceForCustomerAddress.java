package com.service;

import java.util.List;

import com.entity.CustomerAddressEntity;

public interface ServiceForCustomerAddress {

	public List<CustomerAddressEntity> getCustomerAddressByCID(Integer customerId);
}
