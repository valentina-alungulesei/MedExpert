package com.medexpert.dao;

import java.util.List;
import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.medexpert.entity.User;

@Repository
public class UserDao implements IUserDao {
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	// Inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<User> findAll() {
		// Get the current Hibernate session
		Session currentSession = sessionFactory.openSession();
		
		// Retrieve/read all users from DB
		Query<User> query = currentSession.createQuery("SELECT a FROM User a", User.class);
		
		return query.getResultList();
	}
	
	@Override
	public User findByUsername(String username) {
		// Get the current Hibernate session
		Session currentSession = sessionFactory.openSession();
		
		// Retrieve/read from DB using username
		Query<User> query = currentSession.createQuery("FROM User WHERE username=:uName", User.class);
		query.setParameter("uName", username);
		
		User user = null;
		try {
			user = query.getSingleResult();
		} catch (Exception e) {
			user = null;
		}
		
		return user;
	}

	@Override
	public void save(User user) {
		// Get the current Hibernate session
		Session currentSession = sessionFactory.openSession();
		
		// Create the user
		currentSession.saveOrUpdate(user);
	}
}
