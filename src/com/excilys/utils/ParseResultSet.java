package com.excilys.utils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.model.Company;
import com.excilys.model.Computer;

public class ParseResultSet {
	
	public static ArrayList<Computer> parseAllResultSetComputer(ResultSet res) throws SQLException {
		ArrayList<Computer> computers = new ArrayList<>(); 
		while(res.next()) {
			computers.add(new Computer(res.getInt("id"), res.getString("name"), res.getDate("introduced"), res.getDate("discontinued"), res.getInt("company_id")));
		}
		return computers;
	}
	
	public static Computer parseResultSetComputer(ResultSet res) throws SQLException {
		if(res.next()) {
			return new Computer(res.getInt("id"), res.getString("name"), res.getDate("introduced"), res.getDate("discontinued"), res.getInt("company_id"));
		}else {
		return null;
		}
	}
	
	public static ArrayList<Company> parseAllResultSetCompany(ResultSet res) throws SQLException {
		ArrayList<Company> computers = new ArrayList<>(); 
		while(res.next()) {
			computers.add(new Company(res.getInt("id"), res.getString("name")));
		}
		return computers;
	}
	
	public static Company parseResultSetCompany(ResultSet res) throws SQLException {
		if(res.next()) {
			return new Company(res.getInt("id"), res.getString("name"));
		}else {
		return null;
		}
	}
	
}
