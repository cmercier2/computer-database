package com.excilys.service.IDAO;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.excilys.command.Result;
import com.excilys.model.Computer;

public interface IDAOComputer {
	public void create(Computer computer);
	public void update(Computer computer);
	public void delete(int computer);
	public Computer select(Computer computer);
	public ArrayList<Computer> selectAll(); 
}
