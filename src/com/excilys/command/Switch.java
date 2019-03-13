package com.excilys.command;

import java.util.ArrayList;

import com.excilys.driver.SQLDriver;

public class Switch {
	public static Command switcher(ArrayList<String> commande, SQLDriver driver) {
		switch(commande.get(0)) {
			case "1" :
				return new CreateComputer();
			default:
				return new CreateComputer();
		}
		
		
		
		
	}
}
