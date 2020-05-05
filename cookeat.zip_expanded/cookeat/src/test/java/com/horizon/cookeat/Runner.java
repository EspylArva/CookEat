package com.horizon.cookeat;

import java.util.List;

import javax.persistence.EntityManagerFactory;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;

import com.horizon.cookeat.entities.Equipment;
import com.horizon.cookeat.entities.Etape;
import com.horizon.cookeat.entities.Gallery;
import com.horizon.cookeat.entities.Ingredient;
import com.horizon.cookeat.entities.Recipe;


public class Runner {
	
	private final Logger log = Logger.getLogger(this.getClass());
//	private EntityManagerFactory entityManagerFactory;
	private SessionFactory sessionFactory = new Configuration().configure("generator.cfg.xml").buildSessionFactory();
	
    @Test
    public void crud() {
    	
    	try
    	{
    		// DB at: 					http://127.0.0.1:59843/browser/#
    		// Create template data:	https://www.mockaroo.com/
	    	log.debug("Opening session...");
	        Session session = sessionFactory.openSession();
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
    	Session session = sessionFactory.openSession();
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
	        	        
	        session.save(new Recipe("Fletcher Bryant",29,35,"925-5526 Eu, Av."));
	        session.save(new Recipe("Kieran Vargas",58,38,"5681 Fusce Rd."));
	        session.save(new Recipe("Alan Oliver",127,22,"P.O. Box 136, 2655 Tellus St."));
	        session.save(new Recipe("Andrew Molina",189,32,"P.O. Box 824, 8026 Nulla Rd."));
	        session.save(new Recipe("Richard Silva",51,9,"968-6245 Semper St."));
	        session.save(new Recipe("Oleg Ramirez",98,4,"368-6032 Tempor St."));
	        session.save(new Recipe("Amal Morton",26,36,"P.O. Box 717, 9706 Sit Road"));
	        session.save(new Recipe("Reece Mullins",152,30,"731 Pede Street"));
	        session.save(new Recipe("Sawyer Montoya",188,38,"271-9647 Lacus. St."));
	        session.save(new Recipe("Brent Summers",194,13,"P.O. Box 758, 4175 Dictum. Avenue"));
	        session.save(new Recipe("Amos Oliver",49,6,"310-9504 Aenean St."));
	        session.save(new Recipe("Phillip Leonard",35,25,"P.O. Box 567, 726 Orci. Av."));
	        session.save(new Recipe("Gabriel Franklin",96,46,"967-2436 Montes, Ave"));
	        session.save(new Recipe("Emmanuel Clay",28,16,"8098 Eu Ave"));
	        session.save(new Recipe("Nasim Erickson",137,50,"8944 Dui Av."));
	        session.save(new Recipe("Mark Ford",2,45,"P.O. Box 832, 5570 Eleifend Ave"));
	        session.save(new Recipe("Zane Gillespie",185,48,"6278 Fermentum Avenue"));
	        session.save(new Recipe("Lane Bennett",179,30,"Ap #679-8244 Nulla Ave"));
	        session.save(new Recipe("Kareem Mcbride",65,34,"Ap #753-7476 A, St."));
	        session.save(new Recipe("Kenyon Albert",197,42,"5430 Sapien, Avenue"));
	        session.save(new Recipe("Emmanuel Greene",24,20,"7931 Vel Rd."));
	        session.save(new Recipe("Cooper Hart",196,36,"Ap #547-397 Est, St."));
	        session.save(new Recipe("Chandler Sanchez",149,32,"P.O. Box 987, 8157 Dictum St."));
	        session.save(new Recipe("Benjamin York",162,15,"P.O. Box 915, 8295 Mus. Road"));
	        session.save(new Recipe("Dorian Lane",13,1,"P.O. Box 596, 880 Sagittis Avenue"));
	        session.save(new Recipe("Dustin Sullivan",184,10,"727-6554 Sed, Avenue"));
	        session.save(new Recipe("Erasmus Rice",29,4,"Ap #188-5438 Quisque Rd."));
	        session.save(new Recipe("Bruno Hahn",163,1,"Ap #273-8372 Mi, Av."));
	        session.save(new Recipe("Steven Townsend",63,45,"734-2540 Non St."));
	        session.save(new Recipe("Trevor Shaffer",197,9,"Ap #891-2021 Sit Road"));
	        session.save(new Recipe("Jonah Rasmussen",145,18,"5260 Sit Ave"));
	        session.save(new Recipe("Brody Richard",55,26,"Ap #637-9100 Aliquam Ave"));
	        session.save(new Recipe("Edan Ortega",2,4,"3528 Tellus Ave"));
	        session.save(new Recipe("Nicholas Caldwell",52,17,"Ap #974-6188 Litora Road"));
	        session.save(new Recipe("Xavier Meadows",178,21,"381-9536 Donec Street"));
	        session.save(new Recipe("Harding Herman",112,20,"Ap #244-9494 Orci. Ave"));
	        session.save(new Recipe("Driscoll Moss",100,44,"P.O. Box 100, 2563 Nonummy Avenue"));
	        session.save(new Recipe("Malik Howe",148,7,"864-8595 Imperdiet Rd."));
	        session.save(new Recipe("Elijah Peterson",197,14,"Ap #679-9145 In Rd."));
	        session.save(new Recipe("Dillon Bonner",83,13,"Ap #311-3166 Nullam Av."));
	        session.save(new Recipe("Brian Galloway",176,7,"Ap #228-9799 Sem Rd."));
	        session.save(new Recipe("Jeremy Ortega",92,20,"Ap #788-7889 Pede St."));
	        session.save(new Recipe("Kamal Haney",69,27,"8412 Tellus Rd."));
	        session.save(new Recipe("Micah Gutierrez",88,39,"Ap #498-495 Interdum. Street"));
	        session.save(new Recipe("Aristotle Mathews",18,48,"P.O. Box 864, 7590 Fermentum St."));
	        session.save(new Recipe("Stephen Douglas",85,13,"195-639 Quam. Ave"));
	        session.save(new Recipe("Blake Fitzgerald",63,6,"1451 Faucibus Av."));
	        session.save(new Recipe("Nissim Mcneil",174,34,"5759 Risus. Ave"));
	        session.save(new Recipe("Cadman Walter",99,4,"393-452 Donec Avenue"));
	        session.save(new Recipe("Chandler Roberson",39,21,"P.O. Box 825, 4699 Turpis Avenue"));
	        session.save(new Recipe("Nash Walker",105,24,"4152 Fusce St."));
	        session.save(new Recipe("Lawrence Howell",135,12,"244-5297 Nec, Ave"));
	        session.save(new Recipe("Neil Farrell",113,40,"2216 Eget, Street"));
	        session.save(new Recipe("Murphy Emerson",27,8,"286-3052 In Ave"));
	        session.save(new Recipe("Merritt Mendez",143,47,"501-5818 Lacus Avenue"));
	        session.save(new Recipe("Porter Byrd",79,1,"5745 Ut Avenue"));
	        session.save(new Recipe("Chadwick Aguirre",148,31,"651-1376 Erat St."));
	        session.save(new Recipe("Tanek Wilder",163,16,"7249 Non, St."));
	        session.save(new Recipe("Connor Miles",19,16,"P.O. Box 732, 6215 Eu Ave"));
	        session.save(new Recipe("Fritz Camacho",54,40,"668-4815 In Rd."));
	        session.save(new Recipe("Castor Hinton",191,41,"Ap #827-1042 Vel, Rd."));
	        session.save(new Recipe("Kane Gibbs",86,30,"P.O. Box 325, 8557 Mi, Rd."));
	        session.save(new Recipe("Brady Carney",196,21,"P.O. Box 709, 7073 Fringilla Road"));
	        session.save(new Recipe("Fritz Ryan",120,40,"Ap #204-5446 Ad Rd."));
	        session.save(new Recipe("Timothy Kane",198,40,"Ap #753-2204 Hendrerit Av."));
	        session.save(new Recipe("Richard Valentine",13,15,"6057 Lectus Street"));
	        session.save(new Recipe("Abel Pickett",63,5,"P.O. Box 369, 4070 Justo Avenue"));
	        session.save(new Recipe("Quamar Warren",144,31,"P.O. Box 486, 2313 Sed Ave"));
	        session.save(new Recipe("Giacomo Lindsey",130,5,"Ap #890-4723 Et Street"));
	        session.save(new Recipe("Yasir Mcmahon",154,10,"P.O. Box 638, 5937 Nunc Ave"));
	        session.save(new Recipe("Vincent Leach",38,30,"Ap #917-8677 Sem, Street"));
	        session.save(new Recipe("Martin Strong",163,36,"P.O. Box 555, 4314 Semper Avenue"));
	        session.save(new Recipe("Baker Koch",79,17,"1170 Ut, Rd."));
	        session.save(new Recipe("Armando Gardner",103,29,"Ap #806-2627 Vestibulum. Ave"));
	        session.save(new Recipe("Jermaine Bass",112,43,"Ap #476-1593 Enim Road"));
	        session.save(new Recipe("Prescott Nash",104,36,"Ap #136-8298 Mi, Av."));
	        session.save(new Recipe("Ishmael Luna",125,30,"Ap #864-3176 Nec Av."));
	        session.save(new Recipe("Grant Bruce",93,3,"P.O. Box 828, 2554 Semper St."));
	        session.save(new Recipe("Gary Hines",195,8,"P.O. Box 662, 8090 Vestibulum, Rd."));
	        session.save(new Recipe("Gavin Manning",147,26,"Ap #881-3026 Magna. Rd."));
	        session.save(new Recipe("Abdul Cain",78,2,"P.O. Box 221, 4435 Morbi St."));
	        session.save(new Recipe("Harding Middleton",22,38,"8833 Quisque St."));
	        session.save(new Recipe("Baxter Dunn",58,39,"Ap #911-1155 Netus Rd."));
	        session.save(new Recipe("Yoshio Mcpherson",21,22,"P.O. Box 803, 4365 Mollis Street"));
	        session.save(new Recipe("Tanek Miles",129,11,"932-1978 Porttitor St."));
	        session.save(new Recipe("Bernard Dodson",187,10,"8112 Sem Ave"));
	        session.save(new Recipe("Hunter Maldonado",93,19,"Ap #255-2315 Et Avenue"));
	        session.save(new Recipe("Owen Herrera",188,44,"Ap #873-4622 Morbi Av."));
	        session.save(new Recipe("Kelly Shields",34,45,"P.O. Box 335, 9697 Proin Road"));
	        session.save(new Recipe("Tyrone Johnson",149,14,"7548 Proin St."));
	        session.save(new Recipe("Bruno Grimes",41,10,"P.O. Box 686, 5063 Fringilla Road"));
	        session.save(new Recipe("Price Vega",130,29,"527-9604 Et Ave"));
	        session.save(new Recipe("Mason Buckley",1,14,"P.O. Box 943, 962 Sed, Street"));
	        session.save(new Recipe("Gage Hudson",84,12,"Ap #332-1683 Nunc Ave"));
	        session.save(new Recipe("Salvador Herring",87,3,"390-3931 Duis St."));
	        session.save(new Recipe("Steel Brewer",143,50,"Ap #157-2799 Massa. Rd."));
	        session.save(new Recipe("Elton Pena",195,17,"389-2104 Rutrum Street"));
	        session.save(new Recipe("Bruce Irwin",179,49,"3103 Ipsum St."));
	        session.save(new Recipe("Oscar Olson",49,43,"1582 Tempus St."));
	        session.save(new Recipe("Kaseem Hester",165,47,"P.O. Box 134, 8096 Donec Ave"));
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
    	Session session = sessionFactory.openSession();
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