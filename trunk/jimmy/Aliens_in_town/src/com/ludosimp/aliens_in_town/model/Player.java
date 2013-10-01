package com.ludosimp.aliens_in_town.model;

public class Player {
private String user_name;
	
	public Player(String user_name){
		this.user_name = user_name;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
}
