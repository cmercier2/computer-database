package com.excilys.pagination;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excilys.dto.ComputerDTO;
import com.excilys.enums.OrderBy;
import com.excilys.hibernate.HibernateComputer;
import com.excilys.utils.ComputerMapper;

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
	public List<ComputerDTO> next() throws SQLException {
		List<ComputerDTO> toPrint = new ArrayList<>();
		if (!end)
			startStep += step;
		toPrint = hbnt.selectAllSearchOrder(startStep, step, search, ord).stream().map(s -> ComputerMapper.mapComputer(s))
				.collect(Collectors.toList());
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
	public List<ComputerDTO> current() throws SQLException {
		List<ComputerDTO> toPrint = new ArrayList<>();
		toPrint = hbnt.selectAllSearchOrder(startStep, step, search, ord).stream().map(s -> ComputerMapper.mapComputer(s))
				.collect(Collectors.toList());
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
	public List<ComputerDTO> previous() throws SQLException {
		end = false;
		List<ComputerDTO> toPrint = new ArrayList<>();
		if (startStep != 0)
			startStep -= step;
		toPrint = hbnt.selectAllSearchOrder(startStep, step, search, ord).stream().map(s -> ComputerMapper.mapComputer(s))
				.collect(Collectors.toList());
		return toPrint;
	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<ComputerDTO> init(String str, OrderBy order) throws SQLException {
		ord = order;
		search = str;
		List<ComputerDTO> toPrint = new ArrayList<>();
		startStep = 0;
		toPrint = hbnt.selectAllSearchOrder(startStep, step, search, ord).stream().map(s -> ComputerMapper.mapComputer(s))
				.collect(Collectors.toList());
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
