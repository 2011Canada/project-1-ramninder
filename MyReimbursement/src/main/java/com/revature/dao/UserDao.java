package com.revature.dao;

import java.util.List;

import com.revature.models.User;

public interface UserDao {
	
	public List<User> findAllUser();
	
	public User findUserById(Integer id);
	
	public User findByUsername(String username);
	
	public User saveUser(User obj);
	
	public User updateUser(User obj);
	
	public void deleteUser(User obj);


}
