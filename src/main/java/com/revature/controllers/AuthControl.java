package com.revature.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.ClientUser;
import com.revature.models.Credentials;
import com.revature.models.User;
import com.revature.repositories.GeneralPostgresDAO;

public class AuthControl {
	
	private GeneralPostgresDAO gdao = GeneralPostgresDAO.getGPDAO();
	
	private ObjectMapper om = new ObjectMapper();
	
	public void userLogin(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		Credentials cred = om.readValue(req.getInputStream(), Credentials.class);
		User ua = gdao.getUser(cred.getUsername(), cred.getPassword());
		
		if(ua == null) {
			System.out.println("User Not Found");
			res.setStatus(400);
			res.getWriter().write("User Not Found");
		}
		else {
			HttpSession sess = req.getSession();
			//user.getRole
			sess.setAttribute("User-Role", ua.getRole_id());
			sess.setAttribute("User-ID", ua.getUser_id());
			
			System.out.println("AuthControl activated.");
			
			res.setStatus(200);
			res.getWriter().write(om.writeValueAsString(ua));
			//res.sendRedirect("/ServletDemo/table/table.html");
			//System.out.println("About to return...");
	    	
	    	
	    	//end of else
		}
    	
    	
	}
	
	
	

}
