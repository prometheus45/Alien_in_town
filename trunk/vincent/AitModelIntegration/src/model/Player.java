package model;

import java.io.Serializable;

public class Player implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private Float time_block;

	public Player(String name) {
		this.name = name;
		this.time_block = 0f;
	}

	public Player(String name, Float time_block){
		this.name = name;
		this.time_block = time_block;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void setBlock(Float f){
		this.time_block = f;
	}
	public Float getBlock(){
		return time_block;
	}
	
}
