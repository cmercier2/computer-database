package com.excilys.command;

import java.util.ArrayList;

import com.excilys.model.Computer;
import com.excilys.service.IDAO.IDAOComputer;
import com.excilys.service.JDBC.JDBCComputer;
import com.excilys.ui.CommandLineInterface;

public class ListComputer implements Command{
	ArrayList<Computer> computers;

	@Override
	public Result exucute(CommandLineInterface cli) {
		IDAOComputer comp = new JDBCComputer();
		ArrayList<Computer> cs = comp.selectAll();
		cli.showComputers(cs);
		Result res = new Result(1, cs.toString());
		return res;
	}

	@Override
	public Result handleArgument() {
		return new Result(1, "");
	}

}
