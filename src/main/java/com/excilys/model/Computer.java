package com.excilys.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "computer")
public class Computer implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "introduced")
	private Date introduced;
	@Column(name = "discontinued")
	private Date discontinued;
	@Column(name = "company_id")
	private int company;

	public Computer() {
		
	}

	public Computer(int id, String name, Date introduced, Date discontinued, int company) {
		this.id = id;
		this.name = name;
		this.introduced = introduced;
		this.discontinued = discontinued;
		this.company = company;
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

	/**
	 * getter id
	 * 
	 * @return
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * getter name
	 * 
	 * @return
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * getter introduced date
	 * 
	 * @return
	 */
	public void setIntroduced(Date introduced) {
		this.introduced = introduced;
	}

	/**
	 * getter discontinued date
	 * 
	 * @return
	 */
	public void setDiscontinued(Date discontinued) {
		this.discontinued = discontinued;
	}

	/**
	 * getter company id
	 * 
	 * @return
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

	
/*
	private Computer(ComputerBuilder comp) {
		this.id = comp.id;
		this.name = comp.name;
		this.introduced = comp.introduced;
		this.discontinued = comp.discontinued;
		this.company = comp.company;
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

		@NonNull
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

	}*/

}
