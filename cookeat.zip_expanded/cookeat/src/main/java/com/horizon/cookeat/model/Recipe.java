package com.horizon.cookeat.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Recipe")
public class Recipe implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// ATTRIBUTES //
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recipe_generator")
	@SequenceGenerator(name="recipe_generator", sequenceName = "recipe_seq", initialValue = 100, allocationSize = 100)
	private int id;
	
	
	private String designation;
	private int prep_time;
	private int total_price;
	private String path_to_icon;
	

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(
			name = "recipe_ingredients",
			joinColumns = @JoinColumn(name = "recipe_id"),
			inverseJoinColumns = @JoinColumn(name = "ingredient_id")
	)
	private Set<Ingredient> list_ingredients = new HashSet<Ingredient>();
	
	// GETTERS AND SETTERS //
	
	public void addIngredient(Ingredient i)
	{
		list_ingredients.add(i);
	}
	
	public void removeIngredient(Ingredient i)
	{
		list_ingredients.remove(i);
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
