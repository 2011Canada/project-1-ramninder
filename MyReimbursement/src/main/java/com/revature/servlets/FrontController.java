package com.revature.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






public class FrontController extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String resourcepath = directControl(req, resp) + ".html";
		System.out.println(resourcepath);
		
		req.getRequestDispatcher(resourcepath).forward(req, resp);
		
	}
	
	
	protected String directControl(HttpServletRequest req, HttpServletResponse resp) {
		
		System.out.println(req.getRequestURI());
		
		
		switch(req.getRequestURI()) {
		
		case "/Reimbursement/home.view":
			return "home";
		
		case "/Reimbursement/login.view":
			return "login";
		
		case "/Reimbursement/logout.view":
			return "logout";
		
		case "/Reimbursement/manager.view":
			return "manager";
			
		case "/Reimbursement/employee.view":
			return "employee";
			
		case "/Reimbursement/eCreate.view":
			return "create";
			
			
		}
		
		return null;
		
	}
	
	
	

}
