package main;

import controller.ServerChatControlInterface;

public class Main {

	public static final int PORT = 4444;

	public static void main(String args[]) {

		ServerChatControlInterface serv = new ServerChatControlInterface(PORT);
		serv.start();
		
	}
	
}