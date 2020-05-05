package com.horizon.cookeat;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
//import javax.persistence.NamedNativeQuery;
import javax.persistence.Persistence;
//import javax.persistence.TypedQuery;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
//import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.horizon.cookeat.model.Recipe;

@Service
public class Services {
	
//	private static EntityManager em;
//    private static EntityManagerFactory emFactory = Persistence.createEntityManagerFactory("jpa-h2");
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
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
//	{
//		Recipe recipe = null;
//		Session session = Utils.sessionFactory.openSession();
//        Transaction tx = null;
//        try
//        {
////        	
//        	String sql = String.format("SELECT rec FROM Recipe rec WHERE rec.designation = '%s'", recipe_name);
//        	tx = session.getTransaction();
////        	@SuppressWarnings("unchecked")
////			Query<Recipe> q = session.createQuery(hql, Recipe.class);
//			recipe = (Recipe)session.createQuery(sql).getSingleResult();
////        	Query<Recipe> q = session.createNativeQuery("SELECT RECIPE.* FROM RECIPE AS RECIPE WHERE DESIGNATION = ?", Recipe.class);
////        	q.setParameter(1, recipe_name);
//        	
////        	recipe = (q.getSingleResult());
//        }
//        catch(RuntimeException e){ if(tx != null) { tx.rollback(); } throw e; }
//        finally { session.close(); }
//        return recipe;
//	}
	
	
	public List<Recipe> fetchPool(int pageNumber)
	{
		Session session = Utils.sessionFactory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
	    CriteriaQuery<Recipe> cq = cb.createQuery(Recipe.class);
	    Root<Recipe> rootEntry = cq.from(Recipe.class);
	    CriteriaQuery<Recipe> all = cq.select(rootEntry);
	 
	    TypedQuery<Recipe> allQuery = session.createQuery(all);
	    return allQuery.getResultList();
	}
//	public List<Recipe> fetchPool(int pageNumber)
//	{
//		int pageSize = 10;
//        Session session = Utils.sessionFactory.openSession();
//        Transaction tx = null;
//        List<Recipe> recipes = null;  
//        try
//    	{
//        	tx = session.beginTransaction();
//        	String hql = "select _recipe from Recipe as _recipe";
//        	@SuppressWarnings("unchecked")
//			Query<Recipe> q = session.createQuery(hql);
//    		q.setFirstResult((pageNumber - 1) * pageSize);
//            q.setMaxResults(pageSize);
//        	recipes = q.list();
//        	for(Object o : recipes)
//        	{
//        		System.out.println(o.toString());
//        	}
//    	}
//        catch (RuntimeException e) {
//		    if (tx != null) tx.rollback();
//		    throw e;
//		}
//		finally { session.close(); }
//        
////        for(Recipe r : currentPool)
////        {
////        	if(recipes.contains(r))
////        	{
////        		recipes.remove(r);
////        	}
////        }
//    	return recipes;
//	}
	
	
	public Recipe test()
	{
		Recipe recipe = null;
		try {
			recipe = Recipe.class.newInstance();
			recipe.setDesignation("NOUVELLE RECETTE");
		}
		catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return recipe;
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
