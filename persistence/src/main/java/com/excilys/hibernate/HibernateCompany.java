package com.excilys.hibernate;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.model.Company;

@Repository
@Transactional
public class HibernateCompany {
	@Autowired
	private LocalSessionFactoryBean session;
	private SessionFactory sessionFactory;
	private EntityManager manager;

	@PostConstruct
	public void init() {
		sessionFactory = session.getObject();
		manager = session.getObject().createEntityManager();
	}
	
	public List<Company> selectAll() {
		CriteriaBuilder criteriaBuilder = sessionFactory.getCriteriaBuilder();
		CriteriaQuery<Company> criteriaQuery = criteriaBuilder.createQuery(Company.class);
		Root<Company> root = criteriaQuery.from(Company.class);
		criteriaQuery.select(root);
		TypedQuery<Company> q = manager.createQuery(criteriaQuery);
		return q.getResultList();
	}
	
	@Transactional
	public Company getCompanyById(int company) {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		CriteriaQuery<Company> criteriaQuery = criteriaBuilder.createQuery(Company.class);
		Root<Company> root = criteriaQuery.from(Company.class);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get("id"), company));
		TypedQuery<Company> q = manager.createQuery(criteriaQuery);
		return q.getSingleResult();
	}
	
	
	
}
