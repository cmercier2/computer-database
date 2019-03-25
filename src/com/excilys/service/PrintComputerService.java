package com.excilys.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.excilys.model.Computer;
import com.excilys.pagination.Pagination;

public class PrintComputerService {
	private Pagination page = new Pagination();
	
	public ArrayList<Computer> init(){
		ArrayList<Computer> pageInit = new ArrayList<>();
		try {
			pageInit = page.init();
			return pageInit;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return pageInit;
	}
	
	public ArrayList<Computer> next(){
		ArrayList<Computer> pageNext = new ArrayList<>();
		try {
			pageNext = page.next();
			return pageNext;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return pageNext;
	}
	
	public ArrayList<Computer> previous(){
		ArrayList<Computer> pagePrevious = new ArrayList<>();
		try {
			pagePrevious = page.previous();
			return pagePrevious;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return pagePrevious;
	}
	
}
