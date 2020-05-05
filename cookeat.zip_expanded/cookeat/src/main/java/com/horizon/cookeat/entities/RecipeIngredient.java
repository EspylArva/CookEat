package com.horizon.cookeat.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity(name = "RecipeIngredient")
@Table(name = "recipe_ingredient")
public class RecipeIngredient implements Serializable{
	
	// ATTRIBUTES //
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
    private RecipeIngredientId id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @MapsId("recipeId")
    private Recipe recipe;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("ingredientId")
    private Ingredient ingredient;
 
    @Column(name = "quantity")
    private int quantity;
 
    // METHODS //
    
    @Override
    public boolean equals(Object o) {
    	if (this == o) return true;
    	
    	if (o == null || getClass() != o.getClass())
    		return false;
    	
    	RecipeIngredient that = (RecipeIngredient) o;
    	return Objects.equals(recipe, that.recipe) &&
    			Objects.equals(ingredient, that.ingredient);
    }
    
    @Override
    public int hashCode() {
    	return Objects.hash(recipe, ingredient);
    }
 
    // CONSTRUCTOR //
    public RecipeIngredient() {}
    
    public RecipeIngredient(Recipe r, Ingredient i, int quantity) {
        this.recipe = r;
        this.ingredient = i;

        this.id = new RecipeIngredientId(r.getId(), i.getId());
        this.quantity = quantity;
    }
    
    
	// ATTRIBUTES //
	
	public RecipeIngredientId getId() {
		return id;
	}

	public void setId(RecipeIngredientId id) {
		this.id = id;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}

