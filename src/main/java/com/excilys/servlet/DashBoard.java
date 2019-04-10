package com.excilys.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.excilys.DTO.ComputerDTO;
import com.excilys.enums.Navigate;
import com.excilys.enums.OrderBy;
import com.excilys.service.DeleteComputerService;
import com.excilys.service.PrintComputerService;

/**
 * Servlet implementation class ShowComputer
 */
//@WebServlet("/DashBoard")
public class DashBoard extends HttpServlet {
	private static final long serialVersionUID = 10L;
	private PrintComputerService printService = new PrintComputerService();
	private DeleteComputerService deleteService = new DeleteComputerService();
	private Optional<String> search;
	private Optional<String> orderBy;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DashBoard() {
		super();
	}
	
	private ArrayList<ComputerDTO> handleRequest(Optional<String> req) throws ClassNotFoundException, SQLException {
		String str = search.orElse("");
		switch (Navigate.valueOf(req.orElse("INIT"))) {
		case NEXT:
			return printService.next();
		case PREVIOUS:
			return printService.previous();
		case INIT:
			return printService.init(str, orderBy);
		case CURRENT:
			return printService.current();
		default:
			return printService.init(str, orderBy);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Optional<String> req = Optional.ofNullable(request.getParameter("navigate"));
		search = Optional.ofNullable(request.getParameter("SEARCH"));
		orderBy = Optional.ofNullable(request.getParameter("orderBy"));
		ArrayList<ComputerDTO> comp;
		try {
			comp = handleRequest(req);
			request.setAttribute("ComputerList", comp);
			request.setAttribute("size", printService.getsize());
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
		Optional<String[]> req = Optional.ofNullable(request.getParameterValues("selection"));
		try {
			deleteService.delete(req.orElse(new String[]{}));
			doGet(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}
