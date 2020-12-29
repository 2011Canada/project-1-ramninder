package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ReimburseDao;
import com.revature.dao.ReimburseDaoImplementation;
import com.revature.models.Reimbursement;
import com.revature.models.User;


@WebServlet("/create")
public class CreateServlet extends HttpServlet {
	
	static ReimburseDao rDao = new ReimburseDaoImplementation();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//Returning list of all reimbursement for specific person.
		HttpSession session =  req.getSession();
		User u =  (User) session.getAttribute("user");
		
		//Create json file for all reimbursements.
		List<Reimbursement> reimburses = rDao.findReimsbursementByAuthorId(u.getId());
		ObjectMapper om = new ObjectMapper();
		
		String json = om.writeValueAsString(reimburses);

		resp.setContentType("application/json");
		PrintWriter writer = resp.getWriter();
		writer.write(json);;
		
		

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//Create new Reimbursement for user in current session.
		
		ObjectMapper om = new ObjectMapper();
		Reimbursement reimburse = om.readValue(req.getInputStream(), Reimbursement.class);
		
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		
		reimburse.setAuthor(user.getId());
		
		reimburse = rDao.save(reimburse);
		
	}
	


}
