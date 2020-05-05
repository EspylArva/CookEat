package com.horizon.cookeat;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.horizon.cookeat.entities.*;

@CrossOrigin
@RestController
public class CookEatAPI {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private Services cookeat_services;
	   
	@GetMapping("/fetchall")
	public List<Recipe> fetchAllRecipes() {
		log.debug("Appel à l'API:");
		List<Recipe> allRecipes = cookeat_services.fetchAllRecipes();
		log.debug("Fin d'appel à l'API");
		return allRecipes;
	}
	
	@GetMapping("/fetch/{recipe_name}")
	public List<Recipe> fetchRecipe(@PathVariable String recipe_name) {
		log.debug("Appel à l'API:");
		List<Recipe> recipe = cookeat_services.fetchRecipe(recipe_name);
		log.debug("Fin d'appel à l'API");
		return recipe;
	} 

	@GetMapping("/fetchAll/filterBy/{Filter}/{Filter_value}")
	public List<Recipe> fetchAllRecipesFilteredBy(@PathVariable String Filter, @PathVariable String Filter_value)
	{
		List<Recipe> recipes = cookeat_services.fetchAllRecipesFilteredBy(com.horizon.cookeat.Filter.valueOf(Filter.toUpperCase()), Integer.valueOf(Filter_value));
		return null;
	}
	
	@GetMapping("/update-pool/{pageNumber}")
	public List<Recipe> updatePool(@PathVariable String pageNumber)
	{
		List<Recipe> newPool = null;
		for(int i=1; i<10; i++)
		{
			newPool = cookeat_services.fetchPool(i);
			for(Object recipe : newPool)
			{
				System.out.println(((Recipe)recipe).getDesignation());
			}
		}
//		return newPool;
		return null;
	}
}
