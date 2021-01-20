package com.revature.models;

public class ClientUser {
	
	public ClientUser(String username) {
		super();
		this.username = username;
	}

	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
