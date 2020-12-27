package com.revature.dao;

import java.util.List;

import com.revature.models.Roles;


public interface RoleDao {
	
	public List<Roles> findAllRoles();
	
	public Roles findByIdRoles(Integer id);
	
	
	public Roles saveRoles(Roles obj);
	
	public Roles updateRoles(Roles obj);
	
	public void deleteType(Roles obj);
	
}