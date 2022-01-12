/**
 * 
 */
package org.generation.italy.controller;

import java.util.List;

import org.generation.italy.model.Pizza;
import org.generation.italy.service.IngredientService;
import org.generation.italy.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author lucai
 *
 */

@Controller
@RequestMapping("/pizzas")
public class PizzaController {

	@Autowired
	private PizzaService service;

	@Autowired
	private IngredientService ingredientService;

	@GetMapping
	public String list(Model model, @RequestParam(name = "keyword", required = false) String keyword) {
		List<Pizza> result;
		if(keyword != null && !keyword.isEmpty()) {
			result = service.findByKeywordSortedByRecent(keyword);
			model.addAttribute("keyword", keyword);
		} else {
			result = service.findAllSortedByRecent();
		}
		model.addAttribute("list", result);
		return "/pizzas/list";
	}

	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("pizza", new Pizza());
		return "/pizzas/edit";
	}

	@PostMapping("/create")
	public String doCreate(@ModelAttribute("book") Pizza formPizza, Model model) {
		service.create(formPizza);
		return "redirect:/pizzas";
	}

}
