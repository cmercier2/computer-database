package com.excilys.computerdatabase;


import java.util.ArrayList;

import com.excilys.command.Command;
import com.excilys.command.Result;
import com.excilys.command.Switch;
import com.excilys.ui.CommandLineInterface;

public class ComputerDatabase {
	public static void main(String args[]) throws ClassNotFoundException {
		CommandLineInterface cli = new CommandLineInterface();
		String line;
		Command comm;
		Result res;
		line = cli.getUserINput();
		comm = Switch.switcher(line);
		comm.handleArgument();
		res = comm.exucute(cli);
		if(res.getStatut() == 0) {
			System.out.println(res.getMessage());
		}
	}
}
