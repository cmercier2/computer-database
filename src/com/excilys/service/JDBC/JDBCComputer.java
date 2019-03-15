package com.excilys.service.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.driver.SQLDriver;
import com.excilys.model.Computer;
import com.excilys.service.IDAO.IDAOComputer;
import com.excilys.utils.MapResultSet;

public class JDBCComputer implements IDAOComputer {
	private SQLDriver driver;
	private final String SELECTALL = "SELECT * FROM computer;";
	private final String INSERT = "INSERT INTO computer (name,introduced,discontinued,company_id) VALUES (?, ?, ?, ?);";
	private final String UPDATE = "INSERT INTO computer (name,introduced,discontinued,company_id) VALUES (?, ?, ?, ?);";
	private final String DELETE = "DELETE FROM computer WHERE id = ?;";
	private final String SELECT = "SELECT * FROM computer WHERE id = ?;";

	public JDBCComputer(SQLDriver driver) {
		this.driver = driver;
	}

	@Override
	public void create(Computer computer) {
		PreparedStatement statement = null;
		try {
			statement = driver.prepareConnection(INSERT);
			statement.setString(1, computer.getName());
			statement.setDate(2, computer.getIntroduced());
			statement.setDate(3, computer.getDiscontinued());
			statement.setInt(4, computer.getCompany());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void update(Computer computer) {
		PreparedStatement statement = null;
		try {
			statement = driver.prepareConnection(UPDATE);
			statement.setInt(1, computer.getId());
			statement.setString(2, computer.getName());
			statement.setDate(3, computer.getIntroduced());
			statement.setDate(4, computer.getDiscontinued());
			statement.setInt(3, computer.getCompany());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void delete(Computer computer) {
		PreparedStatement statement = null;
		try {
			statement = driver.prepareConnection(DELETE);
			statement.setInt(1, computer.getId());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	@Override
	public Computer select(Computer computer) {
		PreparedStatement statement = null;
		Computer comp = null;
		try {
			driver = SQLDriver.start();
			statement = driver.prepareConnection(SELECT);
			statement.setInt(1, computer.getId());
			ResultSet result = statement.executeQuery();
			comp = MapResultSet.mapResultSetComputer(result);
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

	@Override
	public ArrayList<Computer> selectAll() {
		PreparedStatement statement = null;
		ArrayList<Computer> comp;
		try {
			statement = driver.prepareConnection(SELECTALL);
			ResultSet result = statement.executeQuery();
			comp = MapResultSet.mapAllResultSetComputer(result);
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
