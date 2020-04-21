package com.horizon.cookeat.model;

public class Recipe {
	
	// ATTRIBUTES //
	
	private int id;
	private int designation;
	private int prep_time;
	private int total_price;
	
	// GETTERS AND SETTERS //
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDesignation() {
		return designation;
	}
	public void setDesignation(int designation) {
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
