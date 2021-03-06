package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.util.ConnectionFactory;

public class ReimburseDaoImplementation implements ReimburseDao{
	
	
	private ConnectionFactory cf = ConnectionFactory.getConnectionFactory();


	@Override
	public List<Reimbursement> findAllReimbursement() {
			List<Reimbursement> reimb = new ArrayList<>();
		
		try {
			Connection conn = cf.getConnection();
			
			String sql = "select * from \"ERS_REIMBURSEMENT\";\n"
					+ "";
				Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
		while(rs.next()) {
			
			Reimbursement temp = new Reimbursement();
			temp.setId(rs.getInt("REIMB_ID"));
			temp.setAmount(rs.getInt(2));
			temp.setSubmitted(rs.getTimestamp(3));
			temp.setResolved(rs.getTimestamp(4));
			temp.setDescription(rs.getString(5));
			temp.setAuthor(rs.getInt(6));
			temp.setResolver(rs.getInt(7));
			temp.setStatus_id(rs.getInt(8));
			temp.setType_id(rs.getInt(9));
			reimb.add(temp);
			
		}	
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return reimb;


	}

	@Override
	public Reimbursement findReimbursementById(Integer id) {
			Reimbursement reimb = null;
		
		try {
			Connection conn = cf.getConnection();
			String sql = "SELECT * FROM \"ERS_REIMBURSEMENT\" WHERE \"REIMB_ID\" = ?\n"
					+ "";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,  id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {

				reimb = new Reimbursement();
				reimb.setId(rs.getInt(1));
				reimb.setAmount(rs.getInt(2));
				reimb.setSubmitted(rs.getTimestamp(3));
				reimb.setResolved(rs.getTimestamp(4));
				reimb.setDescription(rs.getString(5));
				reimb.setAuthor(rs.getInt(6));
				reimb.setResolver(rs.getInt(7));
				reimb.setStatus_id(rs.getInt(8));
				reimb.setType_id(rs.getInt(9));

			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return reimb;

	}

	@Override
	public List<Reimbursement> findReimsbursementByAuthorId(Integer id) {
		List<Reimbursement> reims = new ArrayList<Reimbursement>();
		
		try {
			Connection conn = cf.getConnection();

			String sql = "SELECT * FROM \"ERS_REIMBURSEMENT\" WHERE \"REIMB_AUTHOR\" = ? ORDER BY \"REIMB_ID\"\n"
					+ "";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,  id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {

				Reimbursement r = new Reimbursement();
				r.setId(rs.getInt(1));
				r.setAmount(rs.getInt(2));
				r.setSubmitted(rs.getTimestamp(3));
				r.setResolved(rs.getTimestamp(4));
				r.setDescription(rs.getString(5));
				r.setAuthor(rs.getInt(6));
				r.setResolver(rs.getInt(7));
				r.setStatus_id(rs.getInt(8));
				r.setType_id(rs.getInt(9));
				reims.add(r);

			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return reims;
		
	}

	@Override
	public Reimbursement save(Reimbursement obj) {
		try {
			Connection conn = cf.getConnection();

			conn.setAutoCommit(false);
			
			String sql = "INSERT INTO \"ERS_REIMBURSEMENT\" (\"REIMB_AMOUNT\", \"REIMB_DESCRIPTION\",\n"
					+ "\"REIMB_SUBMITTED\", \"REIMB_RESOLVED\", \"REIMB_AUTHOR\", \n"
					+ "\"REIMB_RESOLVER\", \"REIMB_STATUS_ID\", \"REIMB_TYPE_ID\")\n"
					+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
			
			String[] keyNames = {"REIMB_ID"};
			
			PreparedStatement ps = conn.prepareStatement(sql, keyNames);
			ps.setInt(1, obj.getAmount());
			ps.setString(2, obj.getDescription());
			ps.setTimestamp(3, obj.getSubmitted());
			ps.setTimestamp(4, obj.getResolved());
			ps.setInt(5, obj.getAuthor());
			ps.setInt(6,  obj.getResolver());
			ps.setInt(7, obj.getStatus_id());
			ps.setInt(8, obj.getType_id());
			
			
			
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
	public Reimbursement update(Reimbursement obj) {
		//log.trace("inside update");
		
		try {
			Connection conn = cf.getConnection();
			String sql = "UPDATE \"ERS_REIMBURSEMENT\" SET \"REIMB_STATUS_ID\" = ? WHERE \"REIMB_ID\" = ?					\n"
					+ "";
			//log.trace("inside update, before prepared statement");
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, obj.getStatus_id());
			ps.setInt(2, obj.getId());
			ps.executeUpdate();
		
			//log.trace("update executed");
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return null;
	}

}
