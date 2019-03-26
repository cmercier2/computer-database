package com.excilys.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.service.JDBC.JDBCCompany;
import com.excilys.service.JDBC.JDBCComputer;

public class AddComputerService {
	/**
	 * 
	 * @param values
	 */
	public void addComputer(String values) {
		JDBCComputer jdb = new JDBCComputer();
		Optional<Computer> opt = ArgumentHandler.creationArgument(values);
		if (opt.isPresent()) {
			try {
				jdb.create(opt.get());
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
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
