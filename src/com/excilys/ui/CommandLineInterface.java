package com.excilys.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.excilys.command.Result;
import com.excilys.model.Company;
import com.excilys.model.Computer;

public class CommandLineInterface {
	private Scanner reader = new Scanner(System.in);
	private ArrayList<String> command = new ArrayList<>();
	
	public String getUserINput() {
		String input = "";
		command.clear();
		System.out.println(" -> ");
		if(reader.hasNextLine()) {
			input = reader.nextLine();
		}
		return input;
	}

	
	public void show(Computer res) {
		System.out.println(res.toString());
	}
	
	public void showComputers(ArrayList<Computer> res) {
		for(Computer c : res) {
			System.out.println(c.toString());
		}
	}
	
	public void showCompany(ArrayList<Company> res) {
		for(Company c : res) {
			System.out.println(c.toString());
		}
	}
	
}
