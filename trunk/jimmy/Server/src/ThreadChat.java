import java.net.*;
import java.io.*;

import javax.swing.SwingUtilities;

//Class for all thread clients
class ThreadChat implements Runnable {
	private Thread _t;
	private Socket _s;
	private PrintWriter _out;
	private BufferedReader _in;
	private ServerChat _chatServ;
	private int _numClient = 0;

	// Constructor
	ThreadChat(Socket s, ServerChat chatServ) // the socket is given by the
												// method ss.accept().
	{ 
		_chatServ = chatServ;
		_s = s;

		try {
			System.out.println(s.getInetAddress());
			_out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(_s.getOutputStream())), true);
			if(_out==null)
				System.out.println("In ThreadChat: line 26, _out==null");
			_in = new BufferedReader(new InputStreamReader(_s.getInputStream()));
			_numClient = _chatServ.addClient(_out);

		} catch (IOException e) {
			
		}

		_t = new Thread(this);
		_t.start();
	}

	// Waiting for messages and redirect them to all the clients.
	public void run() {
		String message = "";
		try {
			
			//Infinity.
			for(;;) {
	            message = _in.readLine();         
	            if (message != null && message != "")
					_chatServ.sendAll(message, "");
			}
			
		} catch (Exception e) {
			System.out.println("Erreur run, lecture d'un string");
		} finally // Often call when the client disconnect
		{
			try {
				_chatServ.delClient(_numClient); // Remove the client from the
													// list
				_s.close(); // Close the socket if not already.
			} catch (IOException e) {
				System.out.println("Client sortie erreur");
			}
		}
	}
}
