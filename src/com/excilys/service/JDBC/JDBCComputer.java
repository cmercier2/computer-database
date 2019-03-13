package com.excilys.service.JDBC;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.command.Result;
import com.excilys.driver.SQLDriver;
import com.excilys.model.Computer;
import com.excilys.service.IDAO.IDAOComputer;
import com.excilys.utils.ParseResultSet;

public class JDBCComputer implements IDAOComputer{
	
	@Override
	public void create(Computer computer) {
		String request = "INSERT into computer (name,introduced,discontinued,company_id) values (?, ?, ?, ?);";
		SQLDriver driver = null;
		try {
			driver =  SQLDriver.start();
			PreparedStatement statement = driver.prepareConnection(request);
			statement.setString(1,computer.getName());
			statement.setDate(2,computer.getIntroduced());
			statement.setDate(3,computer.getDiscontinued());
			statement.setInt(4,computer.getCompany());
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
	public void delete(int computer) {
		String request = "DELETE FROM computer WHERE id = ?;";
		SQLDriver driver = null;
		try {
			driver =  SQLDriver.start();
			PreparedStatement statement = driver.prepareConnection(request);
			statement.setInt(1,computer);
			statement.executeUpdate();
		}catch(SQLException e) {
			 e.printStackTrace();
		}finally {
			driver.close();
		}
		
	}

	@Override
	public Computer select(Computer computer) {
		String request = "SELECT * FROM computer WHERE id = ?;";
		SQLDriver driver = null;
		Computer comp = null;
 		try {
			driver =  SQLDriver.start();
			PreparedStatement statement = driver.prepareConnection(request);
			statement.setInt(1,computer.getId());
			ResultSet result = statement.executeQuery();
			comp = ParseResultSet.parseResultSetComputer(result);
			return comp;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			driver.close();
		}
 		return null;
	}

	
	
	@Override
	public ArrayList<Computer> selectAll() {
		String request = "SELECT * FROM computer;";
		SQLDriver driver = null;
		ArrayList<Computer> comp;
		try {
			driver =  SQLDriver.start();
			PreparedStatement statement = driver.prepareConnection(request);
			ResultSet result = statement.executeQuery();
			comp = ParseResultSet.parseAllResultSetComputer(result);
			return comp;
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			driver.close();
		}
		return null;
	}
	

}
