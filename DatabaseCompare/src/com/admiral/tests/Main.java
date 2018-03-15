package com.admiral.tests;

import java.util.ArrayList;

import com.admiral.tables.WebProperty;
import com.admiral.utilities.DB_Type;
import com.admiral.utilities.DB_Utilities;

public class Main {

	static ArrayList <WebProperty> expectedWebPropList;
	static ArrayList <WebProperty> compareWebPropList;
	
	public static void main(String[] args) {
		DB_Utilities dbUtils = new DB_Utilities();
		expectedWebPropList = dbUtils.creatWebRowObject(DB_Type.MYSQL,"webproperties");
		compareWebPropList = dbUtils.creatWebRowObject(DB_Type.MYSQL, "Webproperties2");
		dbUtils.findMatchingKey(expectedWebPropList, compareWebPropList);
		
		for (int i = 0; i < dbUtils.matchedWebRowList.size(); i++) {
			WebProperty webProp = dbUtils.matchedWebRowList.get(i);
				System.out.println(webProp.getId());
				System.out.println(webProp.getBrand());
				System.out.println(webProp.getKey());
				System.out.println(webProp.getValue());
				System.out.println("#############");
			
		}
	}

}
