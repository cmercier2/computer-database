package com.excilys.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.dto.ComputerDTO;
import com.excilys.exception.ComputerNotFoundException;
import com.excilys.exception.InvalidComputerName;
import com.excilys.service.EditComputerService;

@Controller
public class EditComputerController {
	private int currentId;
	@Autowired
	private EditComputerService service;

	/**
	 * 
	 * @param paths
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@GetMapping({ "/EditComputer" })
	public String doGet(@RequestParam(required = false) Map<String, String> paths, Model model) throws IOException {
		currentId = Integer.parseInt(paths.get("id"));
		try {
			model.addAttribute("computerdto", service.getComputer(currentId)
					.orElseThrow(() -> new ComputerNotFoundException("No computer with id " + currentId)));
			model.addAttribute("CompanyList", service.listCompanys());
		} catch (SQLException | ComputerNotFoundException | InvalidComputerName e) {
			e.printStackTrace();
		}
		return "editComputer";
	}

	/**
	 * 
	 * @param computer
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@PostMapping({ "/EditComputer" })
	public String doPost(@ModelAttribute("computerdto") @Validated ComputerDTO computer, ModelMap model)
			throws IOException {
		try {
			service.editComputer(computer);
		} catch (InvalidComputerName | SQLException e) {
			e.printStackTrace();
		}
		model.addAttribute("id", currentId);
		return "dashboard";
	}
}
