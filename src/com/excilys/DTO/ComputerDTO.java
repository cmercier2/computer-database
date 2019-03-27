package com.excilys.DTO;

import java.io.Serializable;

public class ComputerDTO implements Serializable {
	private static final long serialVersionUID = 3586569842068018630L;
	private int id;
	private String name;
	private String introduced;
	private String discontinued;
	private int company;

	private ComputerDTO(ComputerDTOBuilder builderDTO) {
		this.id = builderDTO.id;
		this.name = builderDTO.name;
		this.introduced = builderDTO.introduced;
		this.discontinued = builderDTO.discontinued;
		this.company = builderDTO.company;
	}

	public ComputerDTO() {
	}

	public int getId() {
		return this.id;
	}

	public String getName() {
		return name;
	}

	public String getIntroduced() {
		return introduced;
	}

	public String getDiscontinued() {
		return discontinued;
	}

	public int getCompany() {
		return company;
	}

	public static class ComputerDTOBuilder {
		private int id;
		private String name;
		private String introduced;
		private String discontinued;
		private int company;

		public ComputerDTOBuilder setId(int id) {
			this.id = id;
			return this;
		}

		public ComputerDTOBuilder setName(String name) {
			this.name = name;
			return this;
		}

		public ComputerDTOBuilder setIntroduced(String introduced) {
			this.introduced = introduced;
			return this;
		}

		public ComputerDTOBuilder setDiscontinued(String discontinued) {
			this.discontinued = discontinued;
			return this;
		}

		public ComputerDTOBuilder setCompany(int company) {
			this.company = company;
			return this;
		}

		public ComputerDTO build() {
			return new ComputerDTO(this);
		}

	}
}
