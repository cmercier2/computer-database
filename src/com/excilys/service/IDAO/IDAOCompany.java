package com.excilys.service.IDAO;

import com.excilys.model.Company;

public interface IDAOCompany {
	public void create(Company company);
	public void update(Company company);
	public void delete(Company company);
	public void select(Company company);

}
