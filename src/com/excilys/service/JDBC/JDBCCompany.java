package com.excilys.service.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.excilys.driver.SQLDriver;
import com.excilys.model.Company;
import com.excilys.utils.LoggerConfigurator;
import com.excilys.utils.MapResultSet;

public class JDBCCompany {
	private static final Logger log = LoggerConfigurator.configureLogger(JDBCCompany.class);
	private final String SELECTALL = "SELECT * FROM company;";

	public ArrayList<Company> selectAll() throws SQLException {
		SQLDriver driver = null;
		ArrayList<Company> comp;
		log.debug("list computers");
		PreparedStatement statement = null;
		try {
			driver = SQLDriver.start();
			statement = driver.prepareConnection(SELECTALL);
			ResultSet result = statement.executeQuery();
			comp = MapResultSet.mapAllResultSetCompany(result);
			return comp;
		} finally {
			driver.close();
		}
	}
}
