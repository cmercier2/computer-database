package com.excilys.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Computer implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private Date introduced;
	private Date discontinued;
	private int company;

	/**
	 * 
	 * @param id
	 * @param name
	 * @param introduced
	 * @param discontinued
	 * @param company
	 */
	public Computer(int id, String name, Date introduced, Date discontinued, int company) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
	}

	/**
	 * 
	 * @param name
	 * @param introduced
	 * @param discontinued
	 * @param company
	 */
	public Computer(String name, Date introduced, Date discontinued, int company) {
		this.name = name;
		this.company = company;
		this.introduced = introduced;
		this.discontinued = discontinued;
	}

	/**
	 * 
	 * @param id
	 */
	public Computer(int id) {
		this.id = id;
	}
	
	public Computer(int id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * 
	 * @param name
	 */
	public Computer(String name) {
		this.name = name;
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

	/**
	 * 
	 * @return
	 */
	public Date getIntroduced() {
		return introduced;
	}

	/**
	 * 
	 * @param introduced
	 */
	public void setIntroduced(Date introduced) {
		this.introduced = introduced;
	}

	/**
	 * 
	 * @return
	 */
	public Date getDiscontinued() {
		return discontinued;
	}

	/**
	 * 
	 * @param discontinued
	 */
	public void setDiscontinued(Date discontinued) {
		this.discontinued = discontinued;
	}

	/**
	 * 
	 * @return
	 */
	public int getCompany() {
		return company;
	}

	/**
	 * 
	 * @param company
	 */
	public void setCompany(int company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return this.id + " " + this.name + " " + Objects.toString(this.introduced) + " "
				+ Objects.toString(this.discontinued) + " " + this.company;
	}

}
