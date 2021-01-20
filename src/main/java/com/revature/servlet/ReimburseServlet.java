package com.revature.servlet;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.ClientUser;
import com.revature.models.Credentials;
import com.revature.models.Reimb;
import com.revature.models.ReimbRequest;
import com.revature.models.User;
import com.revature.repositories.GeneralPostgresDAO;

/**
 * Servlet implementation class ReimburseServlet
 */
public class ReimburseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReimburseServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private GeneralPostgresDAO gdao = GeneralPostgresDAO.getGPDAO();
	
	private ObjectMapper om = new ObjectMapper();
	
	private void createReimb(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		Reimb re = om.readValue(req.getInputStream(), Reimb.class);
		
		HttpSession sess = req.getSession();
		//user.getRole
		
		int uid = 2;
		
		if(sess.getAttribute("User-ID") != null) {
			uid = (Integer) sess.getAttribute("User-ID");
		}
		
		Timestamp now = new Timestamp(System.currentTimeMillis());
		
		ReimbRequest rr = new ReimbRequest(0,re.getAmount(),now,null,re.getDescription(),uid,1,0,0);
		
		gdao.createReimbRequest(rr);
		res.getWriter().append("Served at: ").append(req.getContextPath());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		System.out.println("Reimburse Servlet Activated.");
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		createReimb(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
