package com.horizon.cookeat;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;

import com.horizon.cookeat.model.Recipe;

@SuppressWarnings("deprecation")
public class Runner {
    
    @Test
    public void crud() {
    	try
    	{
	    	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	    	Utils.log.debug("Opening session...");
	        Session session = sessionFactory.openSession();
	        Utils.log.debug("Session opened");
	        create(session);
	        read(session);
	        
	        update(session);
	        read(session);
	        
	        delete(session);
	        read(session);
	         
	        session.close();
    	}
    	catch(Exception e) { Utils.log.debug("Crud: " + e.getMessage()); }
    }
     
    private void delete(Session session) {
    	try
    	{    		
    		Utils.log.debug("Deleting mondeo record...");
	        Recipe trucDegueu = (Recipe) session.get(Recipe.class, "3");
	         
	        session.beginTransaction();
	        session.delete(trucDegueu);
	        session.getTransaction().commit();
    	}
    	catch(Exception ex) { Utils.log.debug("Delete: " + ex.getMessage()); }
    }
     
    private void update(Session session) {
        try {	        	
        	Utils.log.debug("Updating tarte aux pommes price...");
	        Recipe tartopom = (Recipe) session.get(Recipe.class, "2");
	        tartopom.setTotal_price(2000);
	         
	        session.beginTransaction();
	        session.saveOrUpdate(tartopom);
	        session.getTransaction().commit();
        }
        catch (Exception e) { Utils.log.debug("Update: " +e.getMessage()); }
    }
 
    private void create(Session session) {
    	try
    	{
	    	Utils.log.debug("Creating car recipes...");
	        
	        Recipe patesCarbo = new Recipe();
	        patesCarbo.setId(1);
	        patesCarbo.setDesignation("Pates a la carbonara");
	        patesCarbo.setPrep_time(15);
	        patesCarbo.setTotal_price(500);
	        
	        Utils.log.debug("Created Pates a la carbonara");
	        
	        Recipe tarteAuxPomxml = new Recipe();
	        tarteAuxPomxml.setId(2);
	        tarteAuxPomxml.setDesignation("Tarte aux pommes");
	        tarteAuxPomxml.setPrep_time(90);
	        tarteAuxPomxml.setTotal_price(1500);
	        
	        Utils.log.debug("Created Tarte aux pommes");
	        
	         
	        session.beginTransaction();
	        session.save(patesCarbo);
	        session.save(tarteAuxPomxml);
	        session.getTransaction().commit();
    	}
    	catch(Exception e) { Utils.log.debug("Create: " + e.getMessage()); }
    }
     
    private void read(Session session) {
    	try
    	{
	        Query q = session.createQuery("select _recipe from Recipe _recipe");
	         
	        List<Recipe> recipes = q.list();
	        Utils.log.debug("Reading recipes in database...");
	        for (Recipe r : recipes) {
	        	Utils.log.debug(String.format("#%s - %s", r.getId(), r.getDesignation()));
	        }
    	}
    	catch(Exception e) {Utils.log.debug("Read: " + e.getMessage()); }
    }
}