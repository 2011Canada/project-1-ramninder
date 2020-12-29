package com.revature.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.revature.dao.ReimburseDao;
import com.revature.dao.ReimburseDaoImplementation;
import com.revature.models.Reimbursement;

public class ReimburseService {
	
	static ReimburseDao  remiD = new ReimburseDaoImplementation();
	
	public static List<Reimbursement> getAllReimbs() {
		
		List<Reimbursement> reim = remiD.findAllReimbursement();
		
		return reim;
		
	}
	
	
	
	public static Reimbursement updateReim(int id) {
		
		Reimbursement r =remiD.findReimbursementById(id);
		remiD.update(r);
		
		return r;
	}
	
	public static void updateStatus(int rid, int rstat) {
		
		Reimbursement r = remiD.findReimbursementById(rid);
		
		r.setStatus_id(rstat);
		
		remiD.update(r);
		
	}
	
	
	public static void createReim() {
		
		List<Reimbursement> reims = remiD.findAllReimbursement();

	
	}

	
	//test save
//		public static Reimbursement saveReim() {
//			
//			Date date = new Date();
//			Timestamp time = new Timestamp(date.getTime());
//			Reimbursement r = new Reimbursement(300, "test", time, time, 1, 2, 2, 3);
//			remiD.save(r);
//			
//			return r;
//		}

}
