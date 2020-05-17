package com.horizon.cookeat.entities;

public class R_Ingredient  extends Ingredient{
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
