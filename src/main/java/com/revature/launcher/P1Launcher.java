package com.revature.launcher;

import com.revature.repositories.GeneralDAO;
import com.revature.repositories.GeneralPostgresDAO;

public class P1Launcher {
	
	//static Logger e720Logger = LogManager.getLogger("com.revature.e720");
	
	public static void main(String[] args) {
		//e720Logger.info("The server has started.");
		
		GeneralDAO gd = new GeneralPostgresDAO();
		//UserAccountService uas = new UserAccountServiceImplementation(uad);
		
		//System.out.println(login_info.get("tbrennan"));
		
		//bmen.beginMenuLoop();
		
//		UserAccount u = gd.getUser("jdoe", "1234");
//		System.out.println(u.getFirstname());
		
	}
}
