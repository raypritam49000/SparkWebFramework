package com.spark.rest.api.services.impl;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.spark.rest.api.dbconfig.HibernateUtil;
import com.spark.rest.api.entity.User;
import com.spark.rest.api.services.UserService;

public class UserServiceImpl implements UserService {

	private Session session = HibernateUtil.getSession();

	@Override
	public User registerUser(User user) {
		Transaction trx = session.beginTransaction();
		Serializable id = session.save(user);
		trx.commit();
		return session.getReference(User.class, id);
	}

	@Override
	public User findByUsername(String username) {
		org.hibernate.Query query = session.createQuery("from User where username = :username");
		query.setParameter("username", username);
		return (User) query.uniqueResult();
	}

	@Override
	public User findByEmail(String email) {
		org.hibernate.Query query = session.createQuery("from User where email = :email");
		query.setParameter("email", email);
		return (User) query.uniqueResult();
	}

}
