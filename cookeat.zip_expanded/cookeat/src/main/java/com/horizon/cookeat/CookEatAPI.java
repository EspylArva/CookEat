package com.horizon.cookeat;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.horizon.cookeat.entities.*;

@CrossOrigin
@RestController
public class CookEatAPI {

	private final Logger log = Logger.getLogger(this.getClass());
	
//	@Autowired
//	private RecipeRepository repo;
	
	@Autowired
	private CookEatService cookeat_services;
	   
	@GetMapping("/recipes")
	public List<Recipe> fetchAllRecipes() {
		List<Recipe> allRecipes = cookeat_services.fetchAllRecipes();
		return allRecipes;
	}
	
	@GetMapping("/recipes/id")
	public List<Recipe> fetchRecipe(@RequestParam String id) {
		List<Recipe> recipe = cookeat_services.fetchRecipe(Integer.valueOf(id));
		return recipe;
	} 
	
	@GetMapping("/recipes/price/{recipe_id}")
	public int computePrice(@PathVariable String recipe_id) {
		int price = cookeat_services.computeTotalPrice(Integer.valueOf(recipe_id));
		return price ;
	} 

	@GetMapping("/recipes/{Filter}/{Filter_value}")
	public List<Recipe> fetchAllRecipesFilteredBy(@PathVariable(name="Filter") String Filter, @PathVariable(name="Filter_value") Object Filter_value)
	{
		List<Recipe> recipes = cookeat_services.fetchAllRecipesFilteredBy(com.horizon.cookeat.Filter.valueOf(Filter.toUpperCase()), Filter_value);
		return recipes;
	}
	
	@GetMapping("/recipes/{pageNumber}")
	public List<Recipe> updatePool(@PathVariable(name="pageNumber") String pageNumber, @RequestParam String quantity)
	{
		List<Recipe> newPool = cookeat_services.fetchPool(Integer.valueOf(pageNumber), Integer.valueOf(quantity));
		return newPool;
	}
	
	@GetMapping("/ingredient/{recette_id}")
	public List<Ingredient> getIngredient(@PathVariable String recette_id)
	{
		List<Ingredient> ingredients = cookeat_services.getIngredients(Integer.valueOf(recette_id));
		return ingredients;
	}
	
}
