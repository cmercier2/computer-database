package com.excilys.service.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import org.apache.log4j.Logger;

import com.excilys.model.Computer;
import com.excilys.utils.LoggerConfigurator;
import com.excilys.utils.MapResultSet;

public class JDBCComputer {
	//private static final Logger log = LoggerConfigurator.configureLogger(JDBCComputer.class);
	private final String SELECTALL = "SELECT * FROM computer;";
	private final String SELECTALLWITHOFFSET = "SELECT * FROM computer LIMIT ? OFFSET ?;";
	private final String INSERT = "INSERT INTO computer (name,introduced,discontinued,company_id) VALUES (?, ?, ?, ?);";
	private final String UPDATE = "INSERT INTO computer (name,introduced,discontinued,company_id) VALUES (?, ?, ?, ?);";
	private final String DELETE = "DELETE FROM computer WHERE id = ?;";
	private final String SELECT = "SELECT * FROM computer WHERE id = ?;";
	private final String url = "jdbc:mysql://localhost:3306/computer-database-db";
	private final String user = "admincdb";
	private final String mdp = "qwerty1234";

	public int create(Computer computer) throws SQLException, ClassNotFoundException {
		//log.debug("create computer : " + computer.toString());
		Class.forName("com.mysql.cj.jdbc.Driver");
		try(Connection conn = DriverManager.getConnection(url, user, mdp)){
			PreparedStatement statement = conn.prepareStatement(INSERT);
			statement.setString(1, computer.getName());
			statement.setDate(2, computer.getIntroduced());
			statement.setDate(3, computer.getDiscontinued());
			statement.setInt(4, computer.getCompany());
			return statement.executeUpdate();
		}
	}

	public int update(Computer computer) throws SQLException, ClassNotFoundException {
		//log.debug("update computer : " + computer.toString());
		Class.forName("com.mysql.cj.jdbc.Driver");
		try(Connection conn = DriverManager.getConnection(url, user, mdp)){
			PreparedStatement statement = conn.prepareStatement(UPDATE);
			statement.setInt(1, computer.getId());
			statement.setString(2, computer.getName());
			statement.setDate(3, computer.getIntroduced());
			statement.setDate(4, computer.getDiscontinued());
			statement.setInt(5, computer.getCompany());
			return statement.executeUpdate();
		}
	}

	public int delete(Computer computer) throws SQLException, ClassNotFoundException {
		//log.debug("delete computer : " + computer.toString());
		Class.forName("com.mysql.cj.jdbc.Driver");
		try(Connection conn = DriverManager.getConnection(url, user, mdp)){
			PreparedStatement statement = conn.prepareStatement(DELETE);
			statement.setInt(1, computer.getId());
			return statement.executeUpdate();
		}
	}

	public Optional<Computer> select(int id) throws SQLException, ClassNotFoundException {
		//log.debug("select computer : " + id);
		Class.forName("com.mysql.cj.jdbc.Driver");
		try(Connection conn = DriverManager.getConnection(url, user, mdp)){
			PreparedStatement statement = conn.prepareStatement(SELECT);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			return MapResultSet.mapResultSetComputer(result);
		}
	}

	public ArrayList<Computer> selectAll() throws SQLException, ClassNotFoundException {
		//log.debug("list computers");
		Class.forName("com.mysql.cj.jdbc.Driver");
		ArrayList<Computer> comps;
		try(Connection conn = DriverManager.getConnection(url, user, mdp)){
			PreparedStatement statement = conn.prepareStatement(SELECTALL);
			ResultSet result = statement.executeQuery();
			comps = MapResultSet.mapAllResultSetComputer(result);
		} 
		return comps;
	}
	
	public ArrayList<Computer> selectAll(int start, int step) throws SQLException, ClassNotFoundException{
		//log.debug("list computers");
		Class.forName("com.mysql.cj.jdbc.Driver");
		ArrayList<Computer> comps;
		try(Connection conn = DriverManager.getConnection(url, user, mdp)){
			PreparedStatement statement = conn.prepareStatement(SELECTALLWITHOFFSET);
			statement.setInt(1, step);
			statement.setInt(2, start);
			ResultSet result = statement.executeQuery();
			comps = MapResultSet.mapAllResultSetComputer(result);
		} 
		return comps;
	}


}
