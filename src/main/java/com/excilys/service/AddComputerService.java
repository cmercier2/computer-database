package com.excilys.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.excilys.DTO.ComputerDTO;
import com.excilys.JDBC.JDBCCompany;
import com.excilys.JDBC.JDBCComputer;
import com.excilys.exception.InvalidComputerName;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.utils.ArgumentHandler;
import com.excilys.model.Computer.ComputerBuilder;

@Service
public class AddComputerService {
	/**
	 * 
	 * @param computerDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 * @throws InvalidComputerName
	 */
	public void addComputer(ComputerDTO computerDTO) throws ClassNotFoundException, SQLException, InvalidComputerName {
		JDBCComputer jdb = new JDBCComputer();
		Computer computer = new ComputerBuilder()
				.setName(computerDTO.getName() != null ? computerDTO.getName().trim() : "")
				.setIntroduced(ArgumentHandler.parseDate(computerDTO.getIntroduced()).orElse(null))
				.setDiscontinued(ArgumentHandler.parseDate(computerDTO.getDiscontinued()).orElse(null))
				.setCompany(computerDTO.getCompany()).build();
		System.out.println(computer.toString());
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
		JDBCCompany jdbc = new JDBCCompany();
		try {
			listCompany = jdbc.selectAll();
			return listCompany;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return listCompany;
	}

}
