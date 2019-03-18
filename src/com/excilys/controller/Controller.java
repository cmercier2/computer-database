package com.excilys.controller;

import java.util.Arrays;
import java.util.Objects;

import com.excilys.argumenthandler.ArgumentHandler;
import com.excilys.driver.SQLDriver;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.pagination.Pagination;
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
	 * Constructor of controller
	 * 
	 * @param driver
	 * @param cli
	 */
	public Controller(SQLDriver driver, CommandLineInterface cli) {
		Objects.requireNonNull(driver);
		Objects.requireNonNull(cli);
		this.driver = driver;
		this.cli = cli;
	}

	/**
	 * Get a command line with a computer id and print information about this
	 * computer
	 * 
	 * @param commande
	 * @return Result
	 */
	private Result showComputer(String commande) {
		Objects.requireNonNull(commande);
		IDAOComputer comp = new JDBCComputer(driver);
		Computer computer = new Computer(ArgumentHandler.showArgument(commande));
		cli.show(comp.select(computer));
		return new Result(1, "");
	}

	/**
	 * List all computers and start pagination
	 * 
	 * @return Result
	 */
	private Result listComputers() {
		IDAOComputer comp = new JDBCComputer(driver);
		Pagination<Computer> page = new Pagination<>(cli, comp.selectAll());
		page.pagine();
		return new Result(1, "");
	}

	/**
	 * Create a computer with informations enter by the user
	 * 
	 * @param commande
	 * @return Result
	 */
	private Result createComputerComputer(String commande) {
		IDAOComputer comp = new JDBCComputer(driver);
		Computer computer = ArgumentHandler.creationArgument(commande);
		comp.create(computer);
		return new Result(1, "");
	}

	/**
	 * Update a computer with informations enter by the user
	 * 
	 * @param commande
	 * @return Result
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
	 * Get a command line with a id and delete the corresponding computer
	 * 
	 * @param commande
	 * @return Result
	 */
	private Result deleteComputerComputer(String commande) {
		IDAOComputer comp = new JDBCComputer(driver);
		Computer computer = new Computer(ArgumentHandler.deleteArgument(commande));
		comp.delete(computer);
		return new Result(1, "");
	}

	/**
	 * List all companys and start pagination
	 * 
	 * @return Result
	 */
	public Result listCompanys() {
		IDAOCompany comp = new JDBCCompany(driver);
		Pagination<Company> page = new Pagination<>(cli, comp.selectAll());
		page.pagine();
		return new Result(1, "");
	}

	/**
	 * 
	 * Retrieve the first element of user input and call the corresponding function
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
