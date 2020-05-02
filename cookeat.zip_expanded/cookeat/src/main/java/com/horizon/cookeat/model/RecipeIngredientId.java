package com.horizon.cookeat.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RecipeIngredientId implements Serializable
{
	// ATTRIBUTES //
	
	@Column(name = "recipeId")
    private int recipeId;
 
    @Column(name = "ingredientId")
    private int ingredientId;
    
    // METHODS //
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;

        RecipeIngredientId that = (RecipeIngredientId) o;
        return Objects.equals(recipeId, that.recipeId) && Objects.equals(ingredientId, that.ingredientId);
    }
    
    
    // CONSTRUCTOR //
    public RecipeIngredientId() {}
    
    public RecipeIngredientId(int r_id, int i_id)
    {
    	this.recipeId = r_id;
    	this.ingredientId = i_id;
    }


    // GETTERS AND SETTERS //
    
	public int getRecipeId() {
		return recipeId;
	}

	public void setRecipeId(int recipeId) {
		this.recipeId = recipeId;
	}

	public int getIngredientId() {
		return ingredientId;
	}

	public void setIngredientId(int ingredientId) {
		this.ingredientId = ingredientId;
	}

}
