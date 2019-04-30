package com.excilys.pagination;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.enums.OrderBy;
import com.excilys.hibernate.HibernateComputer;
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
	private HibernateComputer hbnt;

	/**
	 * 
	 * @return @throws SQLException @throws ClassNotFoundException @throws
	 */
	public List<Computer> next() throws SQLException {
		List<Computer> toPrint = new ArrayList<>();
		if (!end)
			startStep += step;
		toPrint = hbnt.selectAllSearchOrder(startStep, step, search, ord);
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
		return hbnt.count(search);
	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<Computer> current() throws SQLException {
		List<Computer> toPrint = new ArrayList<>();
		toPrint = hbnt.selectAllSearchOrder(startStep, step, search, ord);
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
	public List<Computer> previous() throws SQLException {
		end = false;
		List<Computer> toPrint = new ArrayList<>();
		if (startStep != 0)
			startStep -= step;
		toPrint = hbnt.selectAllSearchOrder(startStep, step, search, ord);
		return toPrint;
	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<Computer> init(String str, OrderBy order) throws SQLException {
		ord = order;
		search = str;
		List<Computer> toPrint = new ArrayList<>();
		startStep = 0;
		toPrint = hbnt.selectAllSearchOrder(startStep, step, search, ord);
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
