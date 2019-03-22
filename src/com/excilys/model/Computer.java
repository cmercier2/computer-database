package com.excilys.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;
import java.util.Optional;

public class Computer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id = -1;
	private String name = "";
	private Date introduced;
	private Date discontinued;
	private int company;

	
	private Computer(ComputerBuilder comp) {
		this.id = comp.id;
		this.name = comp.name;
		this.introduced = comp.introduced;
		this.discontinued = comp.discontinued;
		this.company = comp.company;
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
	 * getter name
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * setter name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * getter introduced date
	 * 
	 * @return
	 */
	public Date getIntroduced() {
		return introduced;
	}

	/**
	 * setter introduced date
	 * 
	 * @param introduced
	 */
	public void setIntroduced(Date introduced) {
		this.introduced = introduced;
	}

	/**
	 * getter discontinued date
	 * 
	 * @return
	 */
	public Date getDiscontinued() {
		return discontinued;
	}

	/**
	 * setter discontinued date
	 * 
	 * @param discontinued
	 */
	public void setDiscontinued(Date discontinued) {
		this.discontinued = discontinued;
	}

	/**
	 * getter company id
	 * 
	 * @return
	 */
	public int getCompany() {
		return company;
	}

	/**
	 * setter company id
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

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Computer) {
			Computer c = (Computer) obj;
			return this.id == c.getId() && this.name.equals(c.getName()) && this.introduced.equals(c.getIntroduced())
					&& this.discontinued.equals(c.getDiscontinued()) && this.company == c.getCompany();
		}
		return false;
	}
	
	public static class ComputerBuilder{
		private int id;
		private String name;
		private Date introduced;
		private Date discontinued;
		private int company;
		
		public ComputerBuilder setId(int id) {
			this.id = id;
			return this;
		}
		
		public ComputerBuilder setName(String name) {
			this.name = name;
			return this;
		}
		
		public ComputerBuilder setIntroduced(Date introduced) {
			this.introduced = introduced;
			return this;
		}
		
		public ComputerBuilder setDiscontinuede(Date discontinued) {
			this.discontinued = discontinued;
			return this;
		}
		
		public ComputerBuilder setCompany(int company) {
			this.company = company;
			return this;
		}
		
		public Computer build() {
			return new Computer(this);
		}
		
	}

}
