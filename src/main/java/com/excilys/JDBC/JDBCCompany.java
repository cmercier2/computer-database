package com.excilys.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.excilys.model.Company;
import com.excilys.utils.MapResultSet;

public class JDBCCompany {
	// private static final Logger log =
	// LoggerConfigurator.configureLogger(JDBCCompany.class);
	private final String SELECTALL = "SELECT id, name FROM company;";
	private final String url = "jdbc:mysql://localhost:3306/computer-database-db";
	private final String user = "admincdb";
	private final String mdp = "qwerty1234";

	public ArrayList<Company> selectAll() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		ArrayList<Company> comp;
		// log.debug("list computers");
		try (Connection conn = DriverManager.getConnection(url, user, mdp)) {
			PreparedStatement statement = conn.prepareStatement(SELECTALL);
			ResultSet result = statement.executeQuery();
			comp = MapResultSet.mapAllResultSetCompany(result);
		}
		return comp;
	}
}
