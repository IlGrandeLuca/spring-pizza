/**
 * 
 */
package org.generation.italy.controller;

import java.util.List;

import javax.validation.Valid;

import org.generation.italy.model.Pizza;
import org.generation.italy.service.IngredientService;
import org.generation.italy.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		if (keyword != null && !keyword.isEmpty()) {
			result = service.findByKeywordSortedByPrice(keyword);
			model.addAttribute("keyword", keyword);
		} else {
			result = service.findAllSortedByPrice();
		}
		model.addAttribute("list", result);
		return "/pizzas/list";
	}

	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("pizza", service.getById(id));
		return "/pizzas/detail";
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

	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("edit", true);
		model.addAttribute("book", service.getById(id));
		model.addAttribute("ingredientList", ingredientService.findAllSortByCategory());
		return "/pizzas/edit";
	}

	@PostMapping("/edit/{id}")
	public String doUpdate(@Valid @ModelAttribute("pizza") Pizza formPizza, BindingResult bindingResult, Model model,
			RedirectAttributes redirectAttributes) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("edit", true);
			model.addAttribute("ingredientList", ingredientService.findAllSortByCategory());
			return "/pizzas/edit";
		}
		service.update(formPizza);
		redirectAttributes.addFlashAttribute("successMessage", "Book edited!");
		return "redirect:/pizzas";
	}

	@GetMapping("/delete/{id}")
	public String doDelete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
		if (service.getById(id) == null) {
			// Return error message
		}
		service.deleteById(id);
		redirectAttributes.addFlashAttribute("successMessage", "Book deleted!");
		return "redirect:/pizzas";
	}

}
