package com.excilys.service.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.excilys.driver.SQLDriver;
import com.excilys.model.Company;
import com.excilys.service.IDAO.IDAOCompany;
import com.excilys.utils.LoggerConfigurator;
import com.excilys.utils.MapResultSet;

public class JDBCCompany implements IDAOCompany {
	private static final Logger log = LoggerConfigurator.configureLogger(JDBCCompany.class);
	private SQLDriver driver;
	private final String SELECTALL = "SELECT * FROM company;";
	
	public JDBCCompany(SQLDriver driver) {
		this.driver = driver;
	}

	@Override
	public ArrayList<Company> selectAll() {
		ArrayList<Company> comp;
		log.debug("list computers");
		PreparedStatement statement = null;
		try {
			 statement = driver.prepareConnection(SELECTALL);
			ResultSet result = statement.executeQuery();
			comp = MapResultSet.mapAllResultSetCompany(result);
			return comp;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
