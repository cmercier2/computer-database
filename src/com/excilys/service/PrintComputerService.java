package com.excilys.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

import com.excilys.DTO.ComputerDTO;
import com.excilys.DTO.ComputerDTO.ComputerDTOBuilder;
import com.excilys.pagination.Pagination;

public class PrintComputerService {
	private Pagination page = new Pagination();

	/**
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ArrayList<ComputerDTO> init() throws ClassNotFoundException, SQLException {
		return new ArrayList<ComputerDTO>(page.init().stream()
				.map(x -> new ComputerDTOBuilder().setId(x.getId()).setName(x.getName())
						.setIntroduced(x.getIntroduced() == null ? "" : x.getIntroduced().toString())
						.setDiscontinuede(x.getDiscontinued() == null ? "" : x.getDiscontinued().toString())
						.setCompany(x.getCompany()).build())
				.collect(Collectors.toList()));
	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ArrayList<ComputerDTO> next() throws ClassNotFoundException, SQLException {
		return new ArrayList<ComputerDTO>(page.next().stream()
				.map(x -> new ComputerDTOBuilder().setId(x.getId()).setName(x.getName())
						.setIntroduced(x.getIntroduced() == null ? "" : x.getIntroduced().toString())
						.setDiscontinuede(x.getDiscontinued() == null ? "" : x.getDiscontinued().toString())
						.setCompany(x.getCompany()).build())
				.collect(Collectors.toList()));
	}

	/**
	 * 
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public ArrayList<ComputerDTO> previous() throws ClassNotFoundException, SQLException {
		return new ArrayList<ComputerDTO>(page.previous().stream()
				.map(x -> new ComputerDTOBuilder().setId(x.getId()).setName(x.getName())
						.setIntroduced(x.getIntroduced() == null ? "" : x.getIntroduced().toString())
						.setDiscontinuede(x.getDiscontinued() == null ? "" : x.getDiscontinued().toString())
						.setCompany(x.getCompany()).build())
				.collect(Collectors.toList()));
	}

}
