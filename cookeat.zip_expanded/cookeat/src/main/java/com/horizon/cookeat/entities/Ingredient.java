	package com.horizon.cookeat.entities;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ingredient")
public class Ingredient {
	
	
	// ATTRIBUTES //
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ingredient_generator")
	@SequenceGenerator(name="ingredient_generator", sequenceName = "ingredient_seq", initialValue = 200, allocationSize = 100)
	private int id;
	private String unit;
	private String designation;
	private int price_per_unit;
	
	@OneToMany(
	        mappedBy = "ingredient",
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
    )
    private Set<RecipeIngredient> list_recipes = new HashSet<>();
	
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(
			name = "ingredient_allergene",
			joinColumns = @JoinColumn(name = "ingredient_id"),
			inverseJoinColumns = @JoinColumn(name = "allergene_id")
	)
	private Set<Allergene> list_allergenes = new HashSet<Allergene>();
	
	// METHODS //
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ingredient )) return false;
        return id == ((Ingredient) o).getId();
    }
 
    @Override
    public int hashCode() {
        return id;
    }
	
	// CONSTRUCTOR //
    public Ingredient() {}
    
	public Ingredient(String unit, String designation, int ppu)
	{
		this.unit = unit;
		this.designation = designation;
		this.price_per_unit = ppu;
	}
	
	// GETTERS AND SETTERS //
	public void addAllergene(Allergene a)
	{
		list_allergenes.add(a);
	}
	public void removeAllergene(Allergene a)
	{
		list_allergenes.remove(a);
	}
	public void addRecipe(Recipe r)
	{
		RecipeIngredient join = new RecipeIngredient(r, this);
		list_recipes.add(join);
	}
	public void removeRecipe(Recipe r)
	{
	    for (Iterator<RecipeIngredient> iterator = list_recipes.iterator(); iterator.hasNext(); )
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
