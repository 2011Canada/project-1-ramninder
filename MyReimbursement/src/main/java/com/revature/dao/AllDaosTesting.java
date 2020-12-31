package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.models.Status;
import com.revature.models.User;

public class AllDaosTesting {

//	public static void main(String[] args) throws SQLException {
//		
//		
//		userDao();
//
//	}
//	
	
	
	public static void userDao() throws SQLException {
		
		UserDao u = new UserDaoImplementation();
		
		User u3 = u.findByUsername("Ajit");
		//User u2 = u.findUserById(1);
		System.out.println(u3);
		
		
//		//finding all users		
//	List<User> u1 = u.findAllUser();		
//	System.out.println(u1);
		
		
		//insert status
		
//		StatusDao s = new StatusDaoImplementation();
//		
//		Status s1 = new Status();
//		
//		s1.setStatus("Pending");
//		
//		//s.saveStatus(s1);	
//		
//		System.out.println(s.findAllStatus());
	}

}
