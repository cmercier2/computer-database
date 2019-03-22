package com.excilys.service;

import java.sql.SQLException;
import java.util.Optional;

import com.excilys.model.Computer;
import com.excilys.service.JDBC.JDBCComputer;

public class AddComputerService {
	public void addComputer(String values) {
		JDBCComputer jdb = new JDBCComputer();
		Optional<Computer> opt = ArgumentHandler.creationArgument(values);
		if(opt.isPresent()) {
			try {
				jdb.create(opt.get());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
