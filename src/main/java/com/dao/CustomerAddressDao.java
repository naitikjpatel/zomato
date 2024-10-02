package com.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.CustomerAddressEntity;

import jakarta.persistence.Query;

@Repository
public class CustomerAddressDao {

	@Autowired
	SessionFactory sf;

	public List<CustomerAddressEntity> getCustomerAddressByCID(Integer customerId) {
		Session session = sf.openSession();
		Query addresses = session.createQuery("from CustomerAddressEntity c where c.customerId=: customerId",
				CustomerAddressEntity.class);
		addresses.setParameter("customerId", customerId);
		List<CustomerAddressEntity> list = ((org.hibernate.query.Query<CustomerAddressEntity>) addresses).list();
		return list;

	}

}
