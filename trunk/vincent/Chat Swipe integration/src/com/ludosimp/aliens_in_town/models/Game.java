package com.ludosimp.aliens_in_town.models;

import java.util.ArrayList;

import com.ludosimp.aliens_in_town.models.Avatar.Type;
import com.ludosimp.aliens_in_town.models.MessageSR.Turn;

public class Game {
private ArrayList<String> deads;
private ArrayList<String> players;
private ArrayList<String> posts_names;
private ArrayList<Type> types;
private float time;
private Turn turn;

	public Game(){
		deads = new ArrayList<String>();
		players = new ArrayList<String>();
		posts_names = new ArrayList<String>();
		types = new ArrayList<Type>();
		time = 0;
		turn = Turn.Day;
	}
	
	public void startGame(){
		time = System.currentTimeMillis();
	}
	
	//Deads
	public ArrayList<String> getDeads() {
		return deads;
	}
	public boolean addDead(String dead){
		if(dead == null)
			return false;
		
		return deads.add(dead);
	}
	public void setDeads(ArrayList<String> deads) {
		this.deads = deads;
	}

	//Players
	public ArrayList<String> getPlayers() {
		return players;
	}
	public void setPlayers(ArrayList<String> players) {
		this.players = players;
	}
	public boolean addPlayer(String string){
		if(string == null || string.trim().equals(""))
			return false;
		
		return players.add(string);
	}
	
	//Posts_names
	public ArrayList<String> getPosts_names() {
		return posts_names;
	}
	public void setPosts_names(ArrayList<String> posts_names) {
		this.posts_names = posts_names;
	}
	public boolean addPostName(String post){
		if(post == null)
			return false;
		
		return posts_names.add(post);
	}

	//Types
	public ArrayList<Type> getTypes() {
		return types;
	}
	public void setTypes(ArrayList<Type> types) {
		this.types = types;
	}
	public boolean addType(Type type){
		if(type == null)
			return false;
		
		return addType(type);
	}
	
	//Time
	public float getTime() {
		return time;
	}
	public void setTime(float time) {
		this.time = time;
	}

	//Turn
	public Turn getTurn() {
		return turn;
	}
	public void setTurn(Turn turn) {
		this.turn = turn;
	}

}
