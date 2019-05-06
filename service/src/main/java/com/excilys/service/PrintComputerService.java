package com.excilys.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.enums.OrderBy;
import com.excilys.model.Computer;
import com.excilys.pagination.Pagination;

@Service
public class PrintComputerService {
	@Autowired
	private Pagination page;

	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Computer> init(String search, Optional<String> ord) throws SQLException {
		return page.init(search, OrderBy.valueOf(ord.orElse("ID")));
	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Computer> current() throws SQLException {
		return page.current();
	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Computer> next() throws SQLException {
		return page.next();
	}

	/**
	 * 
	 * @return
	 */
	public int getsize() {
		return page.getTotalComputer();
	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 */
	public List<Computer> previous() throws SQLException {
		return page.previous();
	}
}
