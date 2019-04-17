package com.excilys.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.excilys.model.Computer;
import com.excilys.model.Computer.ComputerBuilder;

public class ComputerMapper implements RowMapper<Computer>{

	@Override
	public Computer mapRow(ResultSet res, int rowNum) throws SQLException {
		 return new ComputerBuilder().setId(res.getInt("id")).setName(res.getString("name"))
				.setIntroduced(res.getDate("introduced")).setDiscontinued(res.getDate("discontinued"))
				.setCompany(res.getInt("company_id")).build();
	}
}
