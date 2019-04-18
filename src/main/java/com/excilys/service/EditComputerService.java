package com.excilys.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.dto.ComputerDTO;
import com.excilys.dto.ComputerDTO.ComputerDTOBuilder;
import com.excilys.exception.InvalidComputerName;
import com.excilys.jdbctemplate.JDBCTemplateCompany;
import com.excilys.jdbctemplate.JDBCTemplateComputer;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.model.Computer.ComputerBuilder;
import com.excilys.utils.ArgumentHandler;

@Service
public class EditComputerService {
	@Autowired
	private JDBCTemplateComputer jdb;
	@Autowired
	private JDBCTemplateCompany jdbcompany;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 * @throws InvalidComputerName
	 */
	public Optional<ComputerDTO> getComputer(int id) throws SQLException, InvalidComputerName {
		Computer comp = jdb.select(id).orElse(new ComputerBuilder().setId(id).build());
		return Optional.of(new ComputerDTOBuilder().setId(comp.getId()).setName(comp.getName())
				.setIntroduced(comp.getIntroduced() != null ? comp.getIntroduced().toString() : "")
				.setDiscontinued(comp.getDiscontinued() != null ? comp.getDiscontinued().toString() : "")
				.setCompany(comp.getCompany()).build());
	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ArrayList<Company> listCompanys() throws SQLException {
		return jdbcompany.selectAll();
	}

	/**
	 * 
	 * @param computerDTO
	 * @throws InvalidComputerName
	 * @throws SQLException
	 */
	public void editComputer(ComputerDTO computerDTO) throws InvalidComputerName, SQLException {
		Computer computer = new ComputerBuilder().setId(computerDTO.getId())
				.setName(computerDTO.getName() != null ? computerDTO.getName().trim() : "")
				.setIntroduced(ArgumentHandler.parseDate(computerDTO.getIntroduced()).orElse(null))
				.setDiscontinued(ArgumentHandler.parseDate(computerDTO.getDiscontinued()).orElse(null))
				.setCompany(computerDTO.getCompanyId()).build();
		if ("".equals(computer.getName()))
			throw new InvalidComputerName("Computer name can't be empty");
		jdb.update(computer);
	}
}
