package com.excilys.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.jdbctemplate.JDBCTemplateComputer;

@Service
public class DeleteComputerService {
	@Autowired
	private JDBCTemplateComputer jdb;

	/**
	 * 
	 * @param toDelete
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public void delete(String[] toDelete) throws ClassNotFoundException, SQLException {
		if (toDelete.length > 0) {
			for (String idStr : toDelete) {
				int id = Integer.parseInt(idStr);
				jdb.delete(id);
			}
		}
	}

}
