package com.excilys.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.dto.ComputerDTO;
import com.excilys.exception.InvalidComputerName;
import com.excilys.hibernate.HibernateCompany;
import com.excilys.hibernate.HibernateComputer;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.model.Computer.ComputerBuilder;
import com.excilys.utils.ArgumentHandler;

@Service
public class AddComputerService {
	@Autowired
	private HibernateComputer hbntComputer;
	@Autowired
	private HibernateCompany hbntCompany;
	
	/**
	 * 
	 * @param computerDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws InvalidComputerName
	 */
	public void addComputer(ComputerDTO computerDTO) throws ClassNotFoundException, SQLException, InvalidComputerName {
		Company company = hbntCompany.getCompanyById(computerDTO.getCompany());
		Computer computer = new ComputerBuilder()
				.setName(computerDTO.getName() != null ? computerDTO.getName().trim() : "")
				.setIntroduced(ArgumentHandler.parseDate(computerDTO.getIntroduced()).orElse(null))
				.setDiscontinued(ArgumentHandler.parseDate(computerDTO.getDiscontinued()).orElse(null))
				.setCompany(company).build();
		if ("".equals(computer.getName()))
			throw new InvalidComputerName("Computer name can't be empty");
		hbntComputer.create(computer);
	}

	/**
	 * 
	 * @return
	 */
	public List<Company> listCompanys() {
		List<Company> listCompany = new ArrayList<>();
		listCompany = hbntCompany.selectAll();
		return listCompany;
	}

}
