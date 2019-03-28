package com.excilys.utils;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class ArgumentHandler {
	/**
	 * Convert string to int
	 * 
	 * @param idToParse
	 * @return
	 */
	public static int parseId(String idToParse) throws NumberFormatException{
		return Integer.parseInt(idToParse);
	}

	/**
	 * COnvert string to date
	 * 
	 * @param dateString
	 * @return Date
	 */
	public static Optional<Date> parseDate(String dateString){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		try {
			return Optional.of(Date.valueOf(LocalDate.parse(dateString.trim(), formatter)));
		} catch (DateTimeParseException e) {
			e.printStackTrace();
			return Optional.empty();
		}
	}

}
