package com.admiral.utilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.annotations.Test;

import com.admiral.controllers.WebPropertyController;
import com.admiral.tables.WebProperty;

/**
 * Class to deal with all the different types of queries to databases 
 * @author garet
 *
 */
public class Queries {
	
	/**
	 * This will query the webproperty table and return a resultset of all the data
	 * @param database result set of data 
	 * @return the result set of data 
	 */
	public ResultSet getWebProperties(String database) {
		WebPropertyController dbUtils = new WebPropertyController();
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
	
	@Test
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
			WebPropertyController dbUtils = new WebPropertyController();
			dbUtils.processException(e);
		}
	}
	
}
