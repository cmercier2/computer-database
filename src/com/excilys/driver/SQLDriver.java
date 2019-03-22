package com.excilys.driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLDriver {
	private static Connection conn;
	private final String url = "jdbc:mysql://localhost:3306/computer-database-db";

	/**
	 * Start sql connection
	 * 
	 */
	private void initiateConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, "admincdb", "qwerty1234");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Factory method for creation of SQLDriver
	 * 
	 * @return SqlDriver
	 */
	public static SQLDriver start() {
		SQLDriver driver = new SQLDriver();
		driver.initiateConnection();
		return driver;
	}

	/**
	 * Prepare sql request
	 * 
	 * @param request
	 * @return PreparedStatement
	 * @throws SQLException
	 */
	public PreparedStatement prepareConnection(String request) throws SQLException {
		return conn.prepareStatement(request);
	}

	/**
	 * Close the sql connection
	 * @throws SQLException 
	 */
	public void close() throws SQLException {
			conn.close();
	}
}
