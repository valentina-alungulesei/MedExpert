package com.medexpert.dao;

import java.util.List;

import com.medexpert.entity.User;

public interface IUserDao {
	
	public List<User> findAll();
	
	public User findByUsername(String username);
	
	public void save(User user);
}
