package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Type;
import com.revature.util.ConnectionFactory;

public class TypesDaoImplementation implements TypesDao {
	
	private ConnectionFactory cf = ConnectionFactory.getConnectionFactory();


	@Override
	public List<Type> findAllTypes() {
		List<Type> types = new ArrayList<Type>();
		
		try {
			Connection conn = cf.getConnection();
			
			String sql = "select * from \"ERS_REIMBURSEMENT_TYPE\";\n"
					+ "";
				Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				
				Type temp = new Type();
				temp.setId(rs.getInt("REIMB_TYPE_ID"));
				temp.setType(rs.getString(2));
				types.add(temp);
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		return types;

	}

	@Override
	public Type findByTypeId(Integer id) {
		Type type = null;
		
		try  {
			
			Connection conn = cf.getConnection();
			String sql = "SELECT * FROM \"ERS_REIMBURSEMENT_TYPE\" WHERE \"REIMB_TYPE_ID\" = ?\n"
					+ "";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,  id);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				type = new Type();
				type.setId(rs.getInt(1));
				type.setType(rs.getString(2));
				
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return type;
		
	}

	@Override
	public Type saveType(Type obj) {
		try  {
			
			Connection conn = cf.getConnection();
			//conn.setAutoCommit(false);
				
			String sql = "INSERT INTO \"ERS_REIMBURSEMENT_TYPE\" (\"REIMB_TYPE\") VALUES(?)\n"
					+ "";
			String[] keyNames = {"REIMB_TYPE_ID"};
				
			PreparedStatement ps = conn.prepareStatement(sql, keyNames);
			ps.setString(1, obj.getType());
				
			int numRows = ps.executeUpdate();
			if(numRows > 0) {
					
				ResultSet pk = ps.getGeneratedKeys();
					
				while(pk.next()) {
						
					obj.setId(pk.getInt(1));
						
				}
					
				//conn.commit();
					
			}	
				
			} catch (SQLException e) {

				e.printStackTrace();
			
			}
			
			return obj;

	}

	@Override
	public Type updateType(Type obj) {
		try  {
			
			Connection conn = cf.getConnection();
			String sql = "UPDATE \"ERS_REIMBURSEMENT_TYPE\" SET \"REIMB_TYPE\" = ? WHERE \"REIMB_TYPE_ID\" = ?\n"
					+ "";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,  obj.getType());
			ps.setInt(2, obj.getId());
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		
		}
		
		return null;
	}

	@Override
	public void deleteType(Type obj) {
		// TODO Auto-generated method stub
		
	}

}
