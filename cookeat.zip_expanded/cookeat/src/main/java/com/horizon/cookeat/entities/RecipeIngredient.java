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

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NaturalIdCache;

@Entity(name = "RecipeIngredient")
@Table(name = "recipe_ingredient")
@NaturalIdCache
@Cache(
    usage = CacheConcurrencyStrategy.READ_WRITE
)
public class RecipeIngredient implements Serializable{
	
	// ATTRIBUTES //
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
    private RecipeIngredientId id;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @MapsId("recipeId")
    private Recipe recipe;
 
    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("ingredientId")
    private Ingredient ingredient;
 
    @Column(name = "quantity")
    private float quantity;
 
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
    
    public RecipeIngredient(Recipe r, Ingredient i, float quantity) {
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

	public float getQuantity() {
		return quantity;
	}

	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}

}

