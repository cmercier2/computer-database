package com.excilys.service;

import java.util.ArrayList;

import com.excilys.model.Computer;
import com.excilys.pagination.Pagination;

public class PrintComputerService {
	private Pagination page = new Pagination();
	
	public ArrayList<Computer> init(){
		return new ArrayList<>();
	}
	
}
