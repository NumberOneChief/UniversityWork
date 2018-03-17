package com.admiral.tests;

import java.util.ArrayList;

import com.admiral.controllers.WebPropertyController;
import com.admiral.tables.WebProperty;
import com.admiral.tables.WebPropertyComparison;
import com.admiral.utilities.DB_Type;

public class Main {

	static ArrayList <WebProperty> expectedWebPropList;
	static ArrayList <WebProperty> compareWebPropList;
	static boolean matchingRowSize;
	
	public static void main(String[] args) {
		WebPropertyController webController = new WebPropertyController();
		expectedWebPropList = webController.creatWebRowObject(DB_Type.MYSQL,"webproperties");
		compareWebPropList = webController.creatWebRowObject(DB_Type.MYSQL, "Webproperties2");
		matchingRowSize = webController.webRowComparison(expectedWebPropList, compareWebPropList);
		
		displayRowComparison(matchingRowSize);
		displayMissingRows(webController);
		displayMismatchedValues(webController);
	}
	/**
	 * If the Arraylist of row objects size match them it will print out 
	 * that they match, else it will print out the expected and actual size
	 * @param matchingSize Boolean returned from weather the size matches or not
	 */
	private static void displayRowComparison(boolean matchingSize) {
		System.out.println("|==========================|");
		System.out.println("|****  Row Count Check ****|");
		System.out.println("|==========================|");
		if(matchingSize) {
			System.out.println("The number of the Rows Match");
		}else {
			int expSize = expectedWebPropList.size();
			int compSize = compareWebPropList.size();
			System.out.printf("Expected Size:| %d | Compared Size:| %d|\n", 
					expSize,compSize);
		}
		System.out.println();
	}

	/**
	 * This method will check if there are any primary key mismatches in the arraylist
	 * and will print out any mismatches or confirm no mismatches
	 * @param webController to obtain access to the Arraylist  
	 */
	public static void displayMissingRows(WebPropertyController webController){
		System.out.println("|==========================|");
		System.out.println("|****   Missing Keys   ****|");
		System.out.println("|==========================|");

		if(webController.rowDoesNotExistsList.size() == 0) {
			System.out.println("No missing primary keys were found");
		}
		
		for (WebProperty webProp : webController.rowDoesNotExistsList) {
			webController.displayWebProp(webProp);
		}
		System.out.println();
	}
	
	/**
	 * This method loops through any entries that have mismatched values
	 * @param webController to obtain access to the Arraylist  
	 */
	public static void displayMismatchedValues(WebPropertyController webController) {
		System.out.println("|==========================|");
		System.out.println("|**** Mismatched Value ****|");
		System.out.println("|==========================|");
		
		if(webController.mismatchedValuesList.size() == 0) {
			System.out.println("There are no mismatched values !");
		}else {
			for(WebPropertyComparison webComparison : webController.mismatchedValuesList){
				System.out.println("----------------------------------------------------------------------");
				System.out.printf("Expected:   " );
				webController.displayWebProp(webComparison.getExpectedWebProperty());
				System.out.printf("Mismatched: ");
				webController.displayWebProp(webComparison.getComparedWebProperty());
				System.out.println("----------------------------------------------------------------------");
			}
		}
	}

}
