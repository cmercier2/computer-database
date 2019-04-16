package com.excilys.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import com.excilys.enums.OrderBy;
import com.excilys.hikaricp.HikariCP;
import com.excilys.model.Computer;
import com.excilys.utils.MapResultSet;

public class JDBCComputer {
	private final String INSERT = "INSERT INTO computer (name,introduced,discontinued,company_id) VALUES (?, ?, ?, ?);";
	private final String UPDATE = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?;";
	private final String DELETE = "DELETE FROM computer WHERE id = ?;";
	private final String DELETECOMPUTERS = "DELETE FROM computer WHERE company_id = ?;";
	private final String SELECT = "SELECT id, name, introduced, discontinued, company_id FROM computer WHERE id = ?;";
	private final String SELECTSEARCHORDERNOT = "SELECT id, name, introduced, discontinued, company_id FROM computer WHERE name LIKE ? LIMIT ? OFFSET ?;";
	private final String SELECTSEARCHNAME = "SELECT id, name, introduced, discontinued, company_id FROM computer WHERE name LIKE ? ORDER BY name LIMIT ? OFFSET ?;";
	private final String SELECTSEARCHINTRODUCED = "SELECT id, name, introduced, discontinued, company_id FROM computer WHERE name LIKE ? ORDER BY introduced LIMIT ? OFFSET ?;";
	private final String SELECTSEARCHDISCONTINUED = "SELECT id, name, introduced, discontinued, company_id FROM computer WHERE name LIKE ? ORDER BY discontinued LIMIT ? OFFSET ?;";
	private final String COUNT = "SELECT * FROM computer WHERE name like ?;";

	/**
	 * 
	 * @param computer
	 * @return
	 * @throws SQLException
	 */
	public int create(Computer computer) throws SQLException {
		try (Connection conn = HikariCP.getInstance().getConnection();
				PreparedStatement statement = conn.prepareStatement(INSERT)) {
			statement.setString(1, computer.getName());
			statement.setDate(2, computer.getIntroduced());
			statement.setDate(3, computer.getDiscontinued());
			statement.setInt(4, computer.getCompany());
			return statement.executeUpdate();
		}
	}

	/**
	 * 
	 * @param computer
	 * @return
	 * @throws SQLException
	 */
	public int update(Computer computer) throws SQLException {
		try (Connection conn = HikariCP.getInstance().getConnection();
				PreparedStatement statement = conn.prepareStatement(UPDATE)) {
			statement.setString(1, computer.getName());
			statement.setDate(2, computer.getIntroduced());
			statement.setDate(3, computer.getDiscontinued());
			statement.setInt(4, computer.getCompany());
			statement.setInt(5, computer.getId());
			return statement.executeUpdate();
		}
	}

	public int deleteComputerByCompanyId(int id) throws SQLException {
		int results = 0;
		Connection conn = HikariCP.getInstance().getConnection();
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement(DELETECOMPUTERS);
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

	public int delete(int id) throws SQLException {
		int results = 0;
		Connection conn = HikariCP.getInstance().getConnection();
		try {
			conn.setAutoCommit(false);
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

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	/*
	public int delete(int id) throws SQLException {
		try (Connection conn = HikariCP.getInstance().getConnection();
				PreparedStatement statement = conn.prepareStatement(DELETE)) {
			statement.setInt(1, id);
			return statement.executeUpdate();
		}
	}*/

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Optional<Computer> select(int id) throws SQLException {
		try (Connection conn = HikariCP.getInstance().getConnection();
				PreparedStatement statement = conn.prepareStatement(SELECT)) {
			statement.setInt(1, id);
			ResultSet result = statement.executeQuery();
			return MapResultSet.mapResultSetComputer(result);
		}
	}

	/**
	 * 
	 * @param start
	 * @param step
	 * @param search
	 * @param order
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Computer> selectAllSearchOrder(int start, int step, String search, OrderBy order)
			throws SQLException {
		PreparedStatement statement = null;
		try (Connection conn = HikariCP.getInstance().getConnection()) {
			switch (order) {
			case NAME:
				statement = conn.prepareStatement(SELECTSEARCHNAME);
				break;
			case INTRODUCED:
				statement = conn.prepareStatement(SELECTSEARCHINTRODUCED);
				break;
			case DISCONTINUED:
				statement = conn.prepareStatement(SELECTSEARCHDISCONTINUED);
				break;
			default:
				statement = conn.prepareStatement(SELECTSEARCHORDERNOT);
				break;
			}
			statement.setString(1, "%" + search + "%");
			statement.setInt(2, step);
			statement.setInt(3, start);
			ResultSet result = statement.executeQuery();
			return MapResultSet.mapAllResultSetComputer(result);
		}
	}

	/**
	 * 
	 * @param str
	 * @return
	 * @throws SQLException
	 */
	public int count(String str) throws SQLException {
		try (Connection conn = HikariCP.getInstance().getConnection();
				PreparedStatement statement = conn.prepareStatement(COUNT)) {
			statement.setString(1, "%" + str + "%");
			ResultSet result = statement.executeQuery();
			result.last();
			return result.getRow();
		}
	}

}
