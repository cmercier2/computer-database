package com.excilys.model;

import java.util.Objects;

public class Company {
	private int id;
	private String name;

	/**
	 * 
	 * @param id
	 * @param name
	 */
	public Company(int id, String name) {
		Objects.requireNonNull(name);
		this.setId(id);
		this.setName(name);
	}

	/**
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return this.id + " " + this.name;
	}

}
