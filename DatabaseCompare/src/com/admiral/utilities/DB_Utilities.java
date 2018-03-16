package com.admiral.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.admiral.tables.WebProperty;
import com.admiral.tables.WebPropertyComparison;


public class DB_Utilities {

	public PropertyFile propFile = new PropertyFile();
	public ArrayList <WebProperty> noMatchedWebRowList = new ArrayList<>();
	public ArrayList <WebProperty> expectedMatchedWebRowList = new ArrayList<>();
	public ArrayList <WebProperty> matchedWebRowList = new ArrayList<>();
	public ArrayList <WebPropertyComparison> webRowList = new ArrayList<>();
	
	public Connection getConnection(DB_Type dbType, String database) throws SQLException {
		Connection conn = null;
		try {
			switch (dbType) {
			case MYSQL:
				conn = DriverManager.getConnection(propFile.getMySqlUrl()+database,
						propFile.getUsername(), propFile.getPassword());
				break;
				
			case ORACLE:
				conn = DriverManager.getConnection(propFile.getOracleUrl(),
						propFile.getUsername(), propFile.getPassword());
				break;
			
			default:
				conn = null;
			}
			return conn;
		} catch (SQLException e) {
			
			processException(e);	
		}
		return conn;
	}
	
	public ArrayList<WebProperty> creatWebRowObject(DB_Type dbType,String database) {
		ArrayList <WebProperty>webRowObjectList = new ArrayList<>();
		Queries webQuery = new Queries();
		ResultSet rs = null;
		WebProperty webProp;
		int id;
		String key;
		String brand;
		String value; 
		
		try {
			getConnection(dbType,database);
			rs = webQuery.getWebProperties(database);
			while(rs.next()) {
				id = rs.getInt("ID");
				key = rs.getString("Key");
				brand = rs.getString("Brand");
				value = rs.getString("Value");
				webProp = new WebProperty(id, key, brand, value);
				webRowObjectList.add(webProp);
			}
			webQuery.displayWebData(rs);
			
		} catch (SQLException e) {
			processException(e);
		}
		return webRowObjectList;
	}
	
	public ArrayList<WebProperty> checkForWebRowMatches(ArrayList<WebProperty> expectedRow, 
				ArrayList<WebProperty> comparedRow) {
		
		boolean matchSize = (expectedRow.size() == comparedRow.size());
		
//		for (int i = 0; i < expectedRow.size(); i++) {
		for(WebProperty expectedWebProp : expectedRow){
			
//			WebProperty expectedWebProp = expectedRow.get(i);
			
			String expectedKey = expectedWebProp.getKey();
			String expectedBrand = expectedWebProp.getBrand();
			boolean match = false;
			
			for (int j = 0; j < comparedRow.size(); j++) {
				
				WebProperty compareWebProp = comparedRow.get(j);
				String compareKey = compareWebProp.getKey();
				String compareBrand = compareWebProp.getBrand();
				
				if(expectedKey.equals(compareKey) && expectedBrand.equals(compareBrand)) {
					
					WebPropertyComparison webMatch = new WebPropertyComparison(expectedWebProp, compareWebProp);
					webRowList.add(webMatch);

					expectedMatchedWebRowList.add(expectedWebProp);
					matchedWebRowList.add(compareWebProp);
					match = true;
					break;
				}
			}
			if(!match) {
				noMatchedWebRowList.add(expectedWebProp);
			}
		}
		return noMatchedWebRowList;
	}
	
	public void compareValues() {
		
	}
	
	public void displayWebProp(WebProperty webProp){
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("ID: " + webProp.getId() +" | ");
		buffer.append("Key: " + webProp.getKey() +" | ");
		buffer.append("Brand: " + webProp.getBrand() +" | ");
		buffer.append("Value: " + webProp.getValue() +" | ");
		System.out.println(buffer.toString());
	}
	
	/**
	 * This method can be called to get specific information on SQL exception
	 * on the exception
	 * @param e This takes an SQL exception 
	 */
	public void processException(SQLException e) {
		System.err.println("Error Message: " + e.getMessage());
		System.err.println("Error Code: " + e.getErrorCode());
		System.err.println("SQL State: " + e.getSQLState());
	}
	
}
