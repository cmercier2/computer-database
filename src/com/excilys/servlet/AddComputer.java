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
import com.excilys.DTO.ComputerDTO;
import com.excilys.DTO.ComputerDTO.ComputerDTOBuilder;
import com.excilys.model.Company;
import com.excilys.service.AddComputerService;
import com.excilys.utils.ArgumentHandler;

/**
 * Servlet implementation class AddComputer
 */
@WebServlet("/AddComputer")
public class AddComputer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddComputer() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AddComputerService service = new AddComputerService();
		ArrayList<Company> comp = service.listCompanys();
		request.setAttribute("CompanyList", comp);
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/addComputer.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ComputerDTO computer = new ComputerDTOBuilder().setName(request.getParameter("name"))
				.setIntroduced(request.getParameter("introduced")).setDiscontinued(request.getParameter("discontinued"))
				.setCompany(ArgumentHandler.parseId(request.getParameter("idCompany"))).build();
		AddComputerService service = new AddComputerService();
		try {
			service.addComputer(computer);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		doGet(request, response);
	}

}
