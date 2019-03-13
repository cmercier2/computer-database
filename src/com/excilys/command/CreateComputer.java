package com.excilys.command;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.excilys.model.Computer;
import com.excilys.service.IDAO.IDAOComputer;
import com.excilys.service.JDBC.JDBCComputer;

public class CreateComputer implements Command{
	private ArrayList<String> command;
	private Computer computer;
	
	public CreateComputer(ArrayList<String> command) {
		this.command = command;
	}

	@Override
	public Result exucute() {
		IDAOComputer comp = new JDBCComputer();
		comp.create(computer);
		return new Result(1, "test");
	}

	
	private int parseId(String idString) throws ParseException {
		return Integer.parseInt(idString);
	}
	
	private Date parseDate(String dateString) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date utilDate = format.parse(dateString);
		return new Date(utilDate.getTime());
	}
	
	
	@Override
	public void handleArgument() {
		int idCompany;
		Date introduced, discontinued;
		try {
			idCompany = parseId(command.get(4));
			introduced = parseDate(command.get(2));
			discontinued = parseDate(command.get(3));
			computer = new Computer(command.get(1), introduced, discontinued, idCompany);
		}catch(ParseException  e) {
			e.printStackTrace();
		}
	}
}
