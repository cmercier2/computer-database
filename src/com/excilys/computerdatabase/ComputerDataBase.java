package com.excilys.computerdatabase;

import com.excilys.controller.Controller;
import com.excilys.driver.SQLDriver;
import com.excilys.ui.CommandLineInterface;

public class ComputerDataBase {
	/**
	 * 
	 * @param args
	 * @throws ClassNotFoundException
	 */
	public static void main(String args[]) throws ClassNotFoundException {
		CommandLineInterface cli = new CommandLineInterface();
		Controller controle;
		controle = new Controller(cli);
		controle.switcher();
	}
	

	
}
