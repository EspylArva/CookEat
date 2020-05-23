package com.horizon.cookeat;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.assertj.core.util.Arrays;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.horizon.cookeat.entities.Equipment;
import com.horizon.cookeat.entities.Etape;
import com.horizon.cookeat.entities.Gallery;
import com.horizon.cookeat.entities.Ingredient;
import com.horizon.cookeat.entities.R_Ingredient;
import com.horizon.cookeat.entities.Recipe;
import com.horizon.cookeat.service.Utils;


public class Runner {
	
	private final Logger log = Logger.getLogger(this.getClass());
//	private EntityManagerFactory entityManagerFactory;
	private SessionFactory sessionFactory = new Configuration().configure("generator.cfg.xml").buildSessionFactory();
	
	@Test
	public void testJSON() throws IOException
	{
		Gson gson = Utils.gson;
		Reader reader = Files.newBufferedReader(Paths.get("src/main/resources/recipes.json").toAbsolutePath());
		JsonArray je = gson.fromJson(reader, JsonArray.class);
		Session session = sessionFactory.openSession();
    	Transaction tx = null;
    	tx = session.beginTransaction();
    	try
    	{
    		for(JsonElement subEle : je)
    		{			
    			JsonObject json_recipe = subEle.getAsJsonObject();
    			
    			JsonArray list_gallery = json_recipe.get("list_gallery").getAsJsonArray();
    			Set<Gallery> recipe_gallery = gson.fromJson(list_gallery, new TypeToken<Set<Gallery>>() {}.getType());
    			for(Gallery image : recipe_gallery){ 
    				session.save(image); 
				}
    			
    			JsonArray list_steps = json_recipe.get("list_steps").getAsJsonArray();
    			Set<Etape> recipe_steps = gson.fromJson(list_steps, new TypeToken<Set<Etape>>() {}.getType());
    			for(Etape step : recipe_steps){ 
    				session.save(step);
				}

    			
    			JsonArray list_ingredients = json_recipe.get("ingredients").getAsJsonArray();
    			HashSet<R_Ingredient> recipe_ingredients = gson.fromJson(list_ingredients, new TypeToken<HashSet<R_Ingredient>>() {}.getType());
    			for(R_Ingredient ing : recipe_ingredients){ 
    				System.out.println(ing.getDesignation());
    				session.saveOrUpdate(new Ingredient(ing.getUnit(),ing.getDesignation(), ing.getPrice_per_unit()));
				}
    			
    			
    			Recipe recipe = new Recipe(
    					json_recipe.get("id").getAsInt(),
    					json_recipe.get("designation").getAsString(),
    					json_recipe.get("prep_time").getAsFloat(),
    					json_recipe.get("total_price").getAsFloat(),
    					json_recipe.get("start_season").getAsInt(),
    					json_recipe.get("end_season").getAsInt(),
    					recipe_ingredients
    					);
    			
    			recipe.addGallery(recipe_gallery);
    			recipe.addStep(recipe_steps);

    			session.save(recipe);
    			log.debug("SAVED " + recipe.getDesignation());
    		}
    		System.out.println("Commiting recipes");
	        tx.commit();
    	}
    	catch (RuntimeException e) {
    	    if (tx != null) tx.rollback();
    	    throw e; // or display error message
    	}
    	finally {
    	    session.close();
    	}
    	
    	read();
	}
	
//    @Test
    public void crud() {    	
    	try
    	{
    		// DB at: 					http://127.0.0.1:59843/browser/#
    		// Create template data:	https://www.mockaroo.com/
	        create();
	        read();
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
    	Session session = sessionFactory.openSession();
    	Transaction tx = null;
    	try
    	{
    		tx = session.beginTransaction();

    		log.debug("Creating Ingredients");
	        Ingredient JIMMYon = new Ingredient(200, "kilogramme;kg", "lardon", 700);
    		Ingredient patrick = new Ingredient(201, "kilogramme;kg", "pate", 500);
    		Ingredient pomXML = new Ingredient(202, "kilogramme;kg", "pomme", 600);

    		log.debug("Creating Recipes");
	        Recipe patesCarbo = new Recipe("Pates a la carbonara", 15, 500);
	        Recipe tarteAuxPomXML = new Recipe("Tarte aux pommes", 90, 1500);
	        Recipe trucDegueu = new Recipe("Ratatouille", 120, 1200);

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
        	
	        
	        patesCarbo.addIngredient(patrick, 2);
	        patesCarbo.addIngredient(JIMMYon, 1);
	        patesCarbo.addEquipment(dominatrixTool);
	        patesCarbo.addEquipment(sacreCULyere);

	        patesCarbo.addStep(s1);patesCarbo.addStep(s2);patesCarbo.addStep(s3);
	        patesCarbo.addGallery(g1);patesCarbo.addGallery(g2);patesCarbo.addGallery(g3);
	        
	        tarteAuxPomXML.addIngredient(pomXML,5);
	        tarteAuxPomXML.addEquipment(sacreCULyere);
	        
	        trucDegueu.addIngredient(JIMMYon, 3);
	        trucDegueu.addEquipment(sacreCULyere);

	        session.save(patesCarbo);
	        session.save(tarteAuxPomXML);
	        session.save(trucDegueu);
	        session.save(new Recipe("Fletcher Bryant",29,35));
	        session.save(new Recipe("Kieran Vargas",58,38));
	        session.save(new Recipe("Alan Oliver",127,22));
	        session.save(new Recipe("Andrew Molina",189,32));
	        session.save(new Recipe("Richard Silva",51,9));
	        session.save(new Recipe("Oleg Ramirez",98,4));
	        session.save(new Recipe("Amal Morton",26,36));
	        session.save(new Recipe("Reece Mullins",152,30));
	        session.save(new Recipe("Sawyer Montoya",188,38));
	        session.save(new Recipe("Brent Summers",194,13));
	        session.save(new Recipe("Amos Oliver",49,6));
	        session.save(new Recipe("Phillip Leonard",35,25));
	        session.save(new Recipe("Gabriel Franklin",96,46));
	        session.save(new Recipe("Emmanuel Clay",28,16));
	        session.save(new Recipe("Nasim Erickson",137,50));
	        session.save(new Recipe("Mark Ford",2,45));
	        session.save(new Recipe("Zane Gillespie",185,48));
	        session.save(new Recipe("Lane Bennett",179,30));
	        session.save(new Recipe("Kareem Mcbride",65,34));
	        session.save(new Recipe("Kenyon Albert",197,42));
	        session.save(new Recipe("Emmanuel Greene",24,20));
	        session.save(new Recipe("Cooper Hart",196,36));
	        session.save(new Recipe("Chandler Sanchez",149,32));
	        session.save(new Recipe("Benjamin York",162,15));
	        session.save(new Recipe("Dorian Lane",13,1));
	        session.save(new Recipe("Dustin Sullivan",184,10));
	        session.save(new Recipe("Erasmus Rice",29,4));
	        session.save(new Recipe("Bruno Hahn",163,1));
	        session.save(new Recipe("Steven Townsend",63,45));
	        session.save(new Recipe("Trevor Shaffer",197,9));
	        session.save(new Recipe("Jonah Rasmussen",145,18));
	        session.save(new Recipe("Brody Richard",55,26));
	        session.save(new Recipe("Edan Ortega",2,4));
	        session.save(new Recipe("Nicholas Caldwell",52,17));
	        session.save(new Recipe("Xavier Meadows",178,21));
	        session.save(new Recipe("Harding Herman",112,20));
	        session.save(new Recipe("Driscoll Moss",100,44));
	        session.save(new Recipe("Malik Howe",148,7));
	        session.save(new Recipe("Elijah Peterson",197,14));
	        session.save(new Recipe("Dillon Bonner",83,13));
	        session.save(new Recipe("Brian Galloway",176,7));
	        session.save(new Recipe("Jeremy Ortega",92,20));
	        session.save(new Recipe("Kamal Haney",69,27));
	        session.save(new Recipe("Micah Gutierrez",88,39));
	        session.save(new Recipe("Aristotle Mathews",18,48));
	        session.save(new Recipe("Stephen Douglas",85,13));
	        session.save(new Recipe("Blake Fitzgerald",63,6));
	        session.save(new Recipe("Nissim Mcneil",174,34));
	        session.save(new Recipe("Cadman Walter",99,4));
	        session.save(new Recipe("Chandler Roberson",39,21));
	        session.save(new Recipe("Nash Walker",105,24));
	        session.save(new Recipe("Lawrence Howell",135,12));
	        session.save(new Recipe("Neil Farrell",113,40));
	        session.save(new Recipe("Murphy Emerson",27,8));
	        session.save(new Recipe("Merritt Mendez",143,47));
	        session.save(new Recipe("Porter Byrd",79,1));
	        session.save(new Recipe("Chadwick Aguirre",148,31));
	        session.save(new Recipe("Tanek Wilder",163,16));
	        session.save(new Recipe("Connor Miles",19,16));
	        session.save(new Recipe("Fritz Camacho",54,40));
	        session.save(new Recipe("Castor Hinton",191,41));
	        session.save(new Recipe("Kane Gibbs",86,30));
	        session.save(new Recipe("Brady Carney",196,21));
	        session.save(new Recipe("Fritz Ryan",120,40));
	        session.save(new Recipe("Timothy Kane",198,40));
	        session.save(new Recipe("Richard Valentine",13,15));
	        session.save(new Recipe("Abel Pickett",63,5));
	        session.save(new Recipe("Quamar Warren",144,31));
	        session.save(new Recipe("Giacomo Lindsey",130,5));
	        session.save(new Recipe("Yasir Mcmahon",154,10));
	        session.save(new Recipe("Vincent Leach",38,30));
	        session.save(new Recipe("Martin Strong",163,36));
	        session.save(new Recipe("Baker Koch",79,17));
	        session.save(new Recipe("Armando Gardner",103,29));
	        session.save(new Recipe("Jermaine Bass",112,43));
	        session.save(new Recipe("Prescott Nash",104,36));
	        session.save(new Recipe("Ishmael Luna",125,30));
	        session.save(new Recipe("Grant Bruce",93,3));
	        session.save(new Recipe("Gary Hines",195,8));
	        session.save(new Recipe("Gavin Manning",147,26));
	        session.save(new Recipe("Abdul Cain",78,2));
	        session.save(new Recipe("Harding Middleton",22,38));
	        session.save(new Recipe("Baxter Dunn",58,39));
	        session.save(new Recipe("Yoshio Mcpherson",21,22));
	        session.save(new Recipe("Tanek Miles",129,11));
	        session.save(new Recipe("Bernard Dodson",187,10));
	        session.save(new Recipe("Hunter Maldonado",93,19));
	        session.save(new Recipe("Owen Herrera",188,44));
	        session.save(new Recipe("Kelly Shields",34,45));
	        session.save(new Recipe("Tyrone Johnson",149,14));
	        session.save(new Recipe("Bruno Grimes",41,10));
	        session.save(new Recipe("Price Vega",130,29));
	        session.save(new Recipe("Mason Buckley",1,14));
	        session.save(new Recipe("Gage Hudson",84,12));
	        session.save(new Recipe("Salvador Herring",87,3));
	        session.save(new Recipe("Steel Brewer",143,50));
	        session.save(new Recipe("Elton Pena",195,17));
    		
	        session.save(JIMMYon);
	        session.save(pomXML);
	        session.save(patrick);

	        
	        session.save(sacreCULyere);
	        session.save(dominatrixTool);
	        
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
    	Session session = sessionFactory.openSession();
    	Transaction tx = null;
    	try
    	{	
    		tx = session.beginTransaction();
	        TypedQuery<Recipe> q = session.createQuery("select _recipe from Recipe _recipe", Recipe.class);
	        List<Recipe> recipes = q.getResultList();
	        log.debug("Reading recipes in database...");
	        for (Recipe r : recipes) {
	        	log.debug(String.format("#%s - %s :: takes %s minutes, costs %s €",
	        			r.getId(), r.getDesignation(), r.getPrep_time(), (r.getTotal_price()/100)));
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
