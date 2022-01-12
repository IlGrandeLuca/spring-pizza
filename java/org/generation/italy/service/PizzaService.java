/**
 * 
 */
package org.generation.italy.service;

import java.math.BigDecimal;
import java.util.List;

import org.generation.italy.model.Pizza;
import org.generation.italy.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author lucai
 *
 */

@Service
public class PizzaService {

	@Autowired
	private PizzaRepository rep;

	public List<Pizza> findAllSortedByPrice() {
		return rep.findAll(Sort.by("price"));
	}

	public List<Pizza> findByKeywordSortedByPrice(String keyword) {
		return rep.findByNameContainingIgnoreCaseOrderByPrice(keyword);
	}

	public Pizza getById(Integer id) {
		return rep.getById(id);
	}

	public Pizza create(Pizza pizza) {
		return rep.save(pizza);
	}

	public Pizza update(Pizza pizza) {
		BigDecimal price = rep.getById(pizza.getId()).getPrice();
		pizza.setPrice(price);
		return rep.save(pizza);
	}

	public void deleteById(Integer id) {
		rep.deleteById(id);
	}

}
