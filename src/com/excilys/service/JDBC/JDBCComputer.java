package com.excilys.service.JDBC;

import java.sql.ResultSet;

import com.excilys.driver.SQLDriver;
import com.excilys.model.Computer;
import com.excilys.service.IDAO.IDAOComputer;

public class JDBCComputer implements IDAOComputer{
	
	@Override
	public void create(Computer computer) {
		
		
	}

	@Override
	public void update(Computer computer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Computer computer) {
		String test;
		
	}

	@Override
	public ResultSet show(Computer computer) {
		return null;
	}

	@Override
	public ResultSet showAll() {
		return null;
	}
	

}
