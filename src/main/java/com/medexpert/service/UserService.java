package com.medexpert.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medexpert.crm.CrmUser;
import com.medexpert.dao.IRoleDao;
import com.medexpert.dao.IUserDao;
import com.medexpert.dao.IUserRepository;
import com.medexpert.entity.Role;
import com.medexpert.entity.Test;
import com.medexpert.entity.User;
import com.medexpert.util.TestsDataReader;
import com.medexpert.util.TestsStore;

@Service
public class UserService implements IUserService {
	
	private Logger logger = Logger.getLogger(getClass().getName());
	
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IRoleDao roleDao;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	private List<Test> testsList;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userDao.findByUsername(username);
		logger.info("UserService -----------> loadUserByUsername() username: \"" + username + "\"");
		
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new org.springframework.security.core.userdetails.User(
				user.getUsername().toLowerCase(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
	
	@Override
	@Transactional
	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}
	
	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}
	
	@Override
	@Transactional
	public void save(CrmUser crmUser) {
		User user = new User();
		
		// Assign user details to the user object
		user.setUsername(crmUser.getUsername());
		user.setPassword(passwordEncoder.encode(crmUser.getFirstName()));
		user.setFirstName(crmUser.getFirstName());
		user.setLastName(crmUser.getLastName());
		user.setEmail(crmUser.getEmail());
		
		// Give user default role of "client"
		user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_CLIENT")));
		
		// Save user in DB
		userDao.save(user);
		
		logger.info("User role is:");
		for (Role role : user.getRoles()) {
			logger.info(role.getName());
		}
		
		logger.info("Saved user: " + user);
	}
	

//	
//	@Autowired
//	public UserServiceImpl(UserRepository userRepository) {
//		this.userRepository = userRepository;
//	}
//
//	@Override
//	public List<User> findAll() {
//		return userRepository.findAllByOrderByLastNameAsc();
//	}
//
//	@Override
//	public User findById(int id) {
//		
//		Optional<User> result = userRepository.findById(id);
//		
//		User User = null;
//		if (result.isPresent()) {
//			User = result.get();
//		}
//		else {
//			// didn't find the user
//			throw new RuntimeException("Did not find the User id: " + id);
//		}
//		
//		return User;
//	}
//
//	@Override
//	public void save(User User) {
//		userRepository.save(User);
//	}
//
//	@Override
//	public void deleteById(int id) {
//		userRepository.deleteById(id);
//	}
//
	@Override
	public List<Test> getTestList() {
		if (testsList == null) {
			testsList = new ArrayList<>();
			TestsDataReader.getInstance().readFromFile("src/main/resources/static/doc/tests.txt");
			testsList = TestsStore.getInstance().getTestsList();
		}
		
		return testsList;
	}
}
