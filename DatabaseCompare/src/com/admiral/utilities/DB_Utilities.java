package com.admiral.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.admiral.tables.WebProperty;


public class DB_Utilities {

	public PropertyFile propFile = new PropertyFile();
	public ArrayList <WebProperty>noMatchWebRowList = new ArrayList<>();
	public ArrayList <WebProperty> matchedWebRowList = new ArrayList<>();
	
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
				webQuery.displayWebProp(webProp);
				webRowObjectList.add(webProp);
			}
			webQuery.displayWebData(rs);
			
		} catch (SQLException e) {
			processException(e);
		}
		return webRowObjectList;
	}
	
	public ArrayList<WebProperty> findMatchingKey(ArrayList<WebProperty> expectedRow, 
				ArrayList<WebProperty> comparedRow) {
		
		for (int i = 0; i < expectedRow.size(); i++) {
			
			WebProperty expectedWebProp = expectedRow.get(i);
			String expectedWebKey = expectedWebProp.getKey();
			
			for (int j = 0; j < comparedRow.size(); j++) {
				
				WebProperty compareWebProp = comparedRow.get(j);
				String compareWebKey = compareWebProp.getKey();
				
				if(expectedWebKey.equals(compareWebKey)) {
					if(!matchedWebRowList.contains(compareWebProp)) {
						matchedWebRowList.add(compareWebProp);
					}
				}else if(!expectedWebKey.equals(compareWebKey)) {
					if(!noMatchWebRowList.contains(compareWebProp)) {
						noMatchWebRowList.add(compareWebProp);
					}
				}
			}
		}
		return noMatchWebRowList;
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
