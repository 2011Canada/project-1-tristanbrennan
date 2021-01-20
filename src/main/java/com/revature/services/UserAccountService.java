package com.revature.services;

import com.revature.models.User;

public class UserAccountService {
	
	private static UserAccountService singleton = new UserAccountService();
	
	public static UserAccountService getUAS() {
		return singleton;
	}
	
	public User login() {
		return null;
	}

}
