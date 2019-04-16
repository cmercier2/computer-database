package com.excilys.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.hikaricp.HikariCP;
import com.excilys.model.Company;
import com.excilys.utils.MapResultSet;

public class JDBCCompany {
	// private static final Logger log =
	// LoggerConfigurator.configureLogger(JDBCCompany.class);
	private final String SELECTALL = "SELECT id, name FROM company;";
	private final String DELETE = "DELETE FROM company WHERE id = ?;";

	public ArrayList<Company> selectAll() throws SQLException, ClassNotFoundException {
		ArrayList<Company> comp;
		// log.debug("list computers");
		try (Connection conn = HikariCP.getInstance().getConnection();
				PreparedStatement statement = conn.prepareStatement(SELECTALL);) {
			ResultSet result = statement.executeQuery();
			comp = MapResultSet.mapAllResultSetCompany(result);
		}
		return comp;
	}

	public int delete(int id) throws SQLException {
		int results = 0;
		Connection conn = HikariCP.getInstance().getConnection();
		try {
			conn.setAutoCommit(false);
			new JDBCComputer().deleteComputerByCompanyId(id);
			PreparedStatement ps = conn.prepareStatement(DELETE);
			ps.setInt(1, id);
			results = ps.executeUpdate();

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.commit();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return results;
	}

}
