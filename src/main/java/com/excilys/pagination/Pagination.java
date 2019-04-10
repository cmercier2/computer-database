package com.excilys.pagination;

import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.JDBC.JDBCComputer;
import com.excilys.enums.OrderBy;
import com.excilys.model.Computer;

public class Pagination {
	private int startStep = 0;
	private int step = 10;
	private boolean end = false;
	private String search;
	private int sizeList = -1;
	private OrderBy ord = OrderBy.ID;

	/**
	 * 
	 * @return @throws SQLException @throws ClassNotFoundException @throws
	 */
	public ArrayList<Computer> next() throws SQLException {
		JDBCComputer jdb = new JDBCComputer();
		ArrayList<Computer> toPrint = new ArrayList<>();
		if (!end)
			startStep += step;
		toPrint = jdb.selectAllSearchOrder(startStep, step, search, ord);
		if (toPrint.size() < step)
			end = true;
		return toPrint;
	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	private int totalSize() throws SQLException {
		JDBCComputer jdb = new JDBCComputer();
		return jdb.count(search);
	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ArrayList<Computer> current() throws SQLException {
		JDBCComputer jdb = new JDBCComputer();
		ArrayList<Computer> toPrint = new ArrayList<>();
		toPrint = jdb.selectAllSearchOrder(startStep, step, search, ord);
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
	public ArrayList<Computer> previous() throws SQLException {
		JDBCComputer jdb = new JDBCComputer();
		end = false;
		ArrayList<Computer> toPrint = new ArrayList<>();
		if (startStep != 0)
			startStep -= step;
		toPrint = jdb.selectAllSearchOrder(startStep, step, search, ord);
		return toPrint;
	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ArrayList<Computer> init(String str, OrderBy order) throws SQLException {
		JDBCComputer jdb = new JDBCComputer();
		ord = order;
		search = str;
		System.out.println(ord.get());
		ArrayList<Computer> toPrint = new ArrayList<>();
		startStep = 0;
		toPrint = jdb.selectAllSearchOrder(startStep, step, search, ord);
		sizeList = totalSize();
		return toPrint;
	}

	/**
	 * 
	 * @return
	 */
	public int getTotalComputer() {
		return sizeList;
	}

}
