package com.excilys.service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import com.excilys.model.Computer;
import com.excilys.model.Computer.ComputerBuilder;

public class ArgumentHandler {
	/**
	 * Convert string to int
	 * 
	 * @param idToParse
	 * @return
	 */
	public static int parseId(String idToParse) {
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
	private static Optional<Date> parseDate(String dateString) {
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date utilDate = null;
		try {
			utilDate = format.parse(dateString);
		} catch (ParseException e) {
			return null;
		}
		return Optional.of(new Date(utilDate.getTime()));
	}

	/**
	 * Convert an input string command to a computer
	 * 
	 * @param command
	 * @return Computer
	 */
	public static Optional<Computer> creationArgument(String command) {
		String tokens[] = command.split(";");
		if (tokens.length == 4) {
			Optional.of(new ComputerBuilder().setName(tokens[0]).setIntroduced(parseDate(tokens[1]).orElse(null))
					.setDiscontinuede(parseDate(tokens[2]).orElse(null)).setCompany(parseId(tokens[3])).build());
		}
		return Optional.empty();
	}

	/**
	 * Convert an input string command to a computer for update it
	 * 
	 * @param command
	 * @return Computer
	 */
	public static Optional<Computer> updateArgument(String command) {
		String tokens[] = command.split(";");
		if (tokens.length == 5) {

			return Optional.of(new ComputerBuilder().setId(parseId(tokens[0])).setName(tokens[1])
					.setIntroduced(parseDate(tokens[2]).orElse(null))
					.setDiscontinuede(parseDate(tokens[3]).orElse(null)).setCompany(parseId(tokens[4])).build());
		}
		return Optional.empty();
	}

}
