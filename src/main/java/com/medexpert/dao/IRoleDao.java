package com.medexpert.dao;

import com.medexpert.entity.Role;

public interface IRoleDao {
	
	public Role findRoleByName(String roleName);
}
