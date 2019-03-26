package com.excilys.service;

import java.sql.SQLException;
import java.util.Optional;

import com.excilys.model.Computer;
import com.excilys.service.JDBC.JDBCComputer;

public class EditComputerService {
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Computer getComputer(int id) {
		JDBCComputer jdbc = new JDBCComputer();
		try {
			Optional<Computer> comp = jdbc.select(id);
			if (comp.isPresent()) {
				return comp.get();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
