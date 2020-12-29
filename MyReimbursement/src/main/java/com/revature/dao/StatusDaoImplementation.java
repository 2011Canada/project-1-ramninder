package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Roles;
import com.revature.models.Status;
import com.revature.util.ConnectionFactory;

public class StatusDaoImplementation implements StatusDao {

	private ConnectionFactory cf = ConnectionFactory.getConnectionFactory();

	@Override
	public List<Status> findAllStatus() throws SQLException {
		
		List<Status> status = new ArrayList<>();
		
		try {
			Connection conn = cf.getConnection();
			
			String sql = "select * from \"ERS_REIMBURSEMENT_STATUS\";\n"
					+ "";
				Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
		while(rs.next()) {
			Status stat = new Status();
			stat.setId(rs.getInt("REIMB_STATUS_ID"));
			stat.setStatus(rs.getString(2));
			status.add(stat);
			
		}	
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return status;
	
	}
	@Override
	public Status findByStatusId(Integer id) {
		Status stat = null;
		
		try  {
			
			Connection conn = cf.getConnection();
			
			String sql = "SELECT * FROM \"ERS_REIMBURSEMENT_TYPE\" WHERE \"REIMB_TYPE_ID\" = ?\n"
					+ "";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,  id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				stat = new Status();
				stat.setId(rs.getInt(1));
				stat.setStatus(rs.getString(2));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return stat;
	}

	@Override
	public Status saveStatus(Status obj) {
		try {
			
			Connection conn = cf.getConnection();
	
			String sql = "INSERT INTO \"ERS_REIMBURSEMENT_STATUS\" (\"REIMB_STATUS\") VALUES(?)\n"
					+ "";
			
			String[] keyNames = {"REIMB_STATUS_ID"};
			PreparedStatement ps = conn.prepareStatement(sql, keyNames);
			ps.setString(1, obj.getStatus());
					
			int numRows = ps.executeUpdate();
			
			if(numRows > 0) {
						
				ResultSet pk = ps.getGeneratedKeys();
						
				while(pk.next()) {
							
					obj.setId(pk.getInt(1));
							
				}
						
						
			}	
					
			} catch (SQLException e) {

				e.printStackTrace();
				
			}
				
			return obj;


	}

	@Override
	public Status updateStatus(Status obj) {
		try {
			
			Connection conn = cf.getConnection();

			String sql = "UPDATE \"ERS_REIMBURSEMENT_TYPE\" SET \"REIMB_STATUS\" = ? WHERE \"REIMB_STATUS_ID\" = ?\n"
					+ "";
				
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,  obj.getStatus());
			ps.setInt(2, obj.getId());
			ps.executeUpdate();
				
				
			} catch (SQLException e) {
				
				e.printStackTrace();
			
			}
			
			return null;
	}

	@Override
	public void deleteStatus(Status obj) {
		// TODO Auto-generated method stub
		
	}

}
