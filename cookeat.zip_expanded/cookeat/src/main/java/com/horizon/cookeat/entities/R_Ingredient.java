package com.horizon.cookeat.entities;

import com.google.gson.annotations.Expose;

public class R_Ingredient  extends Ingredient{
	@Expose
	private int id;
	@Expose
	private String unit;
	@Expose
	private String designation;
	@Expose
	private int price_per_unit;
	@Expose
	private int quantity;
	
	public R_Ingredient(int id, String unit, String designation, int ppu, int quantity)
	{
		this.id = id;
		this.unit = unit;
		this.designation = designation;
		this.price_per_unit = ppu;
		this.quantity = quantity;
	}

}
