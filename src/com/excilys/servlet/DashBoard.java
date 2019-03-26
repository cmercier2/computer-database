package com.excilys.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.DTO.ComputerDTO;
import com.excilys.service.PrintComputerService;

/**
 * Servlet implementation class ShowComputer
 */
//@WebServlet("/DashBoard")
public class DashBoard extends HttpServlet {
	private static final long serialVersionUID = 10L;
	private PrintComputerService printService = new PrintComputerService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DashBoard() {
		super();
	}

	private ArrayList<ComputerDTO> handleRequest(Optional<String> req) throws ClassNotFoundException, SQLException {
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
		Optional<String> req = Optional.ofNullable(request.getParameter("navigate"));
		ArrayList<ComputerDTO> comp;
		try {
			comp = handleRequest(req);
			request.setAttribute("ComputerList", comp);
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/dashboard.jsp");
			view.forward(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
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
