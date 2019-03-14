package com.excilys.ui;

import java.util.ArrayList;
import java.util.Scanner;

import com.excilys.model.Company;
import com.excilys.model.Computer;

public class CommandLineInterface {
	private Scanner reader = new Scanner(System.in);
	private ArrayList<String> command = new ArrayList<>();
	private String input;

	/**
	 * 
	 * @return
	 */
	public boolean hasNext() {
		command.clear();
		System.out.print(" -> ");
		try {
			if (reader.hasNextLine()) {
				input = reader.nextLine();
				return true;
			}
		} catch (IllegalStateException e) {
			return false;
		}
		return false;
	}

	/**
	 * 
	 * @return
	 */
	public String next() {
		return input;
	}

	/**
	 * 
	 * @param res
	 */
	public void show(Computer res) {
		System.out.println(res.toString());
	}

	/**
	 * 
	 * @param res
	 */
	public void showComputers(ArrayList<Computer> res) {
		for (Computer c : res) {
			System.out.println(c.toString());
		}
	}

	/**
	 * 
	 * @param res
	 */
	public void showCompany(ArrayList<Company> res) {
		for (Company c : res) {
			System.out.println(c.toString());
		}
	}

}
