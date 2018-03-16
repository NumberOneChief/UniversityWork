package com.admiral.controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.admiral.tables.WebProperty;
import com.admiral.tables.WebPropertyComparison;
import com.admiral.utilities.DB_Type;
import com.admiral.utilities.PropertyFile;
import com.admiral.utilities.Queries;


public class WebPropertyController {

	public PropertyFile propFile = new PropertyFile();
	public ArrayList <WebProperty> valuesMismatchList = new ArrayList<>();
	public ArrayList <WebProperty> rowDoesNotExistsList = new ArrayList<>();
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
	
	public boolean checkForWebRowMatches(ArrayList<WebProperty> expectedRow, 
				ArrayList<WebProperty> comparedRow) {
		
		//comparing the two lists for size, expected and Compared
		boolean matchSize = (expectedRow.size() == comparedRow.size());
		//loop over expected list 
		for(WebProperty expectedWebProp : expectedRow){
			
			//Obtaining the expected key and brand to compare
			String expectedKey = expectedWebProp.getKey();
			String expectedBrand = expectedWebProp.getBrand();
			
			//if match found then add to matched list else add to no match list
			boolean rowMatch = false;
			//loop over the compared list to locate a match on the key and brand
			for (WebProperty compRow : comparedRow) {

				//Checking the values of the key and brand and then value within the statement  
				if(expectedWebProp.getKey().equals(compRow.getKey()) && expectedWebProp.getBrand().equals(compRow.getBrand()) ) {
					
					//Creating the webcompare object to compare and then add to list if true
					WebPropertyComparison webMatch = new WebPropertyComparison(expectedWebProp, compRow);
					
					if(webMatch.isMatch()) {
						matchedWebRowList.add(expectedWebProp);

					}else {
						valuesMismatchList.add(expectedWebProp);
						
					}
					rowMatch = true;
					break;
				}
				
			}
			if(!rowMatch) {
				rowDoesNotExistsList.add(expectedWebProp);
			}
		}
		return matchSize;
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
