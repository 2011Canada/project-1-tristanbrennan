package com.revature.servlet;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.models.Reimb;
import com.revature.models.ReimbRequest;
import com.revature.models.ReimbUpdate;
import com.revature.repositories.GeneralPostgresDAO;

/**
 * Servlet implementation class UpdateServlet
 */
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    private GeneralPostgresDAO gdao = GeneralPostgresDAO.getGPDAO();
	
	private ObjectMapper om = new ObjectMapper();
	
	private void updateReimb(HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		System.out.println("Update Servlet Activated");
		
		ReimbUpdate ru = om.readValue(req.getInputStream(), ReimbUpdate.class);
		
		HttpSession sess = req.getSession();
		//user.getRole
		
		int uid = 1;
		
		if(sess.getAttribute("User-ID") != null) {
			uid = (Integer) sess.getAttribute("User-ID");
		}
		
		Timestamp now = new Timestamp(System.currentTimeMillis());
		
		ReimbRequest old = gdao.getOneReimbRequest(ru.getId());
		
		ReimbRequest updated = new ReimbRequest(
				old.getReimbId(),old.getAmount(),old.getSubmitted(),now,old.getDescript(),
				old.getAuthor(),uid,ru.getStatus(),old.getTypeId());
		
		gdao.updateReimbRequest(updated);
		res.getWriter().append("Served at: ").append(req.getContextPath());
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		updateReimb(request,response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
