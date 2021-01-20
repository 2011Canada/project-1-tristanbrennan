package com.revature.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.revature.models.Credentials;
import com.revature.models.ReimbRequest;
import com.revature.models.User;
import com.revature.repositories.GeneralPostgresDAO;

public class AccountControl {
	
	private GeneralPostgresDAO gdao = GeneralPostgresDAO.getGPDAO();
	
	private ObjectMapper om = new ObjectMapper();
	
	public void userData(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		System.out.println("AccountControl activated.");
		
		HttpSession sess = req.getSession();
		//user.getRole
			
		String URI = req.getRequestURI().substring(req.getContextPath().length(), 
				req.getRequestURI().length());
		
		int rid = 0;
		
		if(URI.equals("/account/0")){
			rid = 0;
		}
		else if(URI.equals("/account/1")){
			rid = 1;
		}
		
		List<ReimbRequest> rr = null;
		
		if(rid == 0) {
			rr = new GeneralPostgresDAO().getFilteredReimbRequests(2);
		}
		else if(rid == 1) {
			rr = new GeneralPostgresDAO().getAllReimbRequests();
		}
			
		res.setStatus(200);
		res.getWriter().write(om.writeValueAsString(rr));

	}
	
	
	

}
