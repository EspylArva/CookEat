package com.horizon.cookeat.dao;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.horizon.cookeat.entities.Recipe;

@Component
public class RecipeDaoImplementation implements RecipeDao{
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	@Override
	public List<Recipe> getAllRecipes() {
		Session session = entityManagerFactory.unwrap(SessionFactory.class).openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
	    CriteriaQuery<Recipe> cq = cb.createQuery(Recipe.class);
	    Root<Recipe> rootEntry = cq.from(Recipe.class);
	    CriteriaQuery<Recipe> all = cq.select(rootEntry);
	 
	    TypedQuery<Recipe> allQuery = session.createQuery(all);
	    return allQuery.getResultList();
//		return criteria.list();
	}

	@Override
	public Recipe getRecipe(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Recipe getRecipe(String description) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Recipe addRecipe(Recipe recipe) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateRecipe(int id, Recipe recipe) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRecipe(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteRecipe(String description) {
		// TODO Auto-generated method stub
		
	}
	

}
