package com.excilys.model;

import java.util.Objects;

public class Company {
	private int id;
	private String name;
	
	public Company(int id, String name) {
		Objects.requireNonNull(name);
		this.setId(id);
		this.setName(name);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return this.id + " " + this.name;
	}
	
}
