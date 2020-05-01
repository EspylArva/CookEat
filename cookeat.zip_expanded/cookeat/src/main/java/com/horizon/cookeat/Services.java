package com.horizon.cookeat;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.horizon.cookeat.model.*;

@Service
public class Services {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	public List<Recipe> listOfRecipes()
	{
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
    	//log.debug("Opening session...");
        Session session = sessionFactory.openSession();
        
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
	        return recipes;
    	}
    	catch(Exception e) {log.debug("Read: " + e.getMessage()); }
        return null;
	}
	

}
