package com.excilys.command;

import java.sql.Date;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.ArrayList;

import com.excilys.model.Computer;
import com.excilys.service.IDAO.IDAOComputer;
import com.excilys.service.JDBC.JDBCComputer;

public class ShowComputer implements Command{
	private Computer computer;
	private String idString;
	
	ShowComputer(String idString){
		this.idString = idString;
	}
	
	private int parseId(String idToParse) throws ParseException {
		return Integer.parseInt(idToParse);
	}
	
	@Override
	public Result exucute() {
		IDAOComputer comp = new JDBCComputer();
		Result res = comp.select(computer);
		return res;
	}

	@Override
	public void handleArgument() {
		int id;
		try {
			id = parseId(idString);
			computer = new Computer(id);
		}catch(ParseException  e) {
			e.printStackTrace();
		}
	}

}
