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
import com.horizon.cookeat.entities.*;

@CrossOrigin
@RestController
public class CookEatAPI {

	private final Logger log = Logger.getLogger(this.getClass());
	
	
	@Autowired
	private CookEatService service;
	   
	@ResponseBody
	@RequestMapping(value = "/recipes")
	public String fetchAllRecipes() {
		List<JsonObject> recipes = service.toListJson(service.fetchAllRecipes());
		return recipes.toString();
	}
	
	@RequestMapping(value = "/recipes", params = "id")
	public String fetchRecipe(@RequestParam String id) {
		List<JsonObject> recipes = service.toListJson(service.fetchRecipe(Integer.valueOf(id)));
		return recipes.get(0).toString();
	} 

	@RequestMapping(value = "/recipes", params = { "filter", "value" })
	public String fetchAllRecipesFilteredBy(@RequestParam(name="filter") String filter, @RequestParam(name="value") Object value)
	{
		List<JsonObject> recipes = service.toListJson(service.fetchAllRecipesFilteredBy(com.horizon.cookeat.config.Filter.valueOf(filter.toUpperCase()), value));
		return recipes.toString();
	}
	
//	@GetMapping("/recipes/{pageNumber}")
	@RequestMapping(value = "/recipes", params = { "id", "quantity" })
	public String updatePool(@RequestParam(name="id") String pageNumber, @RequestParam(name="quantity") String quantity)
	{
		List<JsonObject> recipes = service.toListJson(service.fetchPool(Integer.valueOf(pageNumber), Integer.valueOf(quantity)));
		return recipes.toString();
	}
	
	@GetMapping("/test")
	public String test()
	{
		List<Etape> _s = service.getSteps(Integer.valueOf(100));
		List<Gallery> _g = service.getGallery(Integer.valueOf(100));
	
		
		return null;
	}	
}
