package com.excilys.service.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import org.apache.log4j.Logger;

import com.excilys.driver.SQLDriver;
import com.excilys.model.Computer;
import com.excilys.utils.LoggerConfigurator;
import com.excilys.utils.MapResultSet;

public class JDBCComputer {
	private static final Logger log = LoggerConfigurator.configureLogger(JDBCComputer.class);
	private final String SELECTALL = "SELECT * FROM computer;";
	private final String INSERT = "INSERT INTO computer (name,introduced,discontinued,company_id) VALUES (?, ?, ?, ?);";
	private final String UPDATE = "INSERT INTO computer (name,introduced,discontinued,company_id) VALUES (?, ?, ?, ?);";
	private final String DELETE = "DELETE FROM computer WHERE id = ?;";
	private final String SELECT = "SELECT * FROM computer WHERE id = ?;";

	public int create(Computer computer) throws SQLException {
		SQLDriver driver = null;
		PreparedStatement statement = null;
		log.debug("create computer : " + computer.toString());
		try {
			driver = SQLDriver.start();
			statement = driver.prepareConnection(INSERT);
			statement.setString(1, computer.getName());
			statement.setDate(2, computer.getIntroduced());
			statement.setDate(3, computer.getDiscontinued());
			statement.setInt(4, computer.getCompany());
			return statement.executeUpdate();
		} finally {
			driver.close();
		}
	}

	public int update(Computer computer) throws SQLException {
		SQLDriver driver = null;
		PreparedStatement statement = null;
		log.debug("update computer : " + computer.toString());
		try {
			driver = SQLDriver.start();
			statement = driver.prepareConnection(UPDATE);
			statement.setInt(1, computer.getId());
			statement.setString(2, computer.getName());
			statement.setDate(3, computer.getIntroduced());
			statement.setDate(4, computer.getDiscontinued());
			statement.setInt(5, computer.getCompany());
			return statement.executeUpdate();
		} finally {
			driver.close();
		}
	}

	public int delete(Computer computer) throws SQLException {
		SQLDriver driver = null;
		PreparedStatement statement = null;
		log.debug("delete computer : " + computer.toString());
		try {
			driver = SQLDriver.start();
			statement = driver.prepareConnection(DELETE);
			statement.setInt(1, computer.getId());
			return statement.executeUpdate();
		} finally {
				statement.close();
		}

	}

	public Optional<Computer> select(Computer computer) throws SQLException {
		SQLDriver driver = null;
		PreparedStatement statement = null;
		log.debug("select computer : " + computer.toString());
		try {
			driver = SQLDriver.start();
			driver = SQLDriver.start();
			statement = driver.prepareConnection(SELECT);
			statement.setInt(1, computer.getId());
			ResultSet result = statement.executeQuery();
			return MapResultSet.mapResultSetComputer(result);
		} finally {
				statement.close();
		}
	}

	public ArrayList<Computer> selectAll() throws SQLException {
		SQLDriver driver = null;
		PreparedStatement statement = null;
		log.debug("list computers");
		ArrayList<Computer> comps;
		try {
			driver = SQLDriver.start();
			statement = driver.prepareConnection(SELECTALL);
			ResultSet result = statement.executeQuery();
			comps = MapResultSet.mapAllResultSetComputer(result);
			return comps;
		} finally {
			driver.close();
		}
	}

}
