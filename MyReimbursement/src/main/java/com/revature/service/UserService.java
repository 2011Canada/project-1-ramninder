package com.revature.service;

import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImplementation;
import com.revature.models.User;

public class UserService {
	
	static UserDao userD = new UserDaoImplementation();
	
	
	
	public User validateUser(String user, String password) {

		User u = userD.findByUsername(user);
		
		if(u.getPassword().equals(password)) {
			
			return u;
			
		}
	
		return null;
		
	}

}
