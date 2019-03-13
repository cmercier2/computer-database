package com.excilys.command;

import java.util.ArrayList;

public interface Command {
	public Result exucute();
	public void handleArgument();
}
