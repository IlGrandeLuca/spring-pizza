/**
 * 
 */
package org.generation.italy.service;

import java.util.List;

import org.generation.italy.model.Ingredient;
import org.generation.italy.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author lucai
 *
 */

@Service
public class IngredientService {

	@Autowired
	private IngredientRepository rep;

	public List<Ingredient> findAllSortByCategory() {
		return rep.findAll(Sort.by("ingredient"));
	}

}
