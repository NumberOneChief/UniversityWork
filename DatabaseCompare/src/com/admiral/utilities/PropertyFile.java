package com.admiral.utilities;


import java.util.ResourceBundle;

public class PropertyFile {
	//Creating new resource object and setting the path of the property file
	private static ResourceBundle res = ResourceBundle.getBundle("com.admiral.resources.config" );
	String connString;
	
	//Getting the Oracle connection string from the property file and returning
	public String getOracleUrl() {
	    connString = res.getString("oracleconnstring");
		return connString;
	}
	
	//Getting the MySQL connection String from property file and returns
	public String getMySqlUrl() {
		connString = res.getString("mysqlconnstring");
		return connString;
	}
	
	//Getting the username from the property file and returning
	public String getUsername() {
		String user = res.getString("user");
		return user;
	}
	
	//gets the password from the property file and returns
	public String getPassword() {
		String user = res.getString("password");
		return user;
	}
}
