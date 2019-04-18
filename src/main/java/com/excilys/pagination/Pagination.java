package com.excilys.pagination;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.enums.OrderBy;
import com.excilys.jdbctemplate.JDBCTemplateComputer;
import com.excilys.model.Computer;

@Service
public class Pagination {
	private int startStep = 0;
	private int step = 10;
	private boolean end = false;
	private String search;
	private int sizeList = -1;
	private OrderBy ord = OrderBy.ID;
	@Autowired
	private JDBCTemplateComputer jdb;

	/**
	 * 
	 * @return @throws SQLException @throws ClassNotFoundException @throws
	 */
	public ArrayList<Computer> next() throws SQLException {
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
		return jdb.count(search);
	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ArrayList<Computer> current() throws SQLException {
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
		ord = order;
		search = str;
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
