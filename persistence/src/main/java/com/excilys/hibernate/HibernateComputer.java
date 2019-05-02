package com.excilys.hibernate;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.enums.OrderBy;
import com.excilys.model.Computer;

@Repository
@Transactional
public class HibernateComputer {
	@Autowired
	private LocalSessionFactoryBean session;
	private SessionFactory sessionFactory;

	@PostConstruct
	public void init() {
		sessionFactory = session.getObject();
		sessionFactory.openSession();
	}

	private Session getSession() {
		try {
			return sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			return sessionFactory.openSession();
		}
	}

	private void order(OrderBy order, CriteriaQuery<Computer> criteriaQuery, CriteriaBuilder criteriaBuilder,
			Root<Computer> root) {
		switch (order) {
		case DISCONTINUED:
			criteriaQuery.orderBy(criteriaBuilder.asc(root.get("discontinued")));
		case NAME:
			criteriaQuery.orderBy(criteriaBuilder.asc(root.get("name")));
		case INTRODUCED:
			criteriaQuery.orderBy(criteriaBuilder.asc(root.get("introduced")));
		default:
			return;
		}
	}

	public List<Computer> selectAllSearchOrder(int start, int step, String search, OrderBy order) {
		try (Session session = getSession()) {
			CriteriaBuilder criteriaBuilder = sessionFactory.getCriteriaBuilder();
			CriteriaQuery<Computer> criteriaQuery = criteriaBuilder.createQuery(Computer.class);
			Root<Computer> root = criteriaQuery.from(Computer.class);
			criteriaQuery.select(root);
			order(order, criteriaQuery, criteriaBuilder, root);
			criteriaQuery.where(criteriaBuilder.like(root.get("name"), "%" + search + "%"));
			TypedQuery<Computer> q = session.createQuery(criteriaQuery).setFirstResult(start).setMaxResults(step);
			return q.getResultList();
		}
	}

	public void create(Computer computer) {
		try (Session session = getSession()) {
			Transaction transaction = session.beginTransaction();
			session.save(computer);
			transaction.commit();
		}
	}

	public int count(String str) throws SQLException {
		try (Session session = getSession()) {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
			Root<Computer> root = criteriaQuery.from(Computer.class);
			criteriaQuery.select(criteriaBuilder.count(root));
			criteriaQuery.where(criteriaBuilder.like(root.get("name"), "%" + str + "%"));
			return Math.toIntExact(session.createQuery(criteriaQuery).getSingleResult());
		}
	}

	public void delete(int id) {
		try (Session session = getSession()) {
			Transaction transaction = session.beginTransaction();
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaDelete<Computer> criteriaQuery = criteriaBuilder.createCriteriaDelete(Computer.class);
			Root<Computer> root = criteriaQuery.from(Computer.class);
			criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));
			session.createQuery(criteriaQuery).executeUpdate();
			transaction.commit();
		}
	}

	public Optional<Computer> select(int id) {
		try (Session session = getSession()) {
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaQuery<Computer> criteriaQuery = criteriaBuilder.createQuery(Computer.class);
			Root<Computer> root = criteriaQuery.from(Computer.class);
			criteriaQuery.select(root);
			criteriaQuery.where(criteriaBuilder.equal(root.get("id"), id));
			TypedQuery<Computer> q = session.createQuery(criteriaQuery);
			return Optional.of(q.getSingleResult());
		}
	}

	public void update(Computer computer) {
		System.out.println("computer edit : " + computer.toString());
		try (Session session = getSession()) {
			Transaction transaction = session.beginTransaction();
			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			CriteriaUpdate<Computer> update = criteriaBuilder.createCriteriaUpdate(Computer.class);
			Root<Computer> root = update.from(Computer.class);
			update.set(root.get("name"), computer.getName()).set(root.get("introduced"), computer.getIntroduced())
					.set(root.get("discontinued"), computer.getDiscontinued())
					.set(root.get("company"), computer.getCompany().getId());
			update.where(criteriaBuilder.equal(root.get("id"), computer.getId()));
			session.createQuery(update).executeUpdate();
			transaction.commit();
		}
	}
}
