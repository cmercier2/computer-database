package com.excilys.utils;

public class Result {
	private int statut;
	private String message;

	/**
	 * 
	 * @param statut
	 * @param message
	 */
	public Result(int statut, String message) {
		this.message = message;
		this.statut = statut;
	}

	/**
	 * 
	 * @return
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 
	 * @return
	 */
	public int getStatut() {
		return statut;
	}

}
