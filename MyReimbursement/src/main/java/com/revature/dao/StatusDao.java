package com.revature.dao;

import java.util.List;

import com.revature.models.Status;


public interface StatusDao {
	
	public List<Status> findAllStatus();
	
	public Status findByStatusId(Integer id);
	

	public Status saveRoles(Status obj);
	
	public Status updateRoles(Status obj);
	
	public void deleteType(Status obj);
	


}
