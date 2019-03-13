package com.excilys.command;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;

import com.excilys.model.Computer;
import com.excilys.service.IDAO.IDAOComputer;
import com.excilys.service.JDBC.JDBCComputer;
import com.excilys.ui.CommandLineInterface;

public class DeleteComputer implements Command{
	private int id;
	private String commande;
	
	public DeleteComputer(String commande) {
		this.commande = commande;
	}
	
	
	@Override
	public Result exucute(CommandLineInterface cli) {
		IDAOComputer comp = new JDBCComputer();
		comp.delete(id);
		return new Result(1, "en cours");
	}

	
	private int parseId(String idToParse) throws ParseException {
		return Integer.parseInt(idToParse);
	}
	
	@Override
	public Result handleArgument() {
		String[] l = commande.split(" ");
		if(l.length == 2) {
			try {
				id = parseId(l[1]);
			}catch(ParseException  e) {
				e.printStackTrace();
				return new Result(0, e.getMessage());
			}
		}
		return new Result(1, "");
	}

}
