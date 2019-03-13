package com.excilys.driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLDriver {
	private static Connection conn;
	
	private void initiateConnection() {
		String url = "jdbc:mysql://localhost:3306/computer-database-db";
		try {
			conn = DriverManager.getConnection(url, "admincdb", "qwerty1234");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public static SQLDriver start() {
		SQLDriver driver = new SQLDriver();
		driver.initiateConnection();
		return driver;
	}
	
	public void close() {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
}
