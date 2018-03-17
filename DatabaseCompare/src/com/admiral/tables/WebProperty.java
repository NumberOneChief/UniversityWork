package com.admiral.tables;

public class WebProperty{

	
	private int id;	// The id of the database
	private String key; // the first of two primary keys within the database
	private String brand; //The second primary key within the database
	private String value; // The value of the database row 
	
	/**
	 * Constructor to set the values of each row and create the webrow object
	 * @param id The id of the database
	 * @param key the first of two primary keys within the database
	 * @param brand The second primary key within the database
	 * @param value The value of the database row 
	 */
	public WebProperty(int id, String key, String brand, String value) {
		this.id = id;
		this.key = key;
		this.brand = brand;
		this.value = value;
	}
	
	/**
	 * Getter method to return the id of the object
	 * @return int 
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * Getter method to return the key of the object
	 * @return String
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Setter method to set the key of the Object
	 * @param key String
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Getter method to return the Brand of the object
	 * @return String
	 */
	public String getBrand() {
		return brand;
	}
	

	/**
	 * Setter method to set the brand of the Object
	 * @param brand
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * Setter method to set the Value of the Object
	 * @return String
	 */
	public String getValue() {
		return value;
	}
	
//	/**
//	 * Method to compare keys of the two different objects 
//	 * @param keyOne Key of Object one
//	 * @param keyTwo key of the second object to compare
//	 * @return boolean whether the keys match
//	 */
//	public boolean compareKeys(String keyOne, String keyTwo) {
//		if(keyOne.equals(keyTwo)) {
//			return true;
//		}
//		return false;
//	}
//
//	
//	public boolean equals(WebProperty compareProperty) {
//		if(this.key.equals(compareProperty.key) && this.brand.equals(compareProperty.brand)) {
//			return true;
//		}
//		return false;
//	}	
}
