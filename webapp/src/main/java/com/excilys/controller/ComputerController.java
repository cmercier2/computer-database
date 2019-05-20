package com.excilys.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import com.excilys.dto.ComputerDTO;
import com.excilys.enums.Navigate;
import com.excilys.exception.ComputerNotFoundException;
import com.excilys.exception.InvalidComputerName;
import com.excilys.model.Company;
import com.excilys.model.Computer;
import com.excilys.service.AddComputerService;
import com.excilys.service.DeleteComputerService;
import com.excilys.service.EditComputerService;
import com.excilys.service.PrintComputerService;

@Controller
public class ComputerController {
	@Autowired
	private PrintComputerService printService;
	@Autowired
	private DeleteComputerService deleteService;
	@Autowired
	private CookieLocaleResolver localeResolver;
	@Autowired
	private EditComputerService editService;
	@Autowired
	private AddComputerService service;
	private int currentId;
	private Optional<String> search;
	private Optional<String> orderBy;

	public void changeLanguage(String lang) {
		switch (lang) {
		case "en":
			localeResolver.setDefaultLocale(Locale.ENGLISH);
			break;
		case "fr":
			localeResolver.setDefaultLocale(Locale.FRENCH);
			break;
		}
	}

	private List<ComputerDTO> handleRequest(Optional<String> req) throws ClassNotFoundException, SQLException {
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
	 * 
	 * @param paths
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@GetMapping({ "/" })
	public String doGet(@RequestParam(required = false) Map<String, String> paths, Model model) throws IOException {
		if (paths.containsKey("lang")) {
			changeLanguage(paths.get("lang"));
		}
		Optional<String> req = Optional.ofNullable(paths.get("navigate"));
		search = Optional.ofNullable(paths.get("SEARCH"));
		orderBy = Optional.ofNullable(paths.get("orderBy"));
		List<ComputerDTO> comp;
		try {
			comp = handleRequest(req);
			model.addAttribute("ComputerList", comp);
			model.addAttribute("size", printService.getsize());
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return "redirect:/Computers";
	}

	/**
	 * 
	 * @param paths
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@PostMapping({ "/Computers" })
	public String doPostDelete(@RequestParam(required = false) Map<String, String> paths, Model model)
			throws IOException {
		Optional<String> req = Optional.ofNullable(paths.get("selection"));
		try {
			deleteService.delete(req.orElse("").split(","));
			doGet(paths, model);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "dashboard";
	}

	/**
	 * 
	 * @param paths
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@GetMapping({ "/Computer/edit" })
	public String doGetEdit(@RequestParam(required = false) Map<String, String> paths, Model model) throws IOException {
		currentId = Integer.parseInt(paths.get("id"));
		try {
			model.addAttribute("computer", editService.getComputer(currentId)
					.orElseThrow(() -> new ComputerNotFoundException("No computer with id " + currentId)));
			model.addAttribute("CompanyList", editService.listCompanys());
		} catch (SQLException | ComputerNotFoundException e) {
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
	@PostMapping({ "/Computer/edit" })
	public String doPostEdit(@ModelAttribute("computer") @Validated ComputerDTO computer, ModelMap model)
			throws IOException {
		try {
			editService.editComputer(computer);
		} catch (InvalidComputerName | SQLException e) {
			e.printStackTrace();
		}
		model.addAttribute("id", currentId);
		return "dashboard";
	}

	/**
	 * 
	 * @param paths
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@GetMapping({ "/Computer/add" })
	public String doGetAdd(@RequestParam(required = false) Map<String, String> paths, Model model) throws IOException {
		List<Company> comp = service.listCompanys();
		model.addAttribute("CompanyList", comp);
		model.addAttribute("Computer", new Computer());
		return "addComputer";
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@PostMapping({ "/Computer/add" })
	protected String doPostAdd(@ModelAttribute("Computer") @Validated ComputerDTO computer, ModelMap model)
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
