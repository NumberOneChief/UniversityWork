package com.admiral.tests;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.annotations.Test;

import com.admiral.controllers.WebPropertyController;
import com.admiral.utilities.DB_Type;
import com.admiral.utilities.PropertyFile;
import com.admiral.utilities.Queries;

public class UnitTests {

	@Test
	public void testPropFile() {
		PropertyFile pf = new PropertyFile();
		System.out.println(pf.getOracleUrl());
		System.out.println(pf.getMySqlUrl());
		System.out.println(pf.getUsername());
		System.out.println(pf.getPassword());
	}
	
	@Test
	public void testConnection() {
		WebPropertyController dbUtils = new WebPropertyController();
		try {
			dbUtils.getConnection(DB_Type.MYSQL,"WebProperties");
			System.out.println("Connection Successful :)");
			
		} catch (SQLException e) {
			dbUtils.processException(e);
		}
	}
	
	@Test
	public void testDisplayResultSet() throws SQLException {
		Queries query = new Queries();
		ResultSet rs = query.getWebProperties("WebProperties");
		query.displayWebData(rs);
		
	}
}
