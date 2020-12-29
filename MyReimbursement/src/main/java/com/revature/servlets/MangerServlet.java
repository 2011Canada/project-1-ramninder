package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ReimburseDao;
import com.revature.dao.ReimburseDaoImplementation;
import com.revature.models.Reimbursement;

@WebServlet("/manager")
public class MangerServlet extends HttpServlet {
	
	
	ReimburseDao reimburseDao = new ReimburseDaoImplementation();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Reimbursement> reimburse = reimburseDao.findAllReimbursement();
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(reimburse);
		

		PrintWriter writer = resp.getWriter();
		resp.setContentType("application/json");
		writer.write(json);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}
