package com.horizon.cookeat;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.horizon.cookeat.model.*;

@CrossOrigin
@RestController
public class CookEatAPI {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private Services cookeat_services;
	
//	@GetMapping("/instructors/{username}/courses")
//	public List<Course> getAllCourses(@PathVariable String username) {
//		System.out.println("GETTING ALL COURSES FOR USERNAME " + username);
//		return courseManagementService.findAll(username);
//	}
	  
	@GetMapping("/test")
	public String ok() {
		return "TEST OK";
	}
	   
	@GetMapping("/fetch/all")
	public List<Recipe> fetchAllRecipes() {
		System.out.println("Appel à l'API:");
		log.debug("Appel à l'API:");
		List<Recipe> allRecipes = cookeat_services.fetchAllRecipes();
		log.debug("Fin d'appel à l'API");
		return allRecipes;
	}
	
	@GetMapping("/fetch/{recipe_name}")
	public Recipe fetchRecipe(@PathVariable String recipeName) {
		log.debug("Appel à l'API:");
		Recipe recipe = cookeat_services.fetchRecipe(recipeName);
		log.debug("Fin d'appel à l'API");
		return recipe;
	} 
	  

}
