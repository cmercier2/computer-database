package com.excilys.dto;

import java.io.Serializable;
import java.util.Objects;

public class ComputerDTO implements Serializable {
	private static final long serialVersionUID = 3586569842068018630L;
	private int id;
	private String name;
	private String introduced;
	private String discontinued;
	private int companyId;

	private ComputerDTO(ComputerDTOBuilder builderDTO) {
		this.id = builderDTO.id;
		this.name = builderDTO.name;
		this.introduced = builderDTO.introduced;
		this.discontinued = builderDTO.discontinued;
		this.companyId = builderDTO.companyId;
	}

	private ComputerDTO() {
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

	public int getCompanyId() {
		return companyId;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIntroduced(String introduced) {
		this.introduced = introduced;
	}

	public void setDiscontinued(String discontinued) {
		this.discontinued = discontinued;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		return this.id + " " + this.name + " " + Objects.toString(this.introduced) + " "
				+ Objects.toString(this.discontinued) + " " + this.companyId;
	}

	public static class ComputerDTOBuilder {
		private int id;
		private String name;
		private String introduced;
		private String discontinued;
		private int companyId;

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

		public ComputerDTOBuilder setCompany(int companyId) {
			this.companyId = companyId;
			return this;
		}

		public ComputerDTO build() {
			return new ComputerDTO(this);
		}

	}
}
