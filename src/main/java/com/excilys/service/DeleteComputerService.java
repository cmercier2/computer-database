package com.excilys.service;

import java.sql.SQLException;

import com.excilys.JDBC.JDBCComputer;
import com.excilys.utils.ArgumentHandler;

public class DeleteComputerService {

	public void delete(String[] toDelete) throws ClassNotFoundException, SQLException {
		if(toDelete.length > 0) {
			JDBCComputer jdb = new JDBCComputer();
			for(String idStr : toDelete) {
				int id = ArgumentHandler.parseId(idStr);
				jdb.delete(id);
			}
		}
	}

}
