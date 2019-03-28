package com.excilys.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.excilys.DTO.ComputerDTO;
import com.excilys.DTO.ComputerDTO.ComputerDTOBuilder;
import com.excilys.exception.ComputerNotFoundException;
import com.excilys.exception.InvalidComputerName;
import com.excilys.service.EditComputerService;
import com.excilys.utils.ArgumentHandler;

/**
 * Servlet implementation class EditComputer
 */
@WebServlet("/EditComputer")
public class EditComputer extends HttpServlet {
	private static final long serialVersionUID = 100L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditComputer() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = ArgumentHandler.parseId(request.getParameter("id"));
		System.out.println(id);
		EditComputerService service = new EditComputerService();
		try {
			request.setAttribute("Computer",
					service.getComputer(id).orElseThrow(() -> new ComputerNotFoundException("No computer with id " + id)));
			request.setAttribute("CompanyList", service.listCompanys());
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/editComputer.jsp");
			view.forward(request, response);
		} catch (ClassNotFoundException | SQLException | ComputerNotFoundException | InvalidComputerName e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ComputerDTO computer = new ComputerDTOBuilder().setId(ArgumentHandler.parseId(request.getParameter("id"))).setName(request.getParameter("name"))
				.setIntroduced(request.getParameter("introduced")).setDiscontinued(request.getParameter("discontinued"))
				.setCompany(ArgumentHandler.parseId(request.getParameter("idCompany"))).build();
		EditComputerService service = new EditComputerService();
			try {
				service.editComputer(computer);
			} catch (ClassNotFoundException | InvalidComputerName | SQLException e) {
				e.printStackTrace();
			}
		doGet(request, response);
	}

}
