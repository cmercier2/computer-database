package com.excilys.controller;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

import org.apache.log4j.Logger;

import com.excilys.driver.SQLDriver;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.pagination.Pagination;
import com.excilys.service.ArgumentHandler;
import com.excilys.service.JDBC.JDBCCompany;
import com.excilys.service.JDBC.JDBCComputer;
import com.excilys.ui.CommandLineInterface;
import com.excilys.utils.LoggerConfigurator;
import com.excilys.utils.Result;

public class Controller {
	private CommandLineInterface cli;
	private static final Logger log = LoggerConfigurator.configureLogger(Controller.class);

	/**
	 * Constructor of controller
	 * 
	 * @param driver
	 * @param cli
	 */
	public Controller(CommandLineInterface cli) {
		Objects.requireNonNull(cli);
		this.cli = cli;
	}

	/**
	 * Get a command line with a computer id and print information about this
	 * computer
	 * 
	 * @param commande
	 * @return Result
	 */
	private void showComputer(String commande) {
		JDBCComputer comp = new JDBCComputer();
		int id = ArgumentHandler.showArgument(commande);
		if (id == -1) {
			cli.ShowMessage("Unknown Computer");
			return;
		}
		Computer computer = new Computer(id);
		try {
			Optional<Computer> result = comp.select(computer);
			if (!result.isPresent()) {
				cli.ShowMessage("Unknown computer");
				return;
			}
			cli.show(result.get());
			log.debug("show computer" + computer.getId());
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}

	/**
	 * List all computers and start pagination
	 * 
	 * @return Result
	 */
	private void listComputers() {
		JDBCComputer comp = new JDBCComputer();
		Pagination<Computer> page;
		try {
			page = new Pagination<>(cli, comp.selectAll());
			log.debug("list computer");
			page.pagine();
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}

	/**
	 * Create a computer with informations enter by the user
	 * 
	 * @param commande
	 * @return Result
	 */
	private void createComputerComputer(String commande) {
		JDBCComputer comp = new JDBCComputer();
		Computer computer = ArgumentHandler.creationArgument(commande);
		log.debug("create computer" + computer.toString());
		try {
			comp.create(computer);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}

	/**
	 * Update a computer with informations enter by the user
	 * 
	 * @param commande
	 * @return Result
	 */
	private void updateComputerComputer(String commande) {
		JDBCComputer comp = new JDBCComputer();
		Computer computer = new Computer(1);
		log.debug("update computer");
		try {
			comp.update(computer);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}

	/**
	 * Get a command line with a id and delete the corresponding computer
	 * 
	 * @param commande
	 * @return Result
	 */
	private void deleteComputerComputer(String commande) {
		JDBCComputer comp = new JDBCComputer();
		int id = ArgumentHandler.deleteArgument(commande);
		if (id == -1) {
			return;
		}
		Computer computer = new Computer(id);
		log.debug("delete computer" + computer.getId());
		try {
			comp.delete(computer);
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}

	/**
	 * List all companys and start pagination
	 * 
	 * @return Result
	 */
	public void listCompanys() {
		JDBCCompany comp = new JDBCCompany();
		Pagination<Company> page;
		try {
			page = new Pagination<>(cli, comp.selectAll());
			log.debug("list company");
			page.pagine();
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
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
