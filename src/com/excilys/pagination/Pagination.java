package com.excilys.pagination;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.excilys.ui.CommandLineInterface;

public class Pagination<T> {
	private ArrayList<T> toPrint = new ArrayList<>();
	private CommandLineInterface cli;
	private int startStep = 0;
	private int endStep;
	private int step = 10;
	private boolean end = false;
	private boolean start = true;

	public Pagination(CommandLineInterface cli, ArrayList<T> toPrint) {
		Objects.requireNonNull(toPrint);
		Objects.requireNonNull(cli);
		this.toPrint = toPrint;
		this.cli = cli;
		this.endStep = step;
	}

	/**
	 * Print next step element of toPrint arraylist
	 */
	public void previous() {
		List<T> buff = null;
		end = false;
		startStep -= step;
		endStep -= step;
		buff = toPrint.subList(startStep, endStep);
		for (T s : buff)
			System.out.println(s.toString());
		if (startStep == 0)
			start = true;
	}

	/**
	 * Print next step element of toPrint arraylist
	 */
	public void next() {
		List<T> buff = null;
		startStep += step;
		endStep += step;
		start = false;
		if (endStep > toPrint.size()) {
			buff = toPrint.subList(startStep, toPrint.size());
			end = true;
		} else {
			buff = toPrint.subList(startStep, endStep);
		}
		for (T s : buff)
			System.out.println(s.toString());
	}

	/**
	 * print step first element of the arraylist
	 */
	public void init() {
		List<T> buff = null;
		start = false;
		if (endStep > toPrint.size()) {
			buff = toPrint.subList(startStep, toPrint.size());
			end = true;
		} else {
			buff = toPrint.subList(startStep, endStep);
		}
		for (T s : buff)
			System.out.println(s.toString());
	}

	/**
	 * switch function for user to navigate through the arraylist
	 */
	public void pagine() {
		String input = "";
		System.out.println("n next ; p previous ; q quit");
		init();
		while (cli.hasNext()) {
			input = cli.next();
			switch (input) {
			case "n":
				if (!end)
					next();
				break;
			case "p":
				if (!start)
					previous();
				break;
			case "q":
				return;
			default:
				break;
			}

		}
	}

}
