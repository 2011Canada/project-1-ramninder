package com.revature.dao;

import java.util.List;

import com.revature.models.User;

public class AllDaosTesting {

	public static void main(String[] args) {
		
		
		userDao();

	}
	
	
	
	
	public static void userDao() {
		
		UserDao u = new UserDaoImplementation();
	
		
		//find userby Id
		
		User u2 = u.findUserById(1);
		
		
		System.out.println(u2);
		
		
//		//finding all users		
//	List<User> u1 = u.findAllUser();		
//	System.out.println(u1);
		
	}

}
