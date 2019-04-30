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
	 * @throws ClassNotFoundException
	 */
	public List<Computer> init(String search, Optional<String> ord) throws SQLException {
		return page.init(search, OrderBy.valueOf(ord.orElse("ID")));
		/*return new ArrayList<ComputerDTO>(page.init(search, OrderBy.valueOf(ord.orElse("ID"))).stream()
				.map(x -> new ComputerDTOBuilder().setId(x.getId()).setName(x.getName())
						.setIntroduced(x.getIntroduced() == null ? "" : x.getIntroduced().toString())
						.setDiscontinued(x.getDiscontinued() == null ? "" : x.getDiscontinued().toString())
						.setCompany(x.getCompany()).build())
				.collect(Collectors.toList()));*/
	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<Computer> current() throws SQLException {
		return page.current();
		/*return new ArrayList<ComputerDTO>(page.current().stream()
				.map(x -> new ComputerDTOBuilder().setId(x.getId()).setName(x.getName())
						.setIntroduced(x.getIntroduced() == null ? "" : x.getIntroduced().toString())
						.setDiscontinued(x.getDiscontinued() == null ? "" : x.getDiscontinued().toString())
						.setCompany(x.getCompany()).build())
				.collect(Collectors.toList()));*/
	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<Computer> next() throws SQLException {
		return page.next();
		/*return new ArrayList<ComputerDTO>(page.next().stream()
				.map(x -> new ComputerDTOBuilder().setId(x.getId()).setName(x.getName())
						.setIntroduced(x.getIntroduced() == null ? "" : x.getIntroduced().toString())
						.setDiscontinued(x.getDiscontinued() == null ? "" : x.getDiscontinued().toString())
						.setCompany(x.getCompany()).build())
				.collect(Collectors.toList()));*/
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
	 * @throws ClassNotFoundException
	 */
	public List<Computer> previous() throws SQLException {
		return page.previous();
		/*return new ArrayList<ComputerDTO>(page.previous().stream()
				.map(x -> new ComputerDTOBuilder().setId(x.getId()).setName(x.getName())
						.setIntroduced(x.getIntroduced() == null ? "" : x.getIntroduced().toString())
						.setDiscontinued(x.getDiscontinued() == null ? "" : x.getDiscontinued().toString())
						.setCompany(x.getCompany()).build())
				.collect(Collectors.toList()));*/
	}
}
