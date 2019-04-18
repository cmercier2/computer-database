package com.excilys.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import com.excilys.dto.ComputerDTO;
import com.excilys.dto.ComputerDTO.ComputerDTOBuilder;
import com.excilys.exception.InvalidComputerName;
import com.excilys.model.Company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.service.AddComputerService;

@Controller
public class AddComputerController {
	@Autowired
	private AddComputerService service;

	/**
	 * 
	 * @param paths
	 * @param model
	 * @return
	 * @throws IOException
	 */
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
	protected String doPost(@ModelAttribute("Computer") @Validated ComputerDTO computer, ModelMap model)
			throws IOException {
		System.out.println("computer : " + computer.toString());
		try {
			service.addComputer(computer);
		} catch (ClassNotFoundException | SQLException | InvalidComputerName e) {
			e.printStackTrace();
		}
		return "dashboard";
	}
}
