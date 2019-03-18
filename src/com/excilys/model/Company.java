package com.excilys.model;

import java.io.Serializable;
import java.util.Objects;

public class Company implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	 * getter id
	 * 
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * setter id
	 * 
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * get name
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * seter name
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
