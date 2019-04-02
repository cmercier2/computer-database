package com.excilys.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import com.excilys.exception.InvalidComputerName;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.model.Computer.ComputerBuilder;

public class MapResultSet {
	/**
	 * 
	 * @param res
	 * @return
	 * @throws SQLException
	 * @throws InvalidComputerName 
	 */
	public static ArrayList<Computer> mapAllResultSetComputer(ResultSet res) throws SQLException {
		ArrayList<Computer> computers = new ArrayList<>();
		while (res.next()) {
			computers.add(new ComputerBuilder().setId(res.getInt("id")).setName(res.getString("name"))
					.setIntroduced(res.getDate("introduced")).setDiscontinued(res.getDate("discontinued"))
					.setCompany(res.getInt("company_id")).build());
		}
		return computers;
	}

	/**
	 * 
	 * @param res
	 * @return
	 * @throws SQLException
	 * @throws InvalidComputerName 
	 */
	public static Optional<Computer> mapResultSetComputer(ResultSet res) throws SQLException {
		if (res.next()) {
			return Optional.of(new ComputerBuilder().setId(res.getInt("id")).setName(res.getString("name"))
					.setIntroduced(res.getDate("introduced")).setDiscontinued(res.getDate("discontinued"))
					.setCompany(res.getInt("company_id")).build());
		} else {
			return Optional.empty();
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
