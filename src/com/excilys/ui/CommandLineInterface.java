package com.excilys.ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CommandLineInterface {
	private Scanner reader = new Scanner(System.in);
	private ArrayList<String> command = new ArrayList<>();
	
	public ArrayList<String> getUserINput() {
		String input = "";
		command.clear();
		System.out.println(" -> ");
		if(reader.hasNextLine()) {
			input = reader.nextLine();
		}
		command.addAll(Arrays.asList(input));
		return command;
	}
}
