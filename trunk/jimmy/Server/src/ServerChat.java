import java.net.*;
import java.io.*;
import java.util.*;

public class ServerChat extends Thread{

	// Vector containing Clients output.
	private Vector<PrintWriter> _tabClients = new Vector<PrintWriter>();
	private int _nbClients = 0;
	protected ServerSocket ss;

	public ServerChat(int port) {
		try {
			init(port);
			System.out.println("ServerChat initialisé.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void init(int port) throws Exception {
		if (port <= 0)
			throw new Exception("Port impossible");
		else {
			try {
				// Instantiate the socket server.
				ss = new ServerSocket(port);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void run() {
		super.run();
		System.out.println("Lancement:");
		for (;;) {
			try {
				System.out.println("Attente de connection.");
				new ThreadChat(ss.accept(), this);
				System.out.println("Connection faite.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// Send a message to all the clients.
	synchronized public void sendAll(String message, String sLast) {
		PrintWriter out;
		for(int i=0; i<_nbClients; i++){
			out = _tabClients.get(i);
			out.println(message);
			out.flush();
		}
	}

	// Remove a client from the list.
	synchronized public void delClient(int i) {
		_nbClients--;
		if (_tabClients.get(i)!=null) {
			_tabClients.remove(i);
		}
	}

	// Add a new output of a new client to the list.
	synchronized public int addClient(PrintWriter out) {
		if(out == null)
			System.out.println("ServerChat: line 77, out == null");
		_nbClients++;
		_tabClients.add(out);
		return _tabClients.size();
	}

	// Get the number of clients.
	synchronized public int getNbClients() {
		return _nbClients;
	}

}
