package com.example.ormlitetest.beans;

import com.j256.ormlite.field.DatabaseField;

public class Parent {
	
	@DatabaseField(generatedId=true)
	private int id;
	
	@DatabaseField
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
