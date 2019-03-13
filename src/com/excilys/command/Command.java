package com.excilys.command;

import java.util.ArrayList;

public interface Command {
	public void exucute();
	public void handleArgument(ArrayList<String> argument);
}
