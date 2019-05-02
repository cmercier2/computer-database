package com.excilys.enums;

public enum OrderBy {
	NAME("name"), ID("id"), INTRODUCED("introduced"), DISCONTINUED("discontinued");

	private String row;

	public String get() {
		return this.row;
	}

	private OrderBy(String row) {
		this.row = row;
	}

}
