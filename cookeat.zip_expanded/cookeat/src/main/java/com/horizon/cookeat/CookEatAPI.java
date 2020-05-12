package com.horizon.cookeat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.horizon.cookeat.entities.Ingredient;
import com.horizon.cookeat.entities.Recipe;

@CrossOrigin
@RestController
public class CookEatAPI {

	private final Logger log = Logger.getLogger(this.getClass());
	
//	@Autowired
//	private RecipeRepository repo;
	
	@Autowired
	private CookEatService cookeat_services;
	   
	@RequestMapping(value = "/recipes")
	public List<Recipe> fetchAllRecipes() {
//		List<Recipe> allRecipes = cookeat_services.fetchAll();
		List<Recipe> allRecipes = cookeat_services.fetchAllRecipes();
		return allRecipes;
	}
	
	@RequestMapping(value = "/recipes", params = "id")
	public Map<String,Object> fetchRecipe(@RequestParam String id) {
		Map<String,Object> recipe = new HashMap<String, Object>();
		Object r = cookeat_services.fetchRecipe(Integer.valueOf(id)).get(0);
		recipe.put("recipe", r);
		recipe.put("ingredients", cookeat_services.getIngredients(Integer.valueOf(id)));
		return recipe;
	} 

	@RequestMapping(value = "/recipes", params = { "filter", "value" })
	public List<Recipe> fetchAllRecipesFilteredBy(@RequestParam(name="filter") String filter, @RequestParam(name="value") Object value)
	{
		List<Recipe> recipes = cookeat_services.fetchAllRecipesFilteredBy(com.horizon.cookeat.Filter.valueOf(filter.toUpperCase()), value);
		return recipes;
	}
	
//	@GetMapping("/recipes/{pageNumber}")
	@RequestMapping(value = "/recipes", params = { "id", "quantity" })
	public List<Recipe> updatePool(@RequestParam(name="id") String pageNumber, @RequestParam(name="quantity") String quantity)
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
	
//	@GetMapping("/recipes/price/{recipe_id}")
//	@RequestMapping(value = "/recipes", params = "price")
//	public int computePrice(@RequestParam String price) {
//		int recipes = cookeat_services.computeTotalPrice(Integer.valueOf(price));
//		return recipes ;
//	} 
	
}
