package com.excilys.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.excilys.DTO.ComputerDTO;
import com.excilys.DTO.ComputerDTO.ComputerDTOBuilder;
import com.excilys.exception.InvalidComputerName;
import com.excilys.model.Company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.service.AddComputerService;
import com.excilys.utils.ArgumentHandler;

@Controller
public class AddComputerController {
	@Autowired
	private AddComputerService service;

	@GetMapping({ "/AddComputer" })
	public String doGet(@RequestParam(required = false) Map<String, String> paths, Model model) throws IOException {
		ArrayList<Company> comp = service.listCompanys();
		model.addAttribute("CompanyList", comp);
		model.addAttribute("Computer", new ComputerDTOBuilder().build());
		return "addComputer";
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@PostMapping({ "/AddComputer" })
	protected void doPost(@RequestParam(required = false) Map<String, String> paths, Model model) throws IOException {
		ComputerDTO computer = new ComputerDTOBuilder().setName(paths.get("name"))
				.setIntroduced(paths.get("introduced")).setDiscontinued(paths.get("discontinued"))
				.setCompany(ArgumentHandler.parseId(paths.get("idCompany"))).build();
		AddComputerService service = new AddComputerService();
		try {
			service.addComputer(computer);
		} catch (ClassNotFoundException | SQLException | InvalidComputerName e) {
			e.printStackTrace();
		}
		doGet(paths, model);
	}
}
