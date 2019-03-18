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
		SQLDriver driver = null;
		try {
			driver = SQLDriver.start();
			controle = new Controller(driver, cli);
			controle.switcher();
		} finally {
			driver.close();
		}
	}
}
