package com.excilys.command;

import java.text.ParseException;

import com.excilys.model.Computer;
import com.excilys.service.IDAO.IDAOComputer;
import com.excilys.service.JDBC.JDBCComputer;
import com.excilys.ui.CommandLineInterface;

public class ShowComputer implements Command{
	private Computer computer;
	private String commande;
	
	ShowComputer(String commande){
		this.commande = commande;
	}
	
	
	@Override
	public Result exucute(CommandLineInterface cli) {
		IDAOComputer comp = new JDBCComputer();
		Computer c = comp.select(computer);
		if(c != null) {
			cli.show(c);
			return new Result(1, c.toString());
		}
		return new Result(0, "Ordi inconnu");
	}


	private int parseId(String idToParse) throws ParseException {
		return Integer.parseInt(idToParse);
	}
	
	@Override
	public Result handleArgument() {
		int id;
		String[] l = commande.split(" ");
		if(l.length == 2) {
			try {
				id = parseId(l[1]);
				computer = new Computer(id);
			}catch(ParseException  e) {
				e.printStackTrace();
				return new Result(0, e.getMessage());
			}
		}
		return new Result(1, "");
	}

}
