package com.excilys.command;

import java.util.ArrayList;

import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.service.IDAO.IDAOCompany;
import com.excilys.service.IDAO.IDAOComputer;
import com.excilys.service.JDBC.JDBCCompany;
import com.excilys.service.JDBC.JDBCComputer;
import com.excilys.ui.CommandLineInterface;

public class ListCompany implements Command{
	private Company company;

	@Override
	public Result exucute(CommandLineInterface cli) {
		IDAOCompany comp = new JDBCCompany();
		ArrayList<Company> cs = comp.selectAll();
		cli.showCompany(cs);
		Result res = new Result(1, cs.toString());
		return res;
	}

	@Override
	public Result handleArgument() {
		return new Result(1, "");
	}

}
