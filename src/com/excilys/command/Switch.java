package com.excilys.command;

import java.util.Arrays;


public class Switch {
	public static Command switcher(String commande) {
		String com = Arrays.asList(commande.split(" ")).get(0);
		switch(com) {
			case "createcomputer" :
					return new CreateComputer(commande);
			case "listcomputer":
					return new ListComputer();
			case "updatecomputer":
					return new UpdateComputer();
			case "deletecomputer":
					System.out.println("test");
					return new DeleteComputer(commande);
			case "listcompany":
					return new ListCompany();
			case "show":
					return new ShowComputer(commande);
				default:
				return new InvalidCommand();
		}
		
		
		
		
	}
}
