package com.excilys.command;

import java.util.ArrayList;

import com.excilys.driver.SQLDriver;
import com.excilys.model.Computer;

public class Switch {
	public static Command switcher(ArrayList<String> commande) {
		switch(commande.get(0)) {
			case "add" :
				if(commande.size() == 5) {
					
					return new CreateComputer(commande);
				}else {
					
				}
				default:
				return new CreateComputer(new ArrayList<>());
		}
		
		
		
		
	}
}
