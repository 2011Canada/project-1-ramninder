package com.revature.dao;

import java.util.List;

import com.revature.models.Reimbursement;

public interface ReimburseDao {
	
	
	public List<Reimbursement> findAllReimbursement();
	
	public Reimbursement findReimbursementById(Integer id);
	
	public List<Reimbursement> findReimsbursementByAuthorId(Integer id);
	
	public Reimbursement save(Reimbursement obj);
	
	public Reimbursement update(Reimbursement obj);

}
