package com.admiral.tables;

/**
 * Class to store two objects, expected object and compare object
 * This can then be used obtain the details from these two object
 * @author gareth
 *
 */
public class WebPropertyComparison {

	private WebProperty expectedWebProperty;
	private WebProperty comparedWebProperty;
	private boolean match;
	
	/**
	 * Constructor to take in and set the expected object and compared object
	 * @param expectedWebProperty
	 * @param comparedWebProperty
	 */
	public WebPropertyComparison(WebProperty expectedWebProperty, WebProperty comparedWebProperty){
		this.expectedWebProperty = expectedWebProperty;
		this.comparedWebProperty = comparedWebProperty;
	}

	/**
	 * getter method to obtain the expected webProperty object
	 * @return the expected WebProperty Object
	 */
	public WebProperty getExpectedWebProperty() {
		return expectedWebProperty;
	}

	/**
	 * Setter method to set the expected webproperty object
	 * @param webProperty
	 */
	public void setExpectedWebProperty(WebProperty expectedWebProperty) {
		this.expectedWebProperty = expectedWebProperty;
	}

	/**
	 * Getter method to obtain the compared object 
	 * @return the compared object 
	 */
	public WebProperty getComparedWebProperty() {
		return comparedWebProperty;
	}

	/**
	 * Setter method to set the compared object
	 * @param comparedWebProperty takes in the object classed as the compared object
	 */
	public void setComparedWebProperty(WebProperty comparedWebProperty) {
		this.comparedWebProperty = comparedWebProperty;
	}

	/**
	 * Mehtod to compare the values which are properties within each of the objects 
	 * @return a boolean whether the values match
	 */
	public boolean isMatch() {
		return (expectedWebProperty.getValue()).equals(comparedWebProperty.getValue());
	}

	/**
	 * setter method to set the boolean value of the match variable
	 * @param match takes a boolean 
	 */
	public void setMatch(boolean match) {
		this.match = match;
	}

}
