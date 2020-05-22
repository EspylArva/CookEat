package com.horizon.cookeat.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class R_Ingredient  extends Ingredient{
	// ATTRIBUTES //
	@Expose
	@SerializedName("id")
	private int id;
	@Expose
	@SerializedName("designation")
	private String designation;
	@Expose
	@SerializedName("unit")
	private String unit;
	@Expose
	@SerializedName("price_per_unit")
	private float price_per_unit;
	@Expose
	@SerializedName("quantity")
	private float quantity;
	
	// METHODS //
	@Override
	public String toString()
	{
		return (
				"{"
				+ "\t id: " + id +
				"\t designation: " + designation +
				"\t unit: " + unit +
				"\t price_per_unit: " + price_per_unit +
				"\t quantity: " + quantity +
				"}"
				);
				
	}
	
	

	// CONSTRUCTOR //
	public R_Ingredient() {}
	
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

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public float getPrice_per_unit() {
		return price_per_unit;
	}

	public void setPrice_per_unit(float price_per_unit) {
		this.price_per_unit = price_per_unit;
	}

}
