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

/**
 * A class to control the WebProperty objects 
 * @author gareth g
 *
 */
public class WebPropertyController {
	//Property File object to deal with the connection strings
	public PropertyFile propFile = new PropertyFile();
	
	// Arraylists to store the different comparison matches or mismatches
	public ArrayList <WebProperty> rowDoesNotExistsList = new ArrayList<>();
	public ArrayList <WebProperty> matchedWebRowList = new ArrayList<>();
	public ArrayList <WebPropertyComparison> mismatchedValuesList = new ArrayList<>();
	
	/**
	 * Obtains the parameters and then makes connection with the chosen database
	 * The enums are used to alleviate the type error.
	 * @param dbType enum to confirm the type of connection needed
	 * @param database name of the database/Environment to connect to 
	 * @return return a connection type to use and query 
	 * @throws SQLException catch any errors that arise from connection
	 */
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
	/**
	 * This method queries a table and obtains a result set back whcih then is looped 
	 * through and each row is then created as an object with properties and added to an Arraylist
	 * @param dbType enum to confirm the type of connection needed
	 * @param database String name of the database/ environemtn to connect to 
	 * @return ArrayList of row objects
	 */
	public ArrayList<WebProperty> creatWebRowObject(DB_Type dbType,String database) {
		ArrayList <WebProperty>webRowObjectList = new ArrayList<>();
		Queries webQuery = new Queries();
		ResultSet rs = null;
		Connection conn;
		WebProperty webProp;
		int id;
		String key;
		String brand;
		String value; 
		
		try {
			conn = getConnection(dbType,database);
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
			conn.close();
			
		} catch (SQLException e) {
			processException(e);
		}
		return webRowObjectList;
	}
	
	/**
	 * Compares the expected rows against the comparison rows to locate any mismatches
	 * or missing rows. If missing rows, mismatch values or primary keys do not match 
	 * then they will be added to the specific Arraylist
	 * @param expectedRow The expected object to compare against
	 * @param comparedRow The row to check if matches the expected
	 * @return boolean to confirm if the number of rows match 
	 */
	public boolean webRowComparison(ArrayList<WebProperty> expectedRow, 
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
						//This object takes both the expected and comparison objects
						WebPropertyComparison mismatchedValues = new WebPropertyComparison(expectedWebProp, compRow);
						//List of objects with unmatched values 
						mismatchedValuesList.add(mismatchedValues);
					}
					//row was found which will not need to be added to the rowDoesNotExistsList
					rowMatch = true;
					break;
				}
				
			}
			//Statement to catch rows that were not matched and add to a list
			if(!rowMatch) {
				rowDoesNotExistsList.add(expectedWebProp);
			}
		}
		return matchSize;
	}
	
	/**
	 * A method to output all the WebProperty Object properties, to the console
	 * @param webProp object to obtain all the properties
	 */
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
