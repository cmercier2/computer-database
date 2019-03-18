package com.excilys.controller;

import java.util.Arrays;
import com.excilys.argumenthandler.ArgumentHandler;
import com.excilys.driver.SQLDriver;
import com.excilys.model.Computer;
import com.excilys.service.IDAO.IDAOCompany;
import com.excilys.service.IDAO.IDAOComputer;
import com.excilys.service.JDBC.JDBCCompany;
import com.excilys.service.JDBC.JDBCComputer;
import com.excilys.ui.CommandLineInterface;
import com.excilys.utils.Result;

public class Controller {
	private SQLDriver driver;
	private CommandLineInterface cli;

	/**
	 * 
	 * @param driver
	 * @param cli
	 */
	public Controller(SQLDriver driver, CommandLineInterface cli) {
		this.driver = driver;
		this.cli = cli;
	}

	/**
	 * 
	 * @param commande
	 * @return
	 */
	private Result showComputer(String commande) {
		IDAOComputer comp = new JDBCComputer(driver);
		Computer computer = new Computer(ArgumentHandler.showArgument(commande));
		cli.show(comp.select(computer));
		return new Result(1, "");
	}

	/**
	 * 
	 * @return
	 */
	private Result listComputers() {
		IDAOComputer comp = new JDBCComputer(driver);
		//Pagination page = new Pagination();
		cli.showComputers(comp.selectAll());
		return new Result(1, "");
	}

	/**
	 * 
	 * @param commande
	 * @return
	 */
	private Result createComputerComputer(String commande) {
		IDAOComputer comp = new JDBCComputer(driver);
		Computer computer = ArgumentHandler.creationArgument(commande);
		comp.create(computer);
		return new Result(1, "");
	}

	/**
	 * 
	 * @param commande
	 * @return
	 */
	private Result updateComputerComputer(String commande) {
		IDAOComputer comp = new JDBCComputer(driver);
		Computer computer = new Computer(1);
		if (computer != null) {
			comp.update(computer);
		}
		return new Result(1, "");
	}

	/**
	 * 
	 * @param commande
	 * @return
	 */
	private Result deleteComputerComputer(String commande) {
		IDAOComputer comp = new JDBCComputer(driver);
		Computer computer = new Computer(ArgumentHandler.deleteArgument(commande));
		comp.delete(computer);
		return new Result(1, "");
	}

	/**
	 * 
	 * @return
	 */
	public Result listCompanys() {
		IDAOCompany comp = new JDBCCompany(driver);
		cli.showCompany(comp.selectAll());
		return new Result(1, "");
	}

	/**
	 * 
	 */
	public void switcher() {
		String commande;
		while (cli.hasNext()) {
			commande = cli.next();
			String com = Arrays.asList(commande.split(" ")).get(0);
			switch (com) {
			case "showcomputer":
				showComputer(commande);
				break;
			case "createcomputer":
				createComputerComputer(commande);
				break;
			case "updatecomputer":
				updateComputerComputer(commande);
				break;
			case "deletecomputer":
				deleteComputerComputer(commande);
				break;
			case "listcompany":
				listCompanys();
				break;
			case "listcomputer":
				listComputers();
				break;
			default:
				System.out.println("Invalid command");
			}
		}
	}
		
	
}
