package com.myskunk.bl;

public class Player {
	// instance variables
	String name; // Player's name
	
	public Player() {
		// default  constructor
	}
	
	// Overload
	public Player(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
