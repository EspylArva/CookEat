package com.horizon.cookeat;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.horizon.cookeat.model.Equipment;
import com.horizon.cookeat.model.Etape;
import com.horizon.cookeat.model.Gallery;
import com.horizon.cookeat.model.Ingredient;
import com.horizon.cookeat.model.Recipe;


public class Runner {
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Test
    public void crud() {
    	
    	try
    	{
    		// DB at: 					http://127.0.0.1:59843/browser/#
    		// Create template data:	https://www.mockaroo.com/
//	    	SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	    	log.debug("Opening session...");
	        Session session = Utils.sessionFactory.openSession();
	        Transaction tx = null;
	        log.debug("Session opened");
	        log.debug("Creating recipes...");
	        create();
	        read();
	        
//	        log.debug("Updating tarte aux pommes price...");
//	        update(session);
//	        read(session);
	        
	        //log.debug("Deleting ratatouille record...");
	        //delete(session);
	        //read(session);
	         
//	        session.close();
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
 
    private void create() {
    	Session session = Utils.sessionFactory.openSession();
    	Transaction tx = null;
    	try
    	{
    		tx = session.beginTransaction();
//    		session.beginTransaction();

    		log.debug("Creating Ingredients");
	        Ingredient JIMMYon = new Ingredient("kilogramme;kg", "lardon", 700);
    		Ingredient patrick = new Ingredient("kilogramme;kg", "pate", 500);
    		Ingredient pomXML = new Ingredient("kilogramme;kg", "pomme", 600);

    		log.debug("Creating Recipes");
	        Recipe patesCarbo = new Recipe("Pates a la carbonara", 15, 500, null);
	        Recipe tarteAuxPomXML = new Recipe("Tarte aux pommes", 90, 1500, null);
	        Recipe trucDegueu = new Recipe("Ratatouille", 120, 1200, null);

	        log.debug("Creating Equipment");
	        Equipment sacreCULyere = new Equipment("Cuillere a soupe", null);
        	Equipment dominatrixTool = new Equipment("Fouet", null);
        	
        	log.debug("Creating Steps");
        	Etape s1 = new Etape(1, "Faire cuire les pates");
        	Etape s2 = new Etape(2, "Faire cuire les lardons");
        	Etape s3 = new Etape(3, "Melanger");

        	log.debug("Creating Gallery");
        	Gallery g1 = new Gallery("pathToImage1", "Pates a la carbonara facon Jules");
        	Gallery g2 = new Gallery("pathToImage2", "Pates a la carbonara facon TK");
        	Gallery g3 = new Gallery("pathToImage3", "Pates a la carbonara facon Baptiste");
        	
	        
	        patesCarbo.addIngredient(patrick);
	        patesCarbo.addIngredient(JIMMYon);
	        patesCarbo.addEquipment(dominatrixTool);
	        patesCarbo.addEquipment(sacreCULyere);

	        patesCarbo.addStep(s1);patesCarbo.addStep(s2);patesCarbo.addStep(s3);
	        patesCarbo.addGallery(g1);patesCarbo.addGallery(g2);patesCarbo.addGallery(g3);
	        
	        tarteAuxPomXML.addIngredient(pomXML);
	        tarteAuxPomXML.addEquipment(sacreCULyere);
	        
	        trucDegueu.addIngredient(JIMMYon);
	        trucDegueu.addEquipment(sacreCULyere);
	        	        
    		session.save(JIMMYon);
    		session.save(pomXML);
	        session.save(patrick);
	        
	        session.save(sacreCULyere);
	        session.save(dominatrixTool);
	        
	        session.save(patesCarbo);
	        session.save(tarteAuxPomXML);
	        session.save(trucDegueu);
	        
	        session.save(s1);
	        session.save(s2);
	        session.save(s3);
	        
	        session.save(g1);
	        session.save(g2);
	        session.save(g3);
	        
	        
	        tx.commit();
    	}
    	catch (RuntimeException e) {
    	    if (tx != null) tx.rollback();
    	    throw e; // or display error message
    	}
    	finally {
    	    session.close();
    	}
    }
     
    private void read() {
    	Session session = Utils.sessionFactory.openSession();
    	Transaction tx = null;
    	try
    	{	
    		tx = session.beginTransaction();
    		
	        Query q = session.createQuery("select _recipe from Recipe _recipe");
	         
	        List<Recipe> recipes = q.list();
	        log.debug("Reading recipes in database...");
	        for (Recipe r : recipes) {
	        	log.debug(String.format("#%s - %s :: takes %s minutes, costs %s €",
	        			r.getId(), r.getDesignation(), r.getPrep_time(), (r.getTotal_price()/100)
	        			));
	        }
    	}
    	catch (RuntimeException e) {
    	    if (tx != null) tx.rollback();
    	    throw e; // or display error message
    	}
    	finally {
    	    session.close();
    	}
    }
}