package com.revature.repositories;

import java.util.List;

import com.revature.models.ReimbRequest;
import com.revature.models.User;

public interface GeneralDAO {
	
	//public static GeneralDAO getGPDAO();
	
	public User getUser(String username, String password);
	
	public User getUser(int id);
	
	public void createReimbRequest(ReimbRequest r);
	
	public void updateReimbRequest(ReimbRequest r);
	
	public List<ReimbRequest> getAllReimbRequests();
	
	public List<ReimbRequest> getFilteredReimbRequests(int id);

}
