package com.excilys.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.excilys.DTO.ComputerDTO;
import com.excilys.DTO.ComputerDTO.ComputerDTOBuilder;
import com.excilys.exception.ComputerNotFoundException;
import com.excilys.exception.InvalidComputerName;
import com.excilys.service.EditComputerService;
import com.excilys.utils.ArgumentHandler;

@Controller
public class EditComputerController {
	private int currentId;
	@Autowired
	private EditComputerService service;

	@GetMapping({ "/EditComputer" })
	public String doGet(@RequestParam(required = false) Map<String, String> paths, Model model) throws IOException {
		currentId = ArgumentHandler.parseId(paths.get("id"));
		System.out.println("get : today was a good day");
		try {
			model.addAttribute("Computer", service.getComputer(currentId)
					.orElseThrow(() -> new ComputerNotFoundException("No computer with id " + currentId)));
			model.addAttribute("CompanyList", service.listCompanys());
		} catch (ClassNotFoundException | SQLException | ComputerNotFoundException | InvalidComputerName e) {
			e.printStackTrace();
		}
		return "editComputer";
	}

	@PostMapping({ "/EditComputer" })
	public void doPost(@RequestParam(required = false) Map<String, String> paths, Model model) throws IOException {
		System.out.println("post : today was a good day");
		ComputerDTO computer = new ComputerDTOBuilder().setId(ArgumentHandler.parseId(paths.get("id")))
				.setName(paths.get("name")).setIntroduced(paths.get("introduced"))
				.setDiscontinued(paths.get("discontinued")).setCompany(ArgumentHandler.parseId(paths.get("idCompany")))
				.build();
		EditComputerService service = new EditComputerService();
		try {
			service.editComputer(computer);
		} catch (ClassNotFoundException | InvalidComputerName | SQLException e) {
			e.printStackTrace();
		}
		model.addAttribute("id", currentId);
		doGet(paths, model);
	}
}
