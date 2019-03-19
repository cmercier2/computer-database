package com.excilys.argumenthandler;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.excilys.model.Computer;

public class ArgumentHandler {
	/**
	 * Convert string to int
	 * 
	 * @param idToParse
	 * @return
	 */
	private static int parseId(String idToParse) {
		int number;
		try {
			number = Integer.parseInt(idToParse);
		} catch (NumberFormatException e) {
			return -1;
		}
		return number;
	}

	/**
	 * COnvert string to date
	 * 
	 * @param dateString
	 * @return Date
	 */
	private static Date parseDate(String dateString) {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date utilDate = null;
		try {
			utilDate = format.parse(dateString);
		} catch (ParseException e) {
			return null;
		}
		return new Date(utilDate.getTime());
	}

	/**
	 * Convert an input string command to a computer
	 * 
	 * @param command
	 * @return Computer
	 */
	public static Computer creationArgument(String command) {
		Computer computer = null;
		Date introduced, discontinued;
		int idcompany;
		String argument;
		String[] parse = command.split(" ", 2);
		if(parse.length != 2) {
			return null;
		}
		argument = parse[1];
		String tokens[] = argument.split(";");
		if (tokens.length == 4) {
			computer = new Computer(tokens[0]);
			if ((introduced = parseDate(tokens[1])) != null) {
				computer.setIntroduced(introduced);
			}
			if ((discontinued = parseDate(tokens[2])) != null) {
				computer.setDiscontinued(discontinued);
			}
			if ((idcompany = parseId(tokens[3])) != -1) {
				computer.setCompany(idcompany);
			}
		}
		return computer;
	}

	/**
	 * Parse show command arguments
	 * 
	 * @param command
	 * @return
	 */
	public static int showArgument(String command) {
		String[] tokens = command.split(" ", 2);
		if(tokens.length == 2) {
			String argument = tokens[1];
			int entier = parseId(argument);
			return entier;
		}
		return -1;
	}

	/**
	 * Parse delete command arguments
	 * 
	 * @param command
	 * @return int
	 */
	public static int deleteArgument(String command) {
		String[] tokens = command.split(" ", 2);
		if(tokens.length == 2) {
			String argument = tokens[1];
			int entier = parseId(argument);
			return entier;
		}
		return -1;
	}

	/**
	 * Convert an input string command to a computer for update it
	 * 
	 * @param command
	 * @return Computer
	 */
	public static Computer updateArgument(String command) {
		Computer computer = null;
		Date introduced, discontinued;
		int idcompany, idcomputer;
		String argument = command.split(" ", 2)[1];
		String idToUpdate = command.split(" ", 2)[2];
		String tokens[] = argument.split(";");
		if (tokens.length == 4) {
			computer = new Computer(tokens[0]);
			if ((introduced = parseDate(tokens[1])) != null) {
				computer.setIntroduced(introduced);
			}
			if ((discontinued = parseDate(tokens[2])) != null) {
				computer.setDiscontinued(discontinued);
			}
			if ((idcompany = parseId(tokens[3])) != -1) {
				computer.setCompany(idcompany);
			}
			if ((idcomputer = parseId(idToUpdate)) != -1) {
				computer.setId(idcomputer);
			}
		}
		return computer;
	}

}
