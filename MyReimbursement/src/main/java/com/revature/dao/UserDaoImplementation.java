package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.User;
import com.revature.util.ConnectionFactory;

public class UserDaoImplementation implements UserDao{
	
	
	private ConnectionFactory cf = ConnectionFactory.getConnectionFactory();


	@Override
	public List<User> findAllUser() {
		List<User> users = new ArrayList<>();
		
		try {
			Connection conn = cf.getConnection();
			
			String sql = "select * from \"ERS_USERS\";\n"
					+ "";
				Statement stmt = conn.createStatement();
			ResultSet res = stmt.executeQuery(sql);
			
		while(res.next()) {
			
			User temp = new User();
			temp.setId(res.getInt(1));
			temp.setUsername(res.getString(2));
			temp.setPassword(res.getString(3));
			temp.setFirstName(res.getString(4));
			temp.setLastName(res.getString(5));
			temp.setEmail(res.getString(6));
			temp.setRole_id(res.getInt(7));
			users.add(temp);
		}	
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return users;

	}

	@Override
	public User findUserById(Integer id) {
		User user = null;
		
		try
		{
			Connection conn = cf.getConnection();
			
			String sql = "SELECT * FROM \"ERS_USERS\" WHERE \"ERS_USERS_ID\" = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,  id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				user = new User();
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setFirstName(rs.getString(4));
				user.setLastName(rs.getString(5));
				user.setEmail(rs.getString(6));
				user.setRole_id(rs.getInt(7));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return user;
	}

	@Override
	public User findByUsername(String username) {
User user = null;
		
		try(Connection conn = cf.getConnection()) {
			
			String sql = "SELECT * FROM ERS_USERS WHERE ERS_USERNAME = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,  username);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				user = new User();
				user.setId(rs.getInt(1));
				user.setUsername(username);
				user.setPassword(rs.getString(3));
				user.setFirstName(rs.getString(4));
				user.setLastName(rs.getString(5));	
				user.setEmail(rs.getString(6));
				user.setRole_id(rs.getInt(7));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return user;

	}

	@Override
	public User saveUser(User obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUser(User obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(User obj) {
		// TODO Auto-generated method stub
		
	}

}
