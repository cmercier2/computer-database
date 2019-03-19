package com.excilys.controller;

import java.util.Arrays;
import java.util.Objects;

import org.apache.log4j.Logger;

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
import com.excilys.utils.LoggerConfigurator;
import com.excilys.utils.Result;

public class Controller {
	private SQLDriver driver;
	private CommandLineInterface cli;
	private static final Logger log = LoggerConfigurator.configureLogger(Controller.class);

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
		Computer result = comp.select(computer);
		if (result != null) {
			log.debug("show computer" + computer.getId());
			cli.show(result);
			return new Result(1, "");
		} else {
			cli.ShowMessage("Unknown computer");
			return new Result(0, "");
		}
	}

	/**
	 * List all computers and start pagination
	 * 
	 * @return Result
	 */
	private Result listComputers() {
		IDAOComputer comp = new JDBCComputer(driver);
		Pagination<Computer> page = new Pagination<>(cli, comp.selectAll());
		log.debug("list computer");
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
		log.debug("create computer" + computer.toString());
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
		log.debug("update computer");
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
		log.debug("delete computer" + computer.getId());
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
		log.debug("list company");
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
