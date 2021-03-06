package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.dao.ReimburseDao;
import com.revature.dao.ReimburseDaoImplementation;
import com.revature.dao.UserDao;
import com.revature.dao.UserDaoImplementation;
import com.revature.models.Reimbursement;
import com.revature.models.User;
import com.revature.service.ReimburseService;
import com.revature.service.UserService;


@WebServlet("/login")
public class LoadLoginServlet extends HttpServlet {
	
	static UserService userServ = new UserService();
	
	static ReimburseService reimServ = new ReimburseService();
	
	static ReimburseDao reimburseDao = new ReimburseDaoImplementation();

	static UserDao userDao = new UserDaoImplementation();

		
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getRequestDispatcher("error-login.html").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper om = new ObjectMapper();
		User user = om.readValue(req.getInputStream(), User.class);
		//validating users existence.
		System.out.println(user.getUsername()+ "///// "+user.getPassword());

		
		user = userServ.validateUser(user.getUsername(), user.getPassword());

		
		if(user == null) {
			System.out.println("I entered if null block");

			req.getRequestDispatcher("error-login.html").forward(req, resp);
			

		}
		
		else {
			System.out.println("I entered else session block");

			HttpSession session = req.getSession();
			session.setAttribute("user", user);
			System.out.println(user.getRole_id());
			if(user.getRole_id() == 1) {
				
				resp.sendRedirect("manager.html");
				
			}
			else {
				System.out.println(user.getRole_id());
				
				resp.sendRedirect("employee.html");
				
			}
			
			
		}
	}
	
		@Override
		protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		ObjectMapper mapper = new ObjectMapper();
	
		Reimbursement r = mapper.readValue(req.getInputStream(), Reimbursement.class);
		
		reimServ.updateStatus(r.getId(), r.getStatus_id());

		
	}
}
