package com.excilys.service.IDAO;

import java.util.ArrayList;

import com.excilys.model.Computer;

public interface IDAOComputer {
	public void create(Computer computer);
	public void update(Computer computer);
	public void delete(Computer computer);
	public Computer select(Computer computer);
	public ArrayList<Computer> selectAll(); 
}
