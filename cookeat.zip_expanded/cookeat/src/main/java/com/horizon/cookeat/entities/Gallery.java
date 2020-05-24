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
@Table(name="gallery")
public class Gallery {
	 // ATTRIBUTES //
	@Expose
	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gallery_generator")
	@SequenceGenerator(name="gallery_generator", sequenceName = "gallery_seq", initialValue = 700, allocationSize = 2)
	@Column(name="id", updatable = false, nullable = false)
	private int id;
	@Expose
	private String path;
	@Expose
	private String description;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Recipe recipe;
	
	// METHODS //
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gallery )) return false;
        return id == ((Gallery) o).getId();
    }
 
    @Override
    public int hashCode() {
        return id;
    }
    
    @Override
    public String toString()
    {
    	return ("{ id: " + id + ", path: " + path + ", description: " + description + " }");
    }

	// CONSTRUCTOR //
    public Gallery() {}
    
	public Gallery(String path, String description)
	{
		this.path = path;
		this.description = description;
	}
	
	// GETTERS AND SETTERS //	
	public Recipe getRecipe() {
		return recipe;
	}
	
	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
