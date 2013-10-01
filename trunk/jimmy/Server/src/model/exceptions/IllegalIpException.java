package model.exceptions;

/**
 * 
 * @author jimmy
 * An exception throws in the Ipv4 class.
 */
@SuppressWarnings("serial")
public class IllegalIpException extends Exception{
	
	public IllegalIpException(){
		super("Invalide ip addresses, il n'y a pas quatre bytes");
	}

}
