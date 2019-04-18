package com.excilys.exception;

public class InvalidComputerName extends Exception {

	private static final long serialVersionUID = 5000259472814291518L;

	public InvalidComputerName(String name) {
		super(name);
	}

}
