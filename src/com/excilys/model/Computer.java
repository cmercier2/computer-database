package com.excilys.model;
import java.sql.Date;
import java.util.Objects;

public class Computer {
	private int id;
	private String name;
	private Date introduced;
	private Date discontinued;
	private int company;
	
	
	public Computer(int id, String name, Date introduced, Date discontinued, int company) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
	}

	public Computer(int id) {
		this.id = id;
	}
	
	public Computer(String name, Date introduced, Date discontinued, int company) {
		this.name = name;
		this.company = company;
		this.introduced = introduced;
		this.discontinued = discontinued;
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


	public Date getIntroduced() {
		return introduced;
	}


	public void setIntroduced(Date introduced) {
		this.introduced = introduced;
	}


	public Date getDiscontinued() {
		return discontinued;
	}


	public void setDiscontinued(Date discontinued) {
		this.discontinued = discontinued;
	}


	public int getCompany() {
		return company;
	}


	public void setCompany(int company) {
		this.company = company;
	}
	
	@Override
	public String toString() {
		
		return this.id + " " + this.name + " " + Objects.toString(this.introduced) + " " + Objects.toString(this.discontinued) + " " + this.company;
	}
	
}
