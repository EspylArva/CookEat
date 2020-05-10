package com.horizon.cookeat.dao;

import java.util.List;

import com.horizon.cookeat.entities.*;;
 
public interface RecipeDao {
	public List<Recipe> getAllRecipes() ;
 
	public Recipe getRecipe(int id) ;
	public Recipe getRecipe(String description) ;
	 
	public Recipe addRecipe(Recipe recipe);
 
	public void updateRecipe(int id, Recipe recipe) ;
 
	public void deleteRecipe(int id) ;
	public void deleteRecipe(String description) ;
}