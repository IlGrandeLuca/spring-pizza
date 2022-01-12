package org.generation.italy.controller;

import org.generation.italy.model.Country;
import org.generation.italy.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class CountryController {
	
	@Autowired
	private CountryRepository repository;

	@GetMapping
	public String countries(Model model) {
		model.addAttribute("countries", repository.findAll(Sort.by("name")));
		return "countries";
	}
	
	@GetMapping("/country/{id}")
	public String countryDetail(Model model, @PathVariable Integer id) {
		Country c = repository.getById(id);
		
		model.addAttribute("country", c);
		model.addAttribute("region",c.getRegion());
		model.addAttribute("continent", c.getRegion().getContinent());
		model.addAttribute("languages", c.getLanguages());
		return "detail";
	}
}
