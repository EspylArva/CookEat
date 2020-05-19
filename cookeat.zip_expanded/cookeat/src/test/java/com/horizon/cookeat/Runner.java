package com.horizon.cookeat;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.hibernate.query.Query;

import com.horizon.cookeat.model.Recipe;

public class Runner {
	
	public final Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Test
    public void crud() {
    	
    	try
    	{
    		// DB at: 					http://127.0.0.1:59843/browser/#
    		// Create template data:	https://www.mockaroo.com/
	    	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	    	log.debug("Opening session...");
	        Session session = sessionFactory.openSession();
	        log.debug("Session opened");
	        try
	        {
	        	org.hibernate.query.Query q = session.createQuery("DROP TABLE IF EXISTS recipe CASCADE");
	        }
	        catch(Exception e) { log.debug("Reset: " + e.getMessage()); }
	        
	        log.debug("Creating recipes...");
	        create(session);
	        read(session);
	        
	        log.debug("Updating tarte aux pommes price...");
	        update(session);
	        read(session);
	        
	        log.debug("Deleting ratatouille record...");
	        delete(session);
	        read(session);
	         
	        session.close();
    	}
    	catch(Exception e) { log.debug("Crud: " + e.getMessage()); }
    }
     
    private void delete(Session session) {
    	try
    	{    		
	        Recipe trucDegueu = (Recipe) session.get(Recipe.class, 3);
	         
	        session.beginTransaction();
	        session.delete(trucDegueu);
	        session.getTransaction().commit();
    	}
    	catch(Exception ex) { log.debug("Delete: " + ex.getMessage()); }
    }
     
    private void update(Session session) {
        try {	        	
	        Recipe tartopom = (Recipe) session.get(Recipe.class, 2);
	        tartopom.setTotal_price(2000);
	         
	        session.beginTransaction();
	        session.saveOrUpdate(tartopom);
	        session.getTransaction().commit();
        }
        catch (Exception e) { log.debug("Update: " +e.getMessage()); }
    }
 
    private void create(Session session) {
    	try
    	{
	        
	        Recipe patesCarbo = new Recipe();
	        patesCarbo.setId(1);
	        patesCarbo.setDesignation("Pates a la carbonara");
	        patesCarbo.setPrep_time(15);
	        patesCarbo.setTotal_price(500);
	        
	        log.debug("Created Pates a la carbonara");
	        
	        Recipe tarteAuxPomxml = new Recipe();
	        tarteAuxPomxml.setId(2);
	        tarteAuxPomxml.setDesignation("Tarte aux pommes");
	        tarteAuxPomxml.setPrep_time(90);
	        tarteAuxPomxml.setTotal_price(1500);
	        
	        log.debug("Created Tarte aux pommes");
	        
	        Recipe ratatouille = new Recipe();
	        ratatouille.setId(3);
	        ratatouille.setDesignation("Ratatouille");
	        ratatouille.setPrep_time(120);
	        ratatouille.setTotal_price(1200);
	        
	        log.debug("Created Ratatouille");
	        
	         
	        session.beginTransaction();
	        
	        session.save(patesCarbo);
	        session.save(tarteAuxPomxml);
	        session.save(ratatouille);
	        
	        session.getTransaction().commit();
    	}
    	catch(Exception e) { Utils.log.debug("Create: " + e.getMessage()); }
    }
     
    private void read(Session session) {
    	try
    	{
	        Query q = session.createQuery("select _recipe from Recipe _recipe");
	         
	        List<Recipe> recipes = q.list();
	        log.debug("Reading recipes in database...");
	        for (Recipe r : recipes) {
	        	log.debug(String.format("#%s - %s :: takes %s minutes, costs %s €",
	        			r.getId(), r.getDesignation(), r.getPrep_time(), (r.getTotal_price()/100)
	        			));
	        }
    	}
    	catch(Exception e) {log.debug("Read: " + e.getMessage()); }
    }
}