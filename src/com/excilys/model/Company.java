package com.excilys.model;

import java.io.Serializable;
import java.util.Objects;

public class Company implements Serializable {
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
		this.id = id;
		this.name = name;
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
	 * get name
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return this.id + " " + this.name;
	}

}
