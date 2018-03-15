package com.admiral.tests;

import java.util.ArrayList;

import com.admiral.tables.WebProperty;
import com.admiral.utilities.DB_Type;
import com.admiral.utilities.DB_Utilities;

public class Main {

	static ArrayList <WebProperty> expectedWebPropList;
	static ArrayList <WebProperty> compareWebPropList;
	static ArrayList <WebProperty> unMatchedRowList;
	
	
	public static void main(String[] args) {
		DB_Utilities dbUtils = new DB_Utilities();
		expectedWebPropList = dbUtils.creatWebRowObject(DB_Type.MYSQL,"webproperties");
		compareWebPropList = dbUtils.creatWebRowObject(DB_Type.MYSQL, "Webproperties2");
		unMatchedRowList = dbUtils.checkForWebRowMatches(expectedWebPropList, compareWebPropList);
		
		for (WebProperty webProp : unMatchedRowList) {
			System.out.printf("%35s","|**** Missing Row ****|\n");
			System.out.printf("%35s","-----------------------\n");
			dbUtils.displayWebProp(webProp);
		}
	}

}
