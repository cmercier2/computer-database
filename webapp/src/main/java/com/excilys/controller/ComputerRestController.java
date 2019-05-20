package com.excilys.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

import com.excilys.dto.ComputerDTO;
import com.excilys.enums.Navigate;
import com.excilys.service.PrintComputerService;

@RestController
public class ComputerRestController {
	@Autowired
	private PrintComputerService printService;
	@Autowired
	private CookieLocaleResolver localeResolver;
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

	@RequestMapping(value = { "/", "/Computers" })
	public ResponseEntity<List<ComputerDTO>> doGet(@RequestParam(required = false) Map<String, String> paths, Model model) {
		System.out.println("test");
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
			return new ResponseEntity<>(comp, HttpStatus.OK);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
