package com.excilys.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Computer implements Serializable {
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
	 * getter name
	 * 
	 * @return
	 */
	public String getName() {
		return name;
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
	 * getter discontinued date
	 * 
	 * @return
	 */
	public Date getDiscontinued() {
		return discontinued;
	}

	/**
	 * getter company id
	 * 
	 * @return
	 */
	public int getCompany() {
		return company;
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
			return this.id == c.getId() && compareString(this.name, c.getName())
					&& compareDate(this.introduced, c.getIntroduced())
					&& compareDate(this.discontinued, c.getDiscontinued()) && this.company == c.getCompany();
		}
		return false;
	}

	private static boolean compareDate(Date dt1, Date dt2) {
		return (dt1 == null ? dt2 == null : dt1.equals(dt2));
	}

	private static boolean compareString(String str1, String str2) {
		return (str1 == null ? str2 == null : str1.equals(str2));
	}

	public static class ComputerBuilder {
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

		public ComputerBuilder setDiscontinued(Date discontinued) {
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
