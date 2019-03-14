package com.excilys.service.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.driver.SQLDriver;
import com.excilys.model.Company;
import com.excilys.service.IDAO.IDAOCompany;
import com.excilys.utils.MapResultSet;

public class JDBCCompany implements IDAOCompany {
	private SQLDriver driver;
	private final String SELECTALL = "SELECT * FROM company;";
	
	public JDBCCompany(SQLDriver driver) {
		this.driver = driver;
	}

	@Override
	public void create(Company company) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(Company company) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Company company) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<Company> selectAll() {
		ArrayList<Company> comp;
		PreparedStatement statement = null;
		try {
			 statement = driver.prepareConnection(SELECTALL);
			ResultSet result = statement.executeQuery();
			comp = MapResultSet.mapAllResultSetCompany(result);
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
