package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.models.Status;


public interface StatusDao {
	
	public List<Status> findAllStatus() throws SQLException;
	
	public Status findByStatusId(Integer id);
	

	public Status saveStatus(Status obj);
	
	public Status updateStatus(Status obj);
	
	public void deleteStatus(Status obj);
	


}
