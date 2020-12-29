package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Roles;
import com.revature.util.ConnectionFactory;

public class RoleDaoImplementation implements RoleDao {
	
	private ConnectionFactory cf = ConnectionFactory.getConnectionFactory();


	@Override
	public List<Roles> findAllRoles() {
		List<Roles> roles = new ArrayList<>();
		
		try {
			Connection conn = cf.getConnection();
			
			String sql = "select * from \"ERS_USER_ROLES\";\n"
					+ "";
				Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
		while(rs.next()) {
			
			Roles role = new Roles();
			role.setId(rs.getInt("ERS_USER_ROLE_ID"));
			role.setRole(rs.getString(2));
			roles.add(role);
			
		}	
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return roles;
	}

	@Override
	public Roles findByIdRoles(Integer id) {
Roles role = null;
		
		try {
			Connection conn = cf.getConnection();

			String sql = "SELECT * FROM \"ERS_REIMBURSEMENT_TYPE\" WHERE \"REIMB_TYPE_ID\" = ?\n"
					+ "";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,  id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				role = new Roles();
				role.setId(rs.getInt(1));
				role.setRole(rs.getString(2));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return role;		
		
	}

	@Override
	public Roles saveRoles(Roles obj) {
		try {
			Connection conn = cf.getConnection();

			conn.setAutoCommit(false);
					
			String sql = "INSERT INTO \"ERS_USER_ROLES\" (\"USER_ROLE\") VALUES(?)\n"
					+ "";		
			String[] keyNames = {"ERS_USER_ROLE_ID"};
					
			PreparedStatement ps = conn.prepareStatement(sql, keyNames);
			ps.setString(1, obj.getRole());
					
			int numRows = ps.executeUpdate();
			if(numRows > 0) {
						
				ResultSet pk = ps.getGeneratedKeys();
						
				while(pk.next()) {
							
					obj.setId(pk.getInt(1));
							
				}
						
				conn.commit();
						
			}	
					
			} catch (SQLException e) {

				e.printStackTrace();
				
			}
				
			return obj;		
			
			
	}

	@Override
	public Roles updateRoles(Roles obj) {
		try {
			Connection conn = cf.getConnection();

			String sql = "UPDATE ERS_USER_ROLES SET USER_ROLE = ? WHERE ERS_USER_ROLE_ID = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,  obj.getRole());
			ps.setInt(2, obj.getId());
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return null;	
	}

	@Override
	public void deleteType(Roles obj) {
		// TODO Auto-generated method stub
		
	}

}
