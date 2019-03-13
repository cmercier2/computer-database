package com.excilys.computerdatabase;


import java.util.ArrayList;

import com.excilys.command.Command;
import com.excilys.command.Switch;
import com.excilys.ui.CommandLineInterface;

public class ComputerDatabase {
	public static void main(String args[]) throws ClassNotFoundException {
		CommandLineInterface cli = new CommandLineInterface();
		ArrayList<String> line;
		Command comm;
		line = cli.getUserINput();
		comm = Switch.switcher(line);
		comm.handleArgument();
		comm.exucute();
	}
}
