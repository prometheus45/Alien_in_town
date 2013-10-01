package com.ludosimp.aliens_in_town.model.exceptions;

@SuppressWarnings("serial")
public class IllegalIpException extends Exception{
	
	public IllegalIpException(){
		super("Invalide ip addresses, il n'y a pas quatre bytes");
	}

}
