package com.revature.service;

import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImplementation;
import com.revature.models.User;

public class UserService {
	
	static UserDao userD = new UserDaoImplementation();
	
	
	
	public User validateUser(String user, String password) {
		
		if(user.equals("") || password.equals("")) {
			System.out.println("went first if");

			return null;
		}
		
		else {
			
			System.out.println("went second else");

		User u = userD.findByUsername(user);
		if(u == null) {
			return null;
		}
		
		if(u.getPassword().equals(password) && u.getUsername().equals(user)) {
			
			return u;
			
		}
		
		}
		return null;
		
	}

}
