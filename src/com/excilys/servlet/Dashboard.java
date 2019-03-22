package com.excilys.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.excilys.model.Computer;
import com.excilys.pagination.Pagination;
import com.excilys.service.ArgumentHandler;
import com.excilys.service.PrintComputerService;

/**
 * Servlet implementation class ShowComputer
 */
@WebServlet("/")
public class Dashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PrintComputerService printService = new PrintComputerService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Dashboard() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Computer> comp = new ArrayList<>();
		String req = request.getParameter("navigate");
		switch(req) {
		case "next" :
			break;
		case "previous":
			comp = printService.init();
			break;
		default:
			comp = printService.init();
			break;
		}
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
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
