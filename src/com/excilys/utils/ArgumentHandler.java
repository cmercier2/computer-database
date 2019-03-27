package com.excilys.utils;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		try {
			return Optional.of(Date.valueOf(LocalDateTime.parse(dateString, formatter).toLocalDate()));
		} catch (DateTimeParseException e) {
			return Optional.empty();
		}
	}

}
