package com.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.RestaurantEntity;

import jakarta.persistence.Query;

@Repository
public class RestaurantDao {

	@Autowired
	SessionFactory sf;

	public List<RestaurantEntity> getRestaurantByActive(Integer active) {
		Session session = sf.openSession();
		Transaction tr = session.beginTransaction();
		Query restaurants = session.createQuery("from RestaurantEntity r where r.active=:active",
				RestaurantEntity.class);
		restaurants.setParameter("active", active);
		List<RestaurantEntity> list = ((org.hibernate.query.Query<RestaurantEntity>) restaurants).list();
		tr.commit();
		session.close();
		return list;

	}

	public List<RestaurantEntity> getRestaurantByActiveAndPincode(Integer active, Integer pincode) {
		Session session = sf.openSession();
		Query restaurants = session.createQuery(
				"from RestaurantEntity r where r.active =: active and r.pincode =: pincode", RestaurantEntity.class);
		restaurants.setParameter("active", active);
		restaurants.setParameter("pincode", pincode);
		List<RestaurantEntity> list = ((org.hibernate.query.Query<RestaurantEntity>) restaurants).list();
		return list;
	}
}
