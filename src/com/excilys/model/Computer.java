package com.excilys.model;
import java.sql.Date;
import java.util.Objects;

public class Computer {
	private int id;
	private String name;
	private Date introduced;
	private Date discontinued;
	private Company company;
	
	
	public Computer(int id, String name, Date introduced, Date discontinued, Company company) {
		Objects.requireNonNull(name);
		Objects.requireNonNull(introduced);
		Objects.requireNonNull(discontinued);
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
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


	public Company getCompany() {
		return company;
	}


	public void setCompany(Company company) {
		this.company = company;
	}
	
	
	
}
