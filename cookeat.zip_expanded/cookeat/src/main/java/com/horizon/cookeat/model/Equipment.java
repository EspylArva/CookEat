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
@Table(name="equipment")
public class Equipment {
	// ATTRIBUTES //
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "equipment_generator")
	@SequenceGenerator(name="equipment_generator", sequenceName = "equipment_seq", initialValue = 300, allocationSize = 100)
	private int id;
	
	private String designation;
	private String path_to_icon;
	
	@ManyToMany(mappedBy = "list_equipments")
    private Set<Recipe> list_recipes = new HashSet<Recipe>();
	
	// METHODS //
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Equipment)) return false;
        return id == ((Equipment) o).getId();
    }
 
    @Override
    public int hashCode() {
        return id;
    }
	
	// CONSTRUCTOR //
	public Equipment(String designation, String path_icon)
	{
		this.designation = designation;
		this.path_to_icon = path_icon;
	}
	
	// GETTERS AND SETTERS //
	public void addEquipment(Recipe recipe) {
		list_recipes.add(recipe);
	}
	
	public void removeEquipment(Recipe recipe)
	{
		list_recipes.remove(recipe);
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

	public String getPath_to_icon() {
		return path_to_icon;
	}

	public void setPath_to_icon(String path_to_icon) {
		this.path_to_icon = path_to_icon;
	}
	

}
