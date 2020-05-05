package com.horizon.cookeat.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="recipe")
public class Recipe implements Serializable
{
	// ATTRIBUTES //
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recipe_generator")
	@SequenceGenerator(name="recipe_generator", sequenceName = "recipe_seq", initialValue = 100, allocationSize = 100)
	private int id;
	private String designation;
	private int prep_time;
	private int total_price;
	private String path_to_icon;
	
	@OneToMany(
	        mappedBy = "recipe",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
    )
    private Set<RecipeIngredient> list_ingredients = new HashSet<>();
	
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(
			name = "recipe_equipment",
			joinColumns = @JoinColumn(name = "recipe_id"),
			inverseJoinColumns = @JoinColumn(name = "equipment_id")
	)
	private Set<Equipment> list_equipments = new HashSet<Equipment>();
	
	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
//	@MapKey(name = "order")
	private Set<Etape> list_steps = new HashSet<Etape>();
	
	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Gallery> list_gallery = new HashSet<Gallery>();
	
	// METHODS //
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recipe )) return false;
        return id == ((Recipe)o).getId();
    }
 
    @Override
    public int hashCode() {
        return id;
    }
	
	// CONSTRUCTOR //
    public Recipe(){    }
    
	public Recipe(String designation, int prep_time, int total_price, String path_icon)
	{
		this.designation = designation;
		this.prep_time = prep_time;
		this.total_price = total_price;
		this.path_to_icon = path_icon;
	}
	
	public Recipe(String designation, int prep_time, int total_price, String path_icon, List<Ingredient> ingredients)
	{
		this.designation = designation;
		this.prep_time = prep_time;
		this.total_price = total_price;
		this.path_to_icon = path_icon;
		
		for(Ingredient ingredient : ingredients)
		{
			addIngredient(ingredient);
		}
	}
	
	
	// GETTERS AND SETTERS //
	public void addGallery(Gallery g)
	{
		list_gallery.add(g);
		g.setRecipe(this);
	}
	public void removeGallery(Gallery g)
	{
		g.setRecipe(null);
		list_gallery.remove(g);
	}
	
	public void addStep(Etape s)
	{
		s.setRecipe(this);
		list_steps.add(s);
//		list_steps.put(s.getOrder(), s);
	}
	
	public void removeStep(Etape s)
	{
		s.setRecipe(null);
		list_steps.remove(s);
//		list_steps.remove(s.getOrder());
	}
	
	public void addEquipment(Equipment equipment) {
		list_equipments.add(equipment);
	}
	
	public void removeEquipment(Equipment equipment)
	{
		list_equipments.remove(equipment);
	}
	
	public void addIngredient(Ingredient i)
	{
		RecipeIngredient join = new RecipeIngredient(this, i);
		list_ingredients.add(join);
	}
	
	public void removeIngredient(Ingredient i)
	{
	    for (Iterator<RecipeIngredient> iterator = list_ingredients.iterator(); iterator.hasNext(); )
	    {
	        RecipeIngredient join = iterator.next();
	        if (join.getRecipe().equals(this) &&
	                join.getIngredient().equals(i)) {
	            iterator.remove();
	            join.setRecipe(null);
	            join.setIngredient(null);
	        }
	    }
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public int getPrep_time() {
		return prep_time;
	}
	public void setPrep_time(int prep_time) {
		this.prep_time = prep_time;
	}
	public int getTotal_price() {
		return total_price;
	}
	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}
	public String getPath_to_icon() {
		return path_to_icon;
	}
	public void setPath_to_icon(String path_to_icon) {
		this.path_to_icon = path_to_icon;
	}
	
	

}
