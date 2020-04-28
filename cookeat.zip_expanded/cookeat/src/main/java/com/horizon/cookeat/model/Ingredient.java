package com.horizon.cookeat.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Ingredient")
public class Ingredient {
	
	
	// ATTRIBUTES //
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ingredient_generator")
	@SequenceGenerator(name="ingredient_generator", sequenceName = "ingredient_seq", initialValue = 200, allocationSize = 100)
	private int id;
	private String unit;
	private String designation;
	private int price_per_unit;
	
//	@ManyToMany(mappedBy = "list_ingredients")
//    private Set<Recipe> list_recipes = new HashSet<Recipe>();
	
	@OneToMany(
	        mappedBy = "ingredient",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
    )
    private Set<RecipeIngredient> recipes = new HashSet<>();
	
	// CONSTRUCTOR //
	
	public Ingredient(String unit, String designation, int ppu)
	{
		this.unit = unit;
		this.designation = designation;
		this.price_per_unit = ppu;
	}
	
	// GETTERS AND SETTERS //
	
	
	public void addRecipe(Recipe r)
	{
		RecipeIngredient join = new RecipeIngredient(r, this);
		recipes.add(join);
	}
	
	public void removeRecipe(Recipe r)
	{
	    for (Iterator<RecipeIngredient> iterator = recipes.iterator(); iterator.hasNext(); )
	    {
	        RecipeIngredient join = iterator.next();
	        if (join.getIngredient().equals(this) &&
	                join.getRecipe().equals(r)) {
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
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public int getPrice() {
		return price_per_unit;
	}
	public void setPrice(int price) {
		this.price_per_unit = price;
	}
	
	

}
