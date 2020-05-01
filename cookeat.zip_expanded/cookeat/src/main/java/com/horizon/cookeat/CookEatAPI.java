package com.horizon.cookeat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.horizon.cookeat.model.*;

@CrossOrigin
@RestController
public class CookEatAPI {

	  @Autowired
	  private Services cookeat_services;

//		  @GetMapping("/instructors/{username}/courses")
//		  public List<Course> getAllCourses(@PathVariable String username) {
//			  System.out.println("GETTING ALL COURSES FOR USERNAME " + username);
//			  return courseManagementService.findAll(username);
//		  }
	  
	  @GetMapping("/test")
	  public String ok() {
		  return "TEST OK";
	  }
	  
	  @GetMapping("/dbfetch")
	  public List<Recipe> db() {
		  return cookeat_services.listOfRecipes();
	  }
	  

}
