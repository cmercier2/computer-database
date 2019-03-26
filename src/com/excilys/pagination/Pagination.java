package com.excilys.pagination;

import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.model.Computer;
import com.excilys.service.JDBC.JDBCComputer;

public class Pagination {
	private int startStep = 0;
	private int step = 10;
	private boolean end = false;

	/**
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ArrayList<Computer> next() throws SQLException, ClassNotFoundException {
		JDBCComputer jdb = new JDBCComputer();
		if (!end)
			startStep += step;
		ArrayList<Computer> toPrint = jdb.selectAll(startStep, step);
		if (toPrint.size() < step)
			end = true;
		return toPrint;
	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ArrayList<Computer> previous() throws SQLException, ClassNotFoundException {
		JDBCComputer jdb = new JDBCComputer();
		end = false;
		ArrayList<Computer> toPrint = new ArrayList<>();
		if (startStep != 0)
			startStep -= step;
		toPrint = jdb.selectAll(startStep, step);
		return toPrint;
	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ArrayList<Computer> init() throws SQLException, ClassNotFoundException {
		JDBCComputer jdb = new JDBCComputer();
		ArrayList<Computer> toPrint = new ArrayList<>();
		startStep = 0;
		toPrint = jdb.selectAll(startStep, step);
		return toPrint;
	}

}
