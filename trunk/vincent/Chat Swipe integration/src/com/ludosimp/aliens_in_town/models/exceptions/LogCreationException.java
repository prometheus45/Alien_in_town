package com.ludosimp.aliens_in_town.models.exceptions;

@SuppressWarnings("serial")
public class LogCreationException extends Exception {

	public LogCreationException(){
		super("Error in the constructor of the log class.");
	}
}
