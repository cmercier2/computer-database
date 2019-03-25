package com.excilys.servlet;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.PropertyConfigurator;

import com.excilys.model.Computer;
import com.excilys.service.PrintComputerService;

/**
 * Servlet implementation class ShowComputer
 */
//@WebServlet("/DashBoard")
public class DashBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrintComputerService printService = new PrintComputerService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DashBoard() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * public void init() { String cheminWebApp =
	 * getServletContext().getRealPath("/"); String cheminLogConfig = cheminWebApp +
	 * getInitParameter("log4j-fichier-config"); String cheminLog = cheminWebApp +
	 * getInitParameter("log4j-chemin-log"); File logPathDir = new File(cheminLog);
	 * System.setProperty("log.chemin", cheminLog); if (cheminLogConfig != null) {
	 * PropertyConfigurator.configure(cheminLogConfig); }
	 * 
	 * }
	 */

	private ArrayList<Computer> handleRequest(Optional<String> req){
		switch (req.orElse("")) {
		case "next":
			return printService.next();
		case "previous":
			return printService.previous();
		default:
			return printService.init();
		}
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Computer> comp = new ArrayList<>();
		Optional<String> req = Optional.ofNullable(request.getParameter("navigate"));
		comp = handleRequest(req);
		request.setAttribute("ComputerList", comp);
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/dashboard.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// doGet(request, response);
	}

}
