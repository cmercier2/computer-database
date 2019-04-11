package com.excilys.controler;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.DTO.ComputerDTO;
import com.excilys.enums.Navigate;
import com.excilys.service.DeleteComputerService;
import com.excilys.service.PrintComputerService;


@Controller
public class DashBordController {
	@Autowired
	private PrintComputerService printService;
	@Autowired
	private DeleteComputerService deleteService;
	private Optional<String> search;
	private Optional<String> orderBy;

	
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
	@GetMapping({ "/" })
	protected String doGet(@RequestParam(required = false) Map<String, String> paths, Model model)
			throws ServletException, IOException {
		Optional<String> req = Optional.ofNullable(paths.get("navigate"));
		search = Optional.ofNullable(paths.get("SEARCH"));
		orderBy = Optional.ofNullable(paths.get("orderBy"));
		ArrayList<ComputerDTO> comp;
		try {
			comp = handleRequest(req);
			model.addAttribute("ComputerList", comp);
			model.addAttribute("size", printService.getsize());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return "dashboard";
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@PostMapping({ "/" })
	protected void doPost(@RequestParam(required = false) Map<String, String> paths, Model model)
			throws ServletException, IOException {
		/*Optional<String[]> req = Optional.ofNullable(paths.get("selection"));
		try {
			deleteService.delete(req.orElse(new String[]{}));
			doGet(request, response);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}*/
	}
	
}
