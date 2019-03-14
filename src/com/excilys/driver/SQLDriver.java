package com.excilys.driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLDriver {
	private static Connection conn;

	/**
	 * 
	 */
	private void initiateConnection() {
		String url = "jdbc:mysql://localhost:3306/computer-database-db";
		try {
			conn = DriverManager.getConnection(url, "admincdb", "qwerty1234");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return
	 */
	public static SQLDriver start() {
		SQLDriver driver = new SQLDriver();
		driver.initiateConnection();
		return driver;
	}

	/**
	 * 
	 * @param request
	 * @return
	 * @throws SQLException
	 */
	public PreparedStatement prepareConnection(String request) throws SQLException {
		return conn.prepareStatement(request);
	}

	/**
	 * 
	 */
	public void close() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
