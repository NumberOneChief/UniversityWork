package com.admiral.tables;

public class WebProperty{

	
	private int id;
	private String key;
	private String brand;
	private String value;
	
	public WebProperty(int id, String key, String brand, String value) {
		this.id = id;
		this.key = key;
		this.brand = brand;
		this.value = value;
	}
	public int getId() {
		return id;
	}
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getBrand() {
		return brand;
	}
	

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getValue() {
		return value;
	}
	
	public boolean compareKeys(String keyOne, String keyTwo) {
		if(keyOne.equals(keyTwo)) {
			return true;
		}
		return false;
	}

	public boolean equals(WebProperty compareProperty) {
		if(this.key.equals(compareProperty.key) && this.brand.equals(compareProperty.brand)) {
			return true;
		}
		return false;
	}	
}
