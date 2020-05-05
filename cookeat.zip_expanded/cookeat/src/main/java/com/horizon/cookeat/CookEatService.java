package com.horizon.cookeat;

import java.util.List;

//import javax.persistence.TypedQuery;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.horizon.cookeat.dao.RecipeDao;
import com.horizon.cookeat.entities.Recipe;

@Service
public class CookEatService {
		
//	@Autowired
//	private RecipeDao recipeDao;
	
	private final Logger log = Logger.getLogger(this.getClass());
	
	
	public List<Recipe> fetchAllRecipes()
	{
        Session session = Utils.sessionFactory.openSession();
        Transaction tx = null;
        List<Recipe> recipes = null;  
        try
    	{
        	tx = session.beginTransaction();
        	String hql = "select _recipe from Recipe as _recipe";
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
//		return recipeDao.getAllRecipes();
	}
	

	public List<Recipe> fetchRecipe(String recipe_name)
	{
        Session session = Utils.sessionFactory.openSession();
        Transaction tx = null;
        List<Recipe> recipes = null;  
        try
    	{
        	tx = session.beginTransaction();
        	String hql = String.format("select _recipe from Recipe _recipe where _recipe.designation='%s'", recipe_name);
//        	String hql = "select _recipe from Recipe as _recipe";
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
	
//	public List<Recipe> fetchPool(int pageNumber)
//	{
//		Session session = Utils.sessionFactory.openSession();
//		CriteriaBuilder cb = session.getCriteriaBuilder();
//	    CriteriaQuery<Recipe> cq = cb.createQuery(Recipe.class);
//	    Root<Recipe> rootEntry = cq.from(Recipe.class);
//	    CriteriaQuery<Recipe> all = cq.select(rootEntry);
//	 
//	    TypedQuery<Recipe> allQuery = session.createQuery(all);
//	    return allQuery.getResultList();
//	}
	public List<Recipe> fetchPool(int pageNumber)
	{
		int pageSize = 10;
        Session session = Utils.sessionFactory.openSession();
        Transaction tx = null;
        List<Recipe> recipes = null;  
        try
    	{
        	tx = session.beginTransaction();
        	String hql = "select _recipe from Recipe as _recipe";
        	@SuppressWarnings("unchecked")
			Query<Recipe> q = session.createQuery(hql);
    		q.setFirstResult((pageNumber - 1) * pageSize);
            q.setMaxResults(pageSize);
        	recipes = q.list();
        	for(Object o : recipes)
        	{
        		System.out.println(o.toString());
        	}
    	}
        catch (RuntimeException e) {
		    if (tx != null) tx.rollback();
		    throw e;
		}
		finally { session.close(); }
    	return recipes;
	}

	public List<Recipe> fetchAllRecipesFilteredBy(Filter filter, int filterValue)
	{
		Session session = Utils.sessionFactory.openSession();
        Transaction tx = null;
        List<Recipe> recipes = null;  
		try
    	{
        	tx = session.beginTransaction();
        	String hqlFilter = "";
        	switch(filter)
    		{
    			case PRICE:
    				break;
    			case ALLERGY:
    				break;
    			case DIET:
    				break;
    			default:
    				// No filter, return error //
    				break;
    		}
        	
        	String hql = String.format("select _recipe from Recipe as _recipe", hqlFilter);
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
	
}
