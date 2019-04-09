package com.excilys.enums;

public enum OrderBy {
	NAME("name"), ID("id"), INTRODUCED("introduced"), DISCONTINUED("discontinued");
	
	private String row; 
	  
    // getter method 
    public String get() 
    { 
        return this.row; 
    } 
  
    // enum constructor - cannot be public or protected 
    private OrderBy(String row) 
    { 
        this.row = row; 
    } 
    
}
