package com.excilys.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.dto.ComputerDTO;
import com.excilys.exception.InvalidComputerName;
import com.excilys.jdbctemplate.JDBCTemplateCompany;
import com.excilys.jdbctemplate.JDBCTemplateComputer;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.utils.ArgumentHandler;
import com.excilys.model.Computer.ComputerBuilder;

@Service
public class AddComputerService {
	@Autowired
	private JDBCTemplateComputer jdb;
	@Autowired
	private JDBCTemplateCompany jdbcompany;

	/**
	 * 
	 * @param computerDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws InvalidComputerName
	 */
	public void addComputer(ComputerDTO computerDTO) throws ClassNotFoundException, SQLException, InvalidComputerName {
		Computer computer = new ComputerBuilder()
				.setName(computerDTO.getName() != null ? computerDTO.getName().trim() : "")
				.setIntroduced(ArgumentHandler.parseDate(computerDTO.getIntroduced()).orElse(null))
				.setDiscontinued(ArgumentHandler.parseDate(computerDTO.getDiscontinued()).orElse(null))
				.setCompany(computerDTO.getCompanyId()).build();
		if ("".equals(computer.getName()))
			throw new InvalidComputerName("Computer name can't be empty");
		jdb.create(computer);
	}

	/**
	 * 
	 * @return
	 */
	public ArrayList<Company> listCompanys() {
		ArrayList<Company> listCompany = new ArrayList<>();
		try {
			listCompany = jdbcompany.selectAll();
			return listCompany;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listCompany;
	}

}
