package com.excilys.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import com.excilys.enums.OrderBy;
import com.excilys.model.Computer;
import com.excilys.utils.MapResultSet;

public class JDBCComputer {
	// private static final Logger log =
	// LoggerConfigurator.configureLogger(JDBCComputer.class);
	private final String SELECTALL = "SELECT id, name, introduced, discontinued, company_id FROM computer;";
	private final String INSERT = "INSERT INTO computer (name,introduced,discontinued,company_id) VALUES (?, ?, ?, ?);";
	private final String UPDATE = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?;";
	private final String DELETE = "DELETE FROM computer WHERE id = ?;";
	private final String SELECT = "SELECT id, name, introduced, discontinued, company_id FROM computer WHERE id = ?;";
	private final String SELECTSEARCHORDERNOT = "SELECT id, name, introduced, discontinued, company_id FROM computer WHERE name LIKE ? ORDER BY ? LIMIT ? OFFSET ?;";
	private final String SELECTSEARCHNAME = "SELECT id, name, introduced, discontinued, company_id FROM computer WHERE name LIKE ? ORDER BY name LIMIT ? OFFSET ?;";
	private final String SELECTSEARCHINTRODUCED = "SELECT id, name, introduced, discontinued, company_id FROM computer WHERE name LIKE ? ORDER BY introduced LIMIT ? OFFSET ?;";
	private final String SELECTSEARCH = "SELECT id, name, introduced, discontinued, company_id FROM computer WHERE name LIKE ? ORDER BY discontinued LIMIT ? OFFSET ?;";
	private final String COUNT = "SELECT * FROM computer WHERE name like ?;";
	private final String url = "jdbc:mysql://localhost:3306/computer-database-db?serverTimezone=UTC";
	private final String user = "admincdb";
	private final String mdp = "qwerty1234";

	public int create(Computer computer) throws SQLException, ClassNotFoundException {
		// log.debug("create computer : " + computer.toString());
		Class.forName("com.mysql.cj.jdbc.Driver");
		try (Connection conn = DriverManager.getConnection(url, user, mdp)) {
			PreparedStatement statement = conn.prepareStatement(INSERT);
			statement.setString(1, computer.getName());
			statement.setDate(2, computer.getIntroduced());
			statement.setDate(3, computer.getDiscontinued());
			statement.setInt(4, computer.getCompany());
			return statement.executeUpdate();
		}
	}

	public int update(Computer computer) throws SQLException, ClassNotFoundException {
		// log.debug("update computer : " + computer.toString());
		Class.forName("com.mysql.cj.jdbc.Driver");
		try (Connection conn = DriverManager.getConnection(url, user, mdp)) {
			PreparedStatement statement = conn.prepareStatement(UPDATE);
			statement.setString(1, computer.getName());
			statement.setDate(2, computer.getIntroduced());
			statement.setDate(3, computer.getDiscontinued());
			statement.setInt(4, computer.getCompany());
			statement.setInt(5, computer.getId());
			return statement.executeUpdate();
		}
	}

	public int delete(int id) throws SQLException, ClassNotFoundException {
		// log.debug("delete computer : " + computer.toString());
		Class.forName("com.mysql.cj.jdbc.Driver");
		try (Connection conn = DriverManager.getConnection(url, user, mdp)) {
			PreparedStatement statement = conn.prepareStatement(DELETE);
			statement.setInt(1, id);
			return statement.executeUpdate();
		}
	}

	public Optional<Computer> select(int id) throws SQLException, ClassNotFoundException {
		// log.debug("select computer : " + id);
		Class.forName("com.mysql.cj.jdbc.Driver");
		try (Connection conn = DriverManager.getConnection(url, user, mdp)) {
			PreparedStatement statement = conn.prepareStatement(SELECT);
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			return MapResultSet.mapResultSetComputer(result);
		}
	}

	public ArrayList<Computer> selectAll() throws SQLException, ClassNotFoundException {
		// log.debug("list computers");
		Class.forName("com.mysql.cj.jdbc.Driver");
		try (Connection conn = DriverManager.getConnection(url, user, mdp)) {
			PreparedStatement statement = conn.prepareStatement(SELECTALL);
			ResultSet result = statement.executeQuery();
			return MapResultSet.mapAllResultSetComputer(result);
		}
	}
	
	public ArrayList<Computer> selectAllSearchOrder(int start, int step, String search, OrderBy order) throws SQLException, ClassNotFoundException {
		// log.debug("list computers");
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("IN JDBC : " + order.get());
		try (Connection conn = DriverManager.getConnection(url, user, mdp)) {
			PreparedStatement statement = conn.prepareStatement(SELECTSEARCHORDERNOT);
			statement.setString(1, "%" + search + "%");
			statement.setInt(3, step);
			statement.setInt(4, start);
			System.out.println(statement);
			ResultSet result = statement.executeQuery();
			return MapResultSet.mapAllResultSetComputer(result);
		}
	}
	
	
	public int count(String str) throws SQLException, ClassNotFoundException {
		System.out.println("str : " + str);
		Class.forName("com.mysql.cj.jdbc.Driver");
		try (Connection conn = DriverManager.getConnection(url, user, mdp)) {
			PreparedStatement statement = conn.prepareStatement(COUNT);
			statement.setString(1, "%" + str + "%");
			ResultSet result = statement.executeQuery();
			result.last(); 
			return result.getRow();
		}
	}

}
