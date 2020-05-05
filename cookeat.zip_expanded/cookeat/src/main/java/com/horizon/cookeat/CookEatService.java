package com.horizon.cookeat;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;

import com.horizon.cookeat.entities.*;

@Service
public class CookEatService {
		
//	@Autowired
//	private RecipeDao recipeDao;
	
	private final Logger log = Logger.getLogger(this.getClass());
	private SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
//	private SessionFactory sessionFactory = Utils.serviceSessionFactory;
	
	public List<Recipe> fetchAllRecipes()
	{
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<Recipe> recipes = null;  
        try
    	{
        	tx = session.beginTransaction();
        	String hql = "Select _recipe from Recipe as _recipe";
//        	String hql = "from Recipe",
//        	@SuppressWarnings("unchecked")
			Query<Recipe> q = session.createQuery(hql, Recipe.class);
        	recipes = q.getResultList();
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
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<Recipe> recipes = null;  
        try
    	{
        	tx = session.beginTransaction();
        	String hql = String.format("select _recipe from Recipe _recipe where lower(_recipe.designation)=lower('%s')", recipe_name);
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
        Session session = sessionFactory.openSession();
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
	
}
