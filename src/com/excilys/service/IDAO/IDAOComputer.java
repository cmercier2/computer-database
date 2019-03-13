package com.excilys.service.IDAO;

import java.sql.ResultSet;

import com.excilys.command.Result;
import com.excilys.model.Computer;

public interface IDAOComputer {
	public void create(Computer computer);
	public void update(Computer computer);
	public void delete(Computer computer);
	public Result select(Computer computer);
	public Result selectAll(); 
}
