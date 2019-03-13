package com.excilys.command;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;

import com.excilys.model.Computer;
import com.excilys.ui.CommandLineInterface;

public class UpdateComputer implements Command{
	private Computer computer;
	
	@Override
	public Result exucute(CommandLineInterface cli) {
		return null;
	}
	@Override
	public Result handleArgument() {int idCompany;
	/*Date introduced, discontinued;
	try {
	}catch(ParseException  e) {
		return new Result(0, e.getMessage());
	}*/
	return new Result(1, "");
}

}
