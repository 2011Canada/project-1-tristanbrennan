package com.revature.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.ReimbRequest;
import com.revature.models.User;

public class GeneralPostgresDAO extends AbstractPostgresDAO implements GeneralDAO {
	
	private static GeneralPostgresDAO singleton = new GeneralPostgresDAO();
	
	public static GeneralPostgresDAO getGPDAO() {
		return singleton;
	}

	public User getUser(String username, String password) {
		Connection conn = cf.getConnection();
		
		User foundUser = null;
		
		try {
			String sql = "SELECT * \r\n"
					+ "FROM users\r\n"
					+ "WHERE username='" + username + "' AND password='" + password + "';";
			Statement s = conn.createStatement();
			ResultSet res = s.executeQuery(sql);
			
			while(res.next()) {
				foundUser = new User(
						res.getInt("user_id"), 
						res.getInt("user_role_id"), 
						res.getString("username"), 
						res.getString("password"),
						res.getString("firstname"), 
						res.getString("lastname"), 
						res.getString("email")
						);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return foundUser;
	}
	
	public User getUser(int id) {
		Connection conn = cf.getConnection();
		
		User foundUser = null;
		
		try {
			String sql = "SELECT * \r\n"
					+ "FROM users\r\n"
					+ "WHERE user_id=" + id + ";";
			Statement s = conn.createStatement();
			ResultSet res = s.executeQuery(sql);
			
			while(res.next()) {
				foundUser = new User(
						res.getInt("user_id"), 
						res.getInt("user_role_id"), 
						res.getString("username"), 
						res.getString("password"),
						res.getString("firstname"), 
						res.getString("lastname"), 
						res.getString("email")
						);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return foundUser;
	}

	public void createReimbRequest(ReimbRequest r) {
		
		Connection conn = cf.getConnection();
		
		String sql = "insert into ers_reimbursement"
				+ " (reimb_amount,"
				+ "reimb_submitted,"
				+ "reimb_resolved,"
				+ "reimb_description,"
				+ "reimb_author,"
				+ "reimb_resolver,"
				+ "reimb_status_id,"
				+ "reimb_type_id)\r\n"
				+ " values (?,?,?,?,?,?,?,?)\r\n"
				+ " returning reimb_id;";
		
		try {
			PreparedStatement new_acct = conn.prepareStatement(sql);
			new_acct.setDouble(1, r.getAmount());
			new_acct.setTimestamp(2, r.getSubmitted());
			new_acct.setTimestamp(3, r.getResolved());
			new_acct.setString(4, r.getDescript());
			new_acct.setInt(5, r.getAuthor());
			new_acct.setInt(6, r.getResolver());
			new_acct.setInt(7, r.getStatusId());
			new_acct.setInt(8, r.getTypeId());
			
			new_acct.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//must use prepared statement instead of a regular statement when dealing with user input of any kind
		//this is to prevent sql injection
	}
	
	public void updateReimbRequest(ReimbRequest r) {
		Connection conn = cf.getConnection();
		
		String sql = "update ers_reimbursement "
				+ "set reimb_status_id = ? "
				+ "where reimb_id = ? "
				+ "returning reimb_id;";
		
		try {
			PreparedStatement new_user = conn.prepareStatement(sql);
			new_user.setDouble(1, r.getStatusId());
			new_user.setInt(2, r.getReimbId());
			
			new_user.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String sql2 = "update ers_reimbursement "
				+ "set reimb_resolved = ? "
				+ "where reimb_id = ? "
				+ "returning reimb_id;";
		
		try {
			PreparedStatement new_user = conn.prepareStatement(sql2);
			new_user.setTimestamp(1, r.getResolved());
			new_user.setInt(2, r.getReimbId());
			
			new_user.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public List<ReimbRequest> getAllReimbRequests() {
		Connection conn = cf.getConnection();
		ArrayList<ReimbRequest> allAccounts = new ArrayList<ReimbRequest>();
		
		try {
			String sql = "select * from ers_reimbursement;";
			Statement s = conn.createStatement();
			ResultSet res = s.executeQuery(sql);
			
			while(res.next()) {
				allAccounts.add(new ReimbRequest(
						res.getInt("reimb_id"), 
						res.getDouble("reimb_amount"),
						res.getTimestamp("reimb_submitted"),
						res.getTimestamp("reimb_resolved"),
						res.getString("reimb_description"),
						res.getInt("reimb_author"),
						res.getInt("reimb_resolver"),
						res.getInt("reimb_status_id"),
						res.getInt("reimb_type_id")
						)
						);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return allAccounts;
	}

	public List<ReimbRequest> getFilteredReimbRequests(int userId) {
		Connection conn = cf.getConnection();
		ArrayList<ReimbRequest> allAccounts = new ArrayList<ReimbRequest>();
		
		try {
			String sql = "SELECT * \r\n"
					+ "FROM ers_reimbursement\r\n"
					+ "WHERE reimb_author=" + userId + ";";
			Statement s = conn.createStatement();
			ResultSet res = s.executeQuery(sql);
			
			while(res.next()) {
				allAccounts.add(new ReimbRequest(
						res.getInt("reimb_id"), 
						res.getDouble("reimb_amount"),
						res.getTimestamp("reimb_submitted"),
						res.getTimestamp("reimb_resolved"),
						res.getString("reimb_description"),
						res.getInt("reimb_author"),
						res.getInt("reimb_resolver"),
						res.getInt("reimb_status_id"),
						res.getInt("reimb_type_id")
						)
						);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return allAccounts;
	}
	
	public ReimbRequest getOneReimbRequest(int reimbId) {
		Connection conn = cf.getConnection();
		ReimbRequest result = null;
		
		try {
			String sql = "SELECT * \r\n"
					+ "FROM ers_reimbursement\r\n"
					+ "WHERE reimb_id=" + reimbId + ";";
			Statement s = conn.createStatement();
			ResultSet res = s.executeQuery(sql);
			
			while(res.next()) {
				result = (new ReimbRequest(
						res.getInt("reimb_id"), 
						res.getDouble("reimb_amount"),
						res.getTimestamp("reimb_submitted"),
						res.getTimestamp("reimb_resolved"),
						res.getString("reimb_description"),
						res.getInt("reimb_author"),
						res.getInt("reimb_resolver"),
						res.getInt("reimb_status_id"),
						res.getInt("reimb_type_id")
						)
						);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	

}
