/**
 * 
 */
package org.generation.italy.service;

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
	
	public Pizza save(Pizza pizza) {
		return rep.save(pizza);
	}

}
