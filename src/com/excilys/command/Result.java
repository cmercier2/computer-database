package com.excilys.command;


public class Result {
	private int statut;
	private String message;
	
	public Result(int statut, String message) {
		this.message = message;
		this.statut = statut;
	}
	
	public String getMessage() {
		return message;
	}
	
	public int getStatut() {
		return statut;
	}
	
}
