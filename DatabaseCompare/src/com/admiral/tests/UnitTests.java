package com.admiral.tests;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.testng.annotations.Test;

import com.admiral.tables.WebProperty;
import com.admiral.utilities.DB_Type;
import com.admiral.utilities.DB_Utilities;
import com.admiral.utilities.PropertyFile;
import com.admiral.utilities.Queries;

public class UnitTests {

//	@Test
//	public void testPropFile() {
//		PropertyFile pf = new PropertyFile();
//		System.out.println(pf.getOracleUrl());
//		System.out.println(pf.getMySqlUrl());
//		System.out.println(pf.getUsername());
//		System.out.println(pf.getPassword());
//	}
	
//	@Test
//	public void testConnection() {
//		DB_Utilities dbUtils = new DB_Utilities();
//		try {
//			dbUtils.getConnection(DB_Type.MYSQL);
//			System.out.println("Connection Successful :)");
//			
//		} catch (SQLException e) {
//			dbUtils.processException(e);
//		}
//	}
//	
//	@Test
//	public void testDisplayResultSet() throws SQLException {
//		Queries query = new Queries();
//		ResultSet rs = query.getWebProperties("WebProperties");
//		query.displayWebData(rs);
//		
//	}
	
	
	public void testCreateWebRow() {


	}
}
