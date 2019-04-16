package com.excilys.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.excilys.dto.ComputerDTO;
import com.excilys.exception.InvalidComputerName;
import com.excilys.jdbc.JDBCCompany;
import com.excilys.jdbc.JDBCComputer;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.model.Computer.ComputerBuilder;
import com.excilys.utils.ArgumentHandler;

@Service
public class EditComputerService {

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws InvalidComputerName
	 */
	public Optional<Computer> getComputer(int id) throws ClassNotFoundException, SQLException, InvalidComputerName {
		JDBCComputer jdbc = new JDBCComputer();
		return jdbc.select(id);
	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ArrayList<Company> listCompanys() throws ClassNotFoundException, SQLException {
		JDBCCompany jdbc = new JDBCCompany();
		return jdbc.selectAll();
	}

	public void editComputer(ComputerDTO computerDTO) throws InvalidComputerName, ClassNotFoundException, SQLException {
		JDBCComputer jdb = new JDBCComputer();
		Computer computer = new ComputerBuilder().setId(computerDTO.getId())
				.setName(computerDTO.getName() != null ? computerDTO.getName().trim() : "")
				.setIntroduced(ArgumentHandler.parseDate(computerDTO.getIntroduced()).orElse(null))
				.setDiscontinued(ArgumentHandler.parseDate(computerDTO.getDiscontinued()).orElse(null))
				.setCompany(computerDTO.getCompany()).build();
		System.out.println(computer.toString());
		if ("".equals(computer.getName()))
			throw new InvalidComputerName("Computer name can't be empty");
		jdb.update(computer);
	}

}
