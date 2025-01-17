package com.horizon.cookeat.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;
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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.NaturalIdCache;

@Entity
@Table(name = "recipe")
@NaturalIdCache
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Recipe implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// ATTRIBUTES //
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recipe_generator")
	@SequenceGenerator(name = "recipe_generator", sequenceName = "recipe_seq", initialValue = 100, allocationSize = 100)
	private int id;
	@NaturalId
	private String designation;
	private int prep_time;
	private int total_price;

	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
	private transient Set<RecipeIngredient> list_ingredients = new HashSet<>();

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "recipe_equipment", joinColumns = @JoinColumn(name = "recipe_id"), inverseJoinColumns = @JoinColumn(name = "equipment_id"))
	private Set<Equipment> list_equipments = new HashSet<Equipment>();

	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Etape> list_steps = new HashSet<Etape>();

	@OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Gallery> list_gallery = new HashSet<Gallery>();

	// METHODS //
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Recipe))
			return false;
		return id == ((Recipe) o).getId();
	}

	@Override
	public int hashCode() {
		return Objects.hash(designation);
	}

	@Override
	public String toString() {
		return String.format(
				"{\r\n" + 
				"	\"id\":%s,\r\n" + 
				"	\"designation\": \"%s\",\r\n" + 
				"	\"prep_time\": %s,\r\n" + 
				"	\"total_price\": %s\r\n" +
				"}"
				, this.id, this.designation, this.prep_time, this.total_price);
	}

	// CONSTRUCTOR //
	public Recipe() {
	}

	public Recipe(String designation, int prep_time, int total_price, String path_icon) {
		this.designation = designation;
		this.prep_time = prep_time;
		this.total_price = total_price;
	}

	public Recipe(String designation, int prep_time, int total_price, String path_icon,
			Map<Ingredient, Integer> ingredients) {
		this.designation = designation;
		this.prep_time = prep_time;
		this.total_price = total_price;

		for (Entry<Ingredient, Integer> entry : ingredients.entrySet()) {
			addIngredient(entry.getKey(), entry.getValue());
		}
	}

	// GETTERS AND SETTERS //
	public void addGallery(Gallery g) {
		list_gallery.add(g);
		g.setRecipe(this);
	}

	public void removeGallery(Gallery g) {
		g.setRecipe(null);
		list_gallery.remove(g);
	}

	public void addStep(Etape s) {
		s.setRecipe(this);
		list_steps.add(s);
//		list_steps.put(s.getOrder(), s);
	}

	public void removeStep(Etape s) {
		s.setRecipe(null);
		list_steps.remove(s);
//		list_steps.remove(s.getOrder());
	}

	public void addEquipment(Equipment equipment) {
		list_equipments.add(equipment);
	}

	public void removeEquipment(Equipment equipment) {
		list_equipments.remove(equipment);
	}

	public void addIngredient(Ingredient i, int quantity) {
		RecipeIngredient join = new RecipeIngredient(this, i, quantity);
		list_ingredients.add(join);
	}

	public void removeIngredient(Ingredient i) {
		for (Iterator<RecipeIngredient> iterator = list_ingredients.iterator(); iterator.hasNext();) {
			RecipeIngredient join = iterator.next();
			if (join.getRecipe().equals(this) && join.getIngredient().equals(i)) {
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

}
