package com.horizon.cookeat.model;

import javax.persistence.Entity;

@Entity
public class Gallery {

	 // ATTRIBUTES //
	private String id;
	private String path;
	private String description;
	
	// GETTERS AND SETTERS //
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
