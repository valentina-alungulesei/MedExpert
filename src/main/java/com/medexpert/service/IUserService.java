package com.medexpert.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.medexpert.crm.CrmUser;
import com.medexpert.entity.Test;
import com.medexpert.entity.User;

public interface IUserService extends UserDetailsService {
	
	public List<User> findAll();
	
	public User findByUsername(String username);
	
	public User getLoggedInUser();
	
	public void save(CrmUser crmUser);

//	public User findById(int id);
//	
//	public void save(User user);
//	
//	public void deleteById(int id);
	
	List<Test> getTestList();
}
