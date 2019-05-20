package com.excilys.utils;

import com.excilys.dto.ComputerDTO;
import com.excilys.dto.ComputerDTO.ComputerDTOBuilder;
import com.excilys.model.Computer;

public class ComputerMapper {

	public static ComputerDTO mapComputer(Computer computer){
		return new ComputerDTOBuilder().setId(computer.getId()).setName(computer.getName())
				.setIntroduced(null == computer.getIntroduced() ? "" : computer.getIntroduced().toString())
				.setDiscontinued(null == computer.getDiscontinued() ? "" : computer.getDiscontinued().toString())
				.setCompany(null == computer.getCompany() ? 0 : computer.getCompany().getId()).build();
	}
}