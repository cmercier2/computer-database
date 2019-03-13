package com.excilys.command;

import java.util.ArrayList;

import com.excilys.ui.CommandLineInterface;

public class InvalidCommand implements Command{

	@Override
	public Result exucute(CommandLineInterface cli) {
		return new Result(0, "Invalid Command");
	}

	@Override
	public Result handleArgument() {
		return new Result(1, "");
	}

}
