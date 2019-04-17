package com.excilys.jdbctemplate;

import java.sql.SQLException;
import java.util.ArrayList;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.mapper.CompanyMapper;
import com.excilys.model.Company;

@Repository
public class JDBCTemplateCompany {
	private final String SELECTALL = "SELECT id, name FROM company;";
	private final String DELETE = "DELETE FROM company WHERE id = ?;";
	private JdbcTemplate jdbctemplate;
	
	public JDBCTemplateCompany(JdbcTemplate jdbc) {
		this.jdbctemplate = jdbc;
	}

	public ArrayList<Company> selectAll() throws SQLException {
		return new ArrayList<>(jdbctemplate.query(SELECTALL, new CompanyMapper()));
	}

	@Transactional
	public int delete(int id) throws SQLException {
		return jdbctemplate.update(DELETE, id);
	}
}
