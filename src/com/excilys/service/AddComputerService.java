package com.excilys.service;

import java.sql.SQLException;
import java.util.ArrayList;
import com.excilys.DTO.ComputerDTO;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.service.JDBC.JDBCCompany;
import com.excilys.service.JDBC.JDBCComputer;
import com.excilys.utils.ArgumentHandler;
import com.excilys.model.Computer.ComputerBuilder;

public class AddComputerService {
	/**
	 * 
	 * @param computerDTO
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void addComputer(ComputerDTO computerDTO) throws ClassNotFoundException, SQLException {
		JDBCComputer jdb = new JDBCComputer();
		Computer computer = new ComputerBuilder().setName(computerDTO.getName())
				.setIntroduced(ArgumentHandler.parseDate(computerDTO.getIntroduced()).orElse(null))
				.setDiscontinued(ArgumentHandler.parseDate(computerDTO.getDiscontinued()).orElse(null))
				.setCompany(computerDTO.getCompany()).build();
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
