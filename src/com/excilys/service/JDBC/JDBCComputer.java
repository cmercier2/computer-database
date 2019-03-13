package com.excilys.service.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.excilys.command.Result;
import com.excilys.driver.SQLDriver;
import com.excilys.model.Computer;
import com.excilys.service.IDAO.IDAOComputer;

public class JDBCComputer implements IDAOComputer{
	
	@Override
	public void create(Computer computer) {
		String request = "INSERT into computer (name,introduced,discontinued,company_id) values (?, null, null, ?);";
		SQLDriver driver = null;
		try {
			driver =  SQLDriver.start();
			PreparedStatement statement = driver.prepareConnection(request);
			statement.setString(1,computer.getName());
			statement.setInt(2,computer.getCompany());
			statement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			driver.close();
		}
	}

	@Override
	public void update(Computer computer) {
		String request = "INSERT into computer (name,introduced,discontinued,company_id) values (?, null, null, ?);";
		SQLDriver driver = null;
		try {
			driver =  SQLDriver.start();
			PreparedStatement statement = driver.prepareConnection(request);
			statement.setString(1,computer.getName());
			statement.setInt(2,computer.getCompany());
			statement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			driver.close();
		}
	}

	@Override
	public void delete(Computer computer) {
		String request = "DELETE FROM computer WHERE id = ?;";
		SQLDriver driver = null;
		try {
			driver =  SQLDriver.start();
			PreparedStatement statement = driver.prepareConnection(request);
			statement.setInt(1,computer.getId());
			statement.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			driver.close();
		}
		
	}

	@Override
	public Result select(Computer computer) {
		String request = "SELECT * FROM computer WHERE id = ?;";
		SQLDriver driver = null;
		Result res = null;
		try {
			driver =  SQLDriver.start();
			PreparedStatement statement = driver.prepareConnection(request);
			statement.setInt(1,computer.getId());
			ResultSet result = statement.executeQuery();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			driver.close();
		}
		return res;
	}

	@Override
	public Result selectAll() {
		SQLDriver driver = null;
		try {
			driver =  SQLDriver.start();
			
		}finally {
			driver.close();
		}
		return null;
	}
	

}
