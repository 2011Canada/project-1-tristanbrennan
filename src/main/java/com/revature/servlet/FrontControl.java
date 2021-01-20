package com.revature.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.revature.controllers.AuthControl;

@SuppressWarnings("serial")
public class FrontControl extends HttpServlet {
	
	private AuthControl ac = new AuthControl();
	
    public FrontControl() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void directControl(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
    	
    	System.out.println("FrontControl activated.");
    	
    	String URI = req.getRequestURI().substring(req.getContextPath().length(), 
    														req.getRequestURI().length());
    			
    	System.out.println(URI); 
    	
    	HttpSession sess = req.getSession();
		//user.getRole
//		sess.setAttribute("User-Role", ua.getRole_id());
//		sess.setAttribute("User-ID", ua.getUser_id());
    	
    	RequestDispatcher view = null;
    	
    	if(sess.getAttribute("User-Role") == null) {
    		
    		if(URI.equals("/login")) {
    			//res.sendRedirect("/ServletDemo/login/login.html");
    			
    			view = req.getRequestDispatcher(
    					"login.html");
    	    	try {
    				view.forward(req, res);
    			} catch (ServletException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    			} catch (IOException e1) {
    				// TODO Auto-generated catch block
    				e1.printStackTrace();
    			}
    			
    			//end login
        	}
    		else if(URI.equals("/auth")) {
        		ac.userLogin(req, res);
        	}
    		else {
    			
    			res.sendRedirect("http://localhost:8080/ServletDemo/login");
    			res.setStatus(200);
//    			res.getWriter().write();
    		}
    		
		}
    	else {
    		if(URI.equals("/user") && sess.getAttribute("User-Role").equals(0)) {
        		res.setStatus(425);
        	}
        	else if(URI.equals("/admin") && sess.getAttribute("User-Role").equals(1)) {
        		res.setStatus(425);
        	}
    	}
    	
    	  
    	//pw.close();  
    			
    	
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		directControl(request,response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		directControl(request,response);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		directControl(request,response);
		
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		directControl(request,response);
		
	}

}
