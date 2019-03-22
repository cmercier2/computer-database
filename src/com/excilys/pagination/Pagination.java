package com.excilys.pagination;

import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.model.Computer;
import com.excilys.service.JDBC.JDBCComputer;

public class Pagination {
	// private ArrayList<T> toPrint = new ArrayList<>();
	private int startStep = 0;
	private int endStep;
	private int step = 10;
	private boolean init = true;

	public ArrayList<Computer> next() throws SQLException {
		JDBCComputer jdb = new JDBCComputer();
		startStep += step;
		ArrayList<Computer> toPrint = jdb.selectAll(startStep, step);
		return toPrint;
	}

	public ArrayList<Computer> previous() throws SQLException {
		JDBCComputer jdb = new JDBCComputer();
		ArrayList<Computer> toPrint = new ArrayList<>();
		if (startStep != 0) {
			startStep -= step;
			toPrint = jdb.selectAll(startStep, step);
		}
		return toPrint;
	}
	
	public ArrayList<Computer> init() throws SQLException{
		JDBCComputer jdb = new JDBCComputer();
		ArrayList<Computer> toPrint = new ArrayList<>();
		startStep = 0;
		toPrint = jdb.selectAll(startStep, step);
		return toPrint;
	}

}
