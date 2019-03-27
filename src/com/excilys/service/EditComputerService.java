package com.excilys.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.service.JDBC.JDBCCompany;
import com.excilys.service.JDBC.JDBCComputer;

public class EditComputerService {
	/**
	 * 
	 * @param id
	 * @return
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Optional<Computer> getComputer(int id) throws ClassNotFoundException, SQLException {
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
	
}
