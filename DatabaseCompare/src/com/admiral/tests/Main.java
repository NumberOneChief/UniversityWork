package com.admiral.tests;

import java.util.ArrayList;

import com.admiral.controllers.WebPropertyController;
import com.admiral.tables.WebProperty;
import com.admiral.tables.WebPropertyComparison;
import com.admiral.utilities.DB_Type;

public class Main {

	static ArrayList <WebProperty> expectedWebPropList;
	static ArrayList <WebProperty> compareWebPropList;
	
	public static void main(String[] args) {
		WebPropertyController webController = new WebPropertyController();
		
		expectedWebPropList = webController.creatWebRowObject(DB_Type.MYSQL,"webproperties");
		compareWebPropList = webController.creatWebRowObject(DB_Type.MYSQL, "Webproperties2");
		webController.checkForWebRowMatches(expectedWebPropList, compareWebPropList);
		
		System.out.printf("%35s","|**** Missing Row ****|\n");
		System.out.printf("%35s","-----------------------\n");
		for (WebProperty webProp : webController.rowDoesNotExistsList) {

			webController.displayWebProp(webProp);
		}
		
		System.out.printf("%35s","|-------------------------|");
		System.out.printf("\n%36s","|**** Incorrect Value ****|\n");
		System.out.printf("%36s","|-------------------------|\n");
		for (WebProperty webProp : webController.valuesMismatchList) {

			webController.displayWebProp(webProp);
		}
		
		
		for(WebPropertyComparison webComparison : webController.webRowList){
			StringBuffer buffer = new StringBuffer();
			
			buffer.append(webComparison.getWebProperty().getBrand());
			buffer.append(webComparison.getWebProperty().getKey());
			buffer.append(webComparison.isMatch());

			System.out.println(buffer.toString());
		}
		
		
	}

}
