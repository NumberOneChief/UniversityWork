package com.admiral.utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.admiral.tables.WebProperty;

public class Queries {
	
	
	public ResultSet getWebProperties(String database) {
		DB_Utilities dbUtils = new DB_Utilities();
		ResultSet rs = null;
		String webPropQuery = "Select * from WebProperties";
		
		try {
			Connection conn = dbUtils.getConnection(DB_Type.MYSQL,database);
			PreparedStatement stmt = conn.prepareStatement(
					webPropQuery,
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY
			);
			rs = stmt.executeQuery();
					
		} catch (SQLException e) {
			dbUtils.processException(e);
		}
		return rs;
	}
	
	
	public void displayWebData(ResultSet rs){
		
		try {
			while (rs.next()) {
				StringBuffer buffer = new StringBuffer();
				buffer.append("ID: " + rs.getInt("ID") +" | ");
				buffer.append("Key: " + rs.getString("Key") +" | ");
				buffer.append("Brand: " + rs.getString("Brand") +" | ");
				buffer.append("Value: " + rs.getString("Value") +" | ");
				System.out.println(buffer.toString());
			}
		} catch (SQLException e) {
			DB_Utilities dbUtils = new DB_Utilities();
			dbUtils.processException(e);
		}
	}
	
}
