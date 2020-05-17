package com.horizon.cookeat.service;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.horizon.cookeat.config.Utils;
import com.horizon.cookeat.entities.*;

@CrossOrigin
@RestController
public class CookEatAPI {

	private final Logger log = Logger.getLogger(this.getClass());
	
//	@Autowired
//	private RecipeRepository repo;
	
	@Autowired
	private CookEatService cookeat_services;
	   
	@ResponseBody
	@RequestMapping(value = "/recipes")
	public String fetchAllRecipes() {
		List<JsonObject> recipes = cookeat_services.toListJson(cookeat_services.fetchAllRecipes());
		return recipes.toString();
	}
	
	@RequestMapping(value = "/recipes", params = "id")
	public String fetchRecipe(@RequestParam String id) {
		List<JsonObject> recipes = cookeat_services.toListJson(cookeat_services.fetchRecipe(Integer.valueOf(id)));
		return recipes.get(0).toString();
	} 

	@RequestMapping(value = "/recipes", params = { "filter", "value" })
	public String fetchAllRecipesFilteredBy(@RequestParam(name="filter") String filter, @RequestParam(name="value") Object value)
	{
		List<JsonObject> recipes = cookeat_services.toListJson(cookeat_services.fetchAllRecipesFilteredBy(com.horizon.cookeat.config.Filter.valueOf(filter.toUpperCase()), value));
		return recipes.toString();
	}
	
//	@GetMapping("/recipes/{pageNumber}")
	@RequestMapping(value = "/recipes", params = { "id", "quantity" })
	public String updatePool(@RequestParam(name="id") String pageNumber, @RequestParam(name="quantity") String quantity)
	{
		List<JsonObject> recipes = cookeat_services.toListJson(cookeat_services.fetchPool(Integer.valueOf(pageNumber), Integer.valueOf(quantity)));
		return recipes.toString();
	}
	
//	@GetMapping("/ingredient/{recette_id}")
//	public List<R_Ingredient> getIngredient(@PathVariable String recette_id)
//	{
//		List<R_Ingredient> ingredients = cookeat_services.getIngredients(Integer.valueOf(recette_id));
//		return ingredients;
//	}
	
//	@GetMapping("/recipes/price/{recipe_id}")
//	@RequestMapping(value = "/recipes", params = "price")
//	public int computePrice(@RequestParam String price) {
//		int recipes = cookeat_services.computeTotalPrice(Integer.valueOf(price));
//		return recipes ;
//	} 
	
}
