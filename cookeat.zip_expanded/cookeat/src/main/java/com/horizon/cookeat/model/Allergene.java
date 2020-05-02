package com.horizon.cookeat.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="allergene")
public class Allergene {
	// ATTRIBUTES //
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "allergene_generator")
	@SequenceGenerator(name="allergene_generator", sequenceName = "allergene_seq", initialValue = 900, allocationSize = 100)
	private int id;
	private String description;
	
	@ManyToMany(mappedBy = "list_allergenes")
    private Set<Ingredient> list_ingredients = new HashSet<Ingredient>();
	
	// METHODS //
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Allergene )) return false;
        return id == ((Allergene) o).getId();
    }
 
    @Override
    public int hashCode() {
        return id;
    }	
	
	// CONSTRUCTOR //
    public Allergene() {}
    
	public Allergene(String description)
	{
		this.description = description;
	}
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
