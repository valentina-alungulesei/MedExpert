package com.medexpert.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medexpert.entity.User;

public interface IUserRepository extends JpaRepository<User, Integer> {
	
	/* Spring Data JPA will parse the method name and will look
	 * for a specific format and pattern.
	 * It will create appropriate query behind the scenes.
	 */
	public List<User> findAllByOrderByLastNameAsc();
	
	public User findByUsername(String username);

}
