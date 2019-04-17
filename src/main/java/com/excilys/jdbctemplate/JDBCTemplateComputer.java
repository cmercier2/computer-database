package com.excilys.jdbctemplate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import com.excilys.enums.OrderBy;
import com.excilys.mapper.ComputerMapper;
import com.excilys.model.Computer;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class JDBCTemplateComputer {
	private final String INSERT = "INSERT INTO computer (name,introduced,discontinued,company_id) VALUES (?, ?, ?, ?);";
	private final String UPDATE = "UPDATE computer SET name = ?, introduced = ?, discontinued = ?, company_id = ? WHERE id = ?;";
	private final String DELETE = "DELETE FROM computer WHERE id = ?;";
	private final String DELETECOMPUTERS = "DELETE FROM computer WHERE company_id = ?;";
	private final String SELECT = "SELECT id, name, introduced, discontinued, company_id FROM computer WHERE id = ?;";
	private final String SELECTSEARCHORDERNOT = "SELECT id, name, introduced, discontinued, company_id FROM computer WHERE name LIKE ? LIMIT ? OFFSET ?;";
	private final String SELECTSEARCHNAME = "SELECT id, name, introduced, discontinued, company_id FROM computer WHERE name LIKE ? ORDER BY name LIMIT ? OFFSET ?;";
	private final String SELECTSEARCHINTRODUCED = "SELECT id, name, introduced, discontinued, company_id FROM computer WHERE name LIKE ? ORDER BY introduced LIMIT ? OFFSET ?;";
	private final String SELECTSEARCHDISCONTINUED = "SELECT id, name, introduced, discontinued, company_id FROM computer WHERE name LIKE ? ORDER BY discontinued LIMIT ? OFFSET ?;";
	private final String COUNT = "SELECT COUNT(*) FROM computer WHERE name like ?;";
	private JdbcTemplate jdbctemplate;
	
	public JDBCTemplateComputer(JdbcTemplate jdbc) {
		this.jdbctemplate = jdbc;
	}
	
	/**
	 * 
	 * @param computer
	 * @return
	 * @throws SQLException
	 */
	public int create(Computer computer) throws SQLException {
		return jdbctemplate.update(INSERT, new Object[] { computer.getName(), computer.getIntroduced(),
				computer.getDiscontinued(), computer.getCompany() });
	}

	/**
	 * 
	 * @param computer
	 * @return
	 * @throws SQLException
	 */
	public int update(Computer computer) throws SQLException {
		return jdbctemplate.update(UPDATE, computer.getName(), computer.getIntroduced(),
				computer.getDiscontinued(), computer.getCompany(), computer.getId());
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	@Transactional
	public int deleteComputerByCompanyId(int idCompany) throws SQLException {
		return jdbctemplate.update(DELETECOMPUTERS, idCompany);
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	@Transactional
	public int delete(int id) throws SQLException {
		return jdbctemplate.update(DELETE, id);
	}

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Optional<Computer> select(int id) throws SQLException {
		return Optional.ofNullable(jdbctemplate.queryForObject(SELECT, new Object[] {id}, new ComputerMapper()));
	}

	/**
	 * 
	 * @param start
	 * @param step
	 * @param search
	 * @param order
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<Computer> selectAllSearchOrder(int start, int step, String search, OrderBy order)
			throws SQLException {
		switch (order) {
		case NAME:
			return new ArrayList<>(jdbctemplate.query(SELECTSEARCHNAME,
					new Object[] { "%" + search + "%", step, start }, new ComputerMapper()));
		case INTRODUCED:
			return new ArrayList<>(jdbctemplate.query(SELECTSEARCHINTRODUCED,
					new Object[] { "%" + search + "%", step, start }, new ComputerMapper()));
		case DISCONTINUED:
			return new ArrayList<>(jdbctemplate.query(SELECTSEARCHDISCONTINUED,
					new Object[] { "%" + search + "%", step, start }, new ComputerMapper()));
		default:
			return new ArrayList<>(jdbctemplate.query(SELECTSEARCHORDERNOT,
					new Object[] { "%" + search + "%", step, start }, new ComputerMapper()));
		}
	}

	/**
	 * 
	 * @param str
	 * @return
	 * @throws SQLException
	 */
	public int count(String str) throws SQLException {
		return jdbctemplate.queryForObject(COUNT, Integer.class, "%" + str + "%");
	}
}
