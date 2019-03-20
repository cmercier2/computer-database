package com.excilys.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import com.excilys.model.Company;
import com.excilys.model.Computer;

public class MapResultSet {
	/**
	 * 
	 * @param res
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<Computer> mapAllResultSetComputer(ResultSet res) throws SQLException {
		ArrayList<Computer> computers = new ArrayList<>();
		while (res.next()) {
			computers.add(new Computer(res.getInt("id"), res.getString("name"), res.getDate("introduced"),
					res.getDate("discontinued"), res.getInt("company_id")));
		}
		return computers;
	}

	/**
	 * 
	 * @param res
	 * @return
	 * @throws SQLException
	 */
	public static Optional<Computer> mapResultSetComputer(ResultSet res) throws SQLException {
		if (res.next()) {
			return Optional.of(new Computer(res.getInt("id"), res.getString("name"), res.getDate("introduced"),
					res.getDate("discontinued"), res.getInt("company_id")));
		} else {
			return Optional.ofNullable(null);
		}
	}

	/**
	 * 
	 * @param res
	 * @return
	 * @throws SQLException
	 */
	public static ArrayList<Company> mapAllResultSetCompany(ResultSet res) throws SQLException {
		ArrayList<Company> computers = new ArrayList<>();
		while (res.next()) {
			computers.add(new Company(res.getInt("id"), res.getString("name")));
		}
		return computers;
	}

	/**
	 * 
	 * @param res
	 * @return
	 * @throws SQLException
	 */
	public static Optional<Company> mapResultSetCompany(ResultSet res) throws SQLException {
		if (res.next()) {
			return Optional.of(new Company(res.getInt("id"), res.getString("name")));
		} 
			return Optional.ofNullable(null);
	}

}
