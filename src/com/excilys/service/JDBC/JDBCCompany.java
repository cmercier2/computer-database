package com.excilys.service.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.driver.SQLDriver;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.service.IDAO.IDAOCompany;
import com.excilys.utils.ParseResultSet;

public class JDBCCompany implements IDAOCompany{

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
		String request = "SELECT * FROM company;";
		SQLDriver driver = null;
		ArrayList<Company> comp;
		try {
			driver =  SQLDriver.start();
			PreparedStatement statement = driver.prepareConnection(request);
			ResultSet result = statement.executeQuery();
			comp = ParseResultSet.parseAllResultSetCompany(result);
			return comp;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			driver.close();
		}
		return null;
	}
}
