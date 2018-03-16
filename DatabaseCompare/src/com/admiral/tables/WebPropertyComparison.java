package com.admiral.tables;

public class WebPropertyComparison {

	private WebProperty webProperty;
	private WebProperty comparedWebProperty;
	private boolean match;
	
	public WebPropertyComparison(WebProperty webProperty, WebProperty comparedWebProperty){
		this.webProperty = webProperty;
		this.comparedWebProperty = comparedWebProperty;
	}

	public WebProperty getWebProperty() {
		return webProperty;
	}

	public void setWebProperty(WebProperty webProperty) {
		this.webProperty = webProperty;
	}

	public WebProperty getComparedWebProperty() {
		return comparedWebProperty;
	}

	public void setComparedWebProperty(WebProperty comparedWebProperty) {
		this.comparedWebProperty = comparedWebProperty;
	}

	public boolean isMatch() {
		return (webProperty.getValue()).equals(comparedWebProperty.getValue());
	}

	public void setMatch(boolean match) {
		this.match = match;
	}

}
