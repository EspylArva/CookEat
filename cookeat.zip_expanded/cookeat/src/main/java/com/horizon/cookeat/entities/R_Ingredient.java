package com.horizon.cookeat.entities;

import com.google.gson.annotations.Expose;

public class R_Ingredient  extends Ingredient{
	// ATTRIBUTES //
	@Expose
	private int id;
	@Expose
	private String designation;
	@Expose
	private String unit;
	@Expose
	private float price_per_unit;
	@Expose
	private float quantity;
	
	// METHODS //
	
	// CONSTRUCTOR //
	public R_Ingredient(int id, String unit, String designation, float ppu, float quantity)
	{
		this.id = id;
		this.unit = unit;
		this.designation = designation;
		this.price_per_unit = ppu;
		this.quantity = quantity;
	}
	
	// GETTERS AND SETTERS //
	public float getQuantity()
	{
		return this.quantity;
	}
	
	public void setQuantity(float q)
	{
		this.quantity = q;
	}

}
