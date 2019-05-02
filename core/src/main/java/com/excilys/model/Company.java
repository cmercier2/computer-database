package com.excilys.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "company")
public class Company {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int id;
	@Column(name = "name")
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

	public Company() {
		super();
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
