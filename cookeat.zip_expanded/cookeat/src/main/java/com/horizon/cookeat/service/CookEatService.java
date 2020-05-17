package com.horizon.cookeat.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.horizon.cookeat.config.Filter;
import com.horizon.cookeat.entities.*;

@Service
public class CookEatService {
	
	private final Logger log = Logger.getLogger(this.getClass());
	private SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

	public List<Recipe> fetchAllRecipes()
	{
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<Recipe> recipes = null;  
        try
    	{
        	Gson gson = new Gson();
        	tx = session.beginTransaction();
        	String hql = "Select _recipe from Recipe as _recipe";
			TypedQuery<Recipe> q = session.createQuery(hql, Recipe.class);
        	recipes = q.getResultList();
    	}
        catch (RuntimeException e) {
		    if (tx != null) tx.rollback();
		    throw e;
		}
		finally { session.close(); }
    	return recipes;
	}
	

	public List<Recipe> fetchRecipe(int recipe_id)
	{
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<Recipe> recipes = null;  
        try
    	{
        	tx = session.beginTransaction();
        	String hql = String.format("select _recipe from Recipe _recipe where _recipe.id='%s'", recipe_id);
        	@SuppressWarnings("unchecked")
			Query<Recipe> q = session.createQuery(hql);
        	recipes = q.list();
    	}
        catch (RuntimeException e) {
		    if (tx != null) tx.rollback();
		    throw e;
		}
		finally { session.close(); }
    	return recipes;
	}	
	
	public List<Recipe> fetchPool(int pageNumber, int quantity)
	{
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<Recipe> recipes = null;  
        try
    	{
        	tx = session.beginTransaction();
        	String hql = "select _recipe from Recipe as _recipe";
        	@SuppressWarnings("unchecked")
			Query<Recipe> q = session.createQuery(hql);
    		q.setFirstResult(pageNumber - 100); // 100 : starting index of recipes
            q.setMaxResults(quantity);
        	recipes = q.list();

    	}
        catch (RuntimeException e) {
		    if (tx != null) tx.rollback();
		    throw e;
		}
		finally { session.close(); }
    	return recipes;
	}

	public List<Recipe> fetchAllRecipesFilteredBy(Filter filter, Object filterValue)
	{
		Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<Recipe> recipes = null;  
		try
    	{
        	tx = session.beginTransaction();
        	String hqlFilter = "";
        	switch(filter)
    		{
    			case PRICE:
    				log.debug(String.format("Found filter: PRICE"));
    				hqlFilter = String.format("where _recipe.total_price <= %s", filterValue);
    				break;
    			case ALLERGY:
    				log.debug(String.format("Found filter: ALLERGY"));
    				// TODO: To be implemented. See com.horizon.cookeat.entities.allergene and tags
//    				hqlFilter = String.format("where _recipe.total_price <= %s", filterValue);
    				break;
    			case DIET:
    				log.debug(String.format("Found filter: DIET"));
    				// TODO: To be implemented. See com.horizon.cookeat.entities.allergene and tags
    				break;
    			default:
    				// No filter, return results unfiltered //
    				// Could return error/null, but we'd rather give the user some recipes in case the code is buggy
    				break;
    		}        	
        	String hql = String.format("select _recipe from Recipe as _recipe %s", hqlFilter);
        	@SuppressWarnings("unchecked")
			Query<Recipe> q = session.createQuery(hql);
        	recipes = q.list();
    	}
        catch (RuntimeException e) {
		    if (tx != null) tx.rollback();
		    throw e;
		}
		finally { session.close(); }
    	return recipes;
	}
	
	public int computeTotalPrice(int recipe_id)
	{
		int total_price = 0;
        for(Ingredient ing : getIngredients(recipe_id))
        {
        	total_price += ing.getPrice();
        }
        return total_price;
	}
	
	public List<R_Ingredient> getIngredients(int recipe_id)
	{
		Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<R_Ingredient> ingredients = null;  
        try
    	{
        	tx = session.beginTransaction();
        	String hql = String.format("SELECT NEW com.horizon.cookeat.entities.R_Ingredient(ingredient.id, ingredient.designation, ingredient.unit, ingredient.price_per_unit, recipe_ingredient.quantity) FROM Ingredient ingredient INNER JOIN FETCH RecipeIngredient recipe_ingredient ON ingredient.id = recipe_ingredient.ingredient.id WHERE recipe_ingredient.recipe.id = %s", recipe_id);
			TypedQuery<R_Ingredient> q = session.createQuery(hql, R_Ingredient.class);
        	ingredients = q.getResultList();
    	}
        catch (RuntimeException e) {
		    if (tx != null) tx.rollback();
		    throw e;
		}
		finally { session.close(); }
    	return ingredients;
	}


	public List<Etape> getSteps(Integer recipe_id) {
		Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<Etape> steps = null;  
        try
    	{
        	tx = session.beginTransaction();
        	String hql = String.format("SELECT etape FROM Etape AS etape WHERE etape.recipe.id = %s ORDER BY etape.step_order ASC", recipe_id);
			TypedQuery<Etape> q = session.createQuery(hql, Etape.class);
        	steps = q.getResultList();
    	}
        catch (RuntimeException e) {
		    if (tx != null) tx.rollback();
		    throw e;
		}
		finally { session.close(); }
    	return steps;
	}
	
	public List<Gallery> getGallery(Integer recipe_id) {
		Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<Gallery> gallery = null;  
        try
    	{
        	tx = session.beginTransaction();
        	String hql = String.format("SELECT gallery FROM Gallery AS gallery WHERE gallery.recipe.id = %s ORDER BY gallery.id ASC", recipe_id);
			TypedQuery<Gallery> q = session.createQuery(hql, Gallery.class);
        	gallery = q.getResultList();
    	}
        catch (RuntimeException e) {
		    if (tx != null) tx.rollback();
		    throw e;
		}
		finally { session.close(); }
    	return gallery;
	}
	
	public List<JsonObject> toListJson(List<Recipe> allRecipes)
	{
		List<JsonObject> recipes = new ArrayList<JsonObject>();
		for(Recipe r : allRecipes)
		{
			JsonObject jRecipe = Utils.gson.fromJson(r.toString(), JsonObject.class);
			List<R_Ingredient> _ri = getIngredients(r.getId());
			for(R_Ingredient ri : _ri)
			{
				System.out.println("--" + ri.getDesignation());
				System.out.println("Ingredients:" + ri);
			}
			jRecipe.add("ingredients", Utils.gson.toJsonTree(_ri, new TypeToken<List<R_Ingredient>>(){}.getType()));
//			jRecipe.add("list_gallery", Utils.gson.toJsonTree(getGallery(r.getId()), new TypeToken<List<Gallery>>(){}.getType()));
//			jRecipe.add("list_steps", Utils.gson.toJsonTree(getSteps(r.getId()), new TypeToken<List<Etape>>(){}.getType()));
			recipes.add(jRecipe);
		}
		return recipes;
	}
	
	
	
}
