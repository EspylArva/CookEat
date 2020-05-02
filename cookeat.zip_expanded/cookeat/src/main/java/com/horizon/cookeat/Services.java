package com.horizon.cookeat;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.horizon.cookeat.model.Recipe;

@Service
public class Services {
	
	
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	public List<Recipe> fetchAllRecipes()
	{
		log.debug("Opening session...");
		System.out.println("Opening session");
        Session session = Utils.sessionFactory.openSession();
        Transaction tx = null;
         
        List<Recipe> recipes = new ArrayList<Recipe>();
        try
    	{
        	System.out.println("Transaction");
	        
        	tx = session.beginTransaction();
        	System.out.println("Lancement de la query");
	         
        	Query q = session.createQuery("select _recipe from Recipe _recipe");
	        System.out.println("Query recuperee");
	        
	        
	        recipes = q.list();
	        System.out.println(recipes.size() + " items");
	        
	        for(Recipe r : recipes)
	        {
	        	System.out.println(r.getDesignation());
	        }
	        
    	}
        catch (RuntimeException e) {
		    if (tx != null) tx.rollback();
		    throw e; // or display error message
		}
		finally {
		    session.close();
		}
        
    	return recipes;
	}
	
	public Recipe fetchRecipe(String recipe_name)
	{
		return null;
	}
	

}
