import java.io.PrintWriter;

//The child of the serverChat.
//The functions are exactly the same but it is uses to send message to people listening to a listener
//given at his construction.
public class ServerChatControlInterface extends ServerChat {
	private WriteMessageListener listener;

	public ServerChatControlInterface(int port, WriteMessageListener listener) {
		super(port);
		this.listener = listener;
	}

	@Override
	// Send a message to all the clients the same as before but after that send
	// the same message to the interface.
	synchronized public void sendAll(String message, String sLast) {
		super.sendAll(message, sLast);
		listener.writeMessage(message + sLast);
	}

	@Override
	// Remove a client from the list the same as before but after that send a
	// message to the interface.
	synchronized public void delClient(int i) {
		super.delClient(i);
		listener.writeMessage("Le client numéro " + i + " s'est déconnecté.");
	}

	@Override
	// Add a new output of a new client to the list.
	synchronized public int addClient(PrintWriter out) {
		listener.writeMessage("Un nouveau client vient de se connecter");
		return super.addClient(out);
	}

	// Declare the interface.
	// To uses it the interface must specified the action to make when the method is launch here.
	public interface WriteMessageListener {
		public void writeMessage(String message);
	}
}
