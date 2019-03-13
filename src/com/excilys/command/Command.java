package com.excilys.command;

import com.excilys.ui.CommandLineInterface;

public interface Command {
	public Result exucute(CommandLineInterface cli);
	public Result handleArgument();
}
