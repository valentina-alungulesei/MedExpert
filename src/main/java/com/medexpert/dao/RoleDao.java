package com.medexpert.dao;

import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.medexpert.entity.Role;

@Repository
public class RoleDao implements IRoleDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Role findRoleByName(String roleName) {
		// Get the current Hibernate session factory
		Session currentSession = sessionFactory.openSession();
		
		// Retrieve/read from DB using name
		Query<Role> query = currentSession.createQuery("FROM Role WHERE name=:rName", Role.class);
		query.setParameter("rName", roleName);
		
		Role role = null;
		try {
			role = query.getSingleResult();
		} catch (Exception e) {
			role = null;
		}
		
		return role;
	}
}
