package com.excilys.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;

import com.excilys.enums.OrderBy;
import com.excilys.model.Computer;

@Repository
public class HibernateComputer {

	
	public List<Computer> selectAllSearchOrder(int start, int step, String search, OrderBy order){
		 List<Computer> computers = new ArrayList<>();
	      
	      return computers;
 		/*query.select(root);
		switch (order) {
		case NAME:
			//Query q = session.
		//return query.;
		case INTRODUCED:
			
		case DISCONTINUED:
			
		default:
			return null;
		}*/
	}
	
	public void create(Computer computer) {
		
	}
	
	public int count(String str) throws SQLException {
		return 0;//return jdbctemplate.queryForObject(COUNT, Integer.class, "%" + str + "%");
	}

	public void delete(int id) {
		// TODO Auto-generated method stub
		
	}

	public Optional<Computer> select(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(Computer computer) {
		// TODO Auto-generated method stub
		
	}
	
	/*public ArrayList<Computer> selectAllSearchOrder(int start, int step, String search, OrderBy order)
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
	}*/
	
}
