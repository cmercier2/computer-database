package com.excilys.command;

import java.sql.ResultSet;

public class Result {
	private int statut;
	private String message;
	
	public Result(int statut, String message) {
		this.message = message;
		this.statut = statut;
	}
	
}
