package com.horizon.cookeat.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;

@Entity
@Table(name="etape")
public class Etape {
	// ATTRIBUTES //
	@Expose
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "etape_generator")
	@SequenceGenerator(name="etape_generator", sequenceName = "etape_seq", initialValue = 1000, allocationSize=2)
	@Column(name="id", updatable = false, nullable = false)
	private int id;
	@Expose
	private int step_order;
	@Expose
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Recipe recipe;

	// METHODS //
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Etape )) return false;
        return id == ((Etape) o).getId();
    }
 
    @Override
    public int hashCode() {
        return id;
    }
	
	// CONSTRUCTOR //
    public Etape() {}
    
	public Etape(int step_order, String description)
	{
		this.step_order = step_order;
		this.description = description;
	}
	
	// GETTERS AND SETTERS //
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Recipe getRecipe() {
		return recipe;
	}
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	public int getOrder() {
		return step_order;
	}
	public void setOrder(int step_order) {
		this.step_order = step_order;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	

}
