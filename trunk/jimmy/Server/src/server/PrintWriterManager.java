package server;

import java.io.PrintWriter;
import java.util.Vector;

import model.Log.LogType;
import model.LogList.LogListener;

/**
 * 
 * @author jimmy
 * 
 *         A list of printwriter with synchronized methods for multi-thread
 *         access.
 */
public class PrintWriterManager {

	// The name of the class for the log.
	private final String classe = this.getClass().getName();

	// The list of the client output.
	private Vector<PrintWriter> tabClients = new Vector<PrintWriter>();

	// The number of clients.
	private int nbClients = 0;

	// The log listener to add log.
	private LogListener listener;

	/**
	 * Constructor
	 * 
	 * @param listener
	 *            The listener implemented to add log.
	 */
	public PrintWriterManager(LogListener listener) {
		this.listener = listener;
	}

	/**
	 * Synchronized method that send a message with the Message format to all
	 * the clients.
	 * 
	 * @param message
	 */
	synchronized public void sendAll(String message) {
		PrintWriter out;
		boolean erreur = false;

		for (int i = 0; i < nbClients; i++) {
			out = tabClients.get(i);
			if (out == null) {
				listener.logMessage(ServerChat.SERVERIP, classe, "sendAll",
						"PrintWriter in list null, message not send.",
						LogType.ERREUR);
				erreur = true;
			} else {
				out.println(message);
				out.flush();
				if (out.checkError()) {
					listener.logMessage(ServerChat.SERVERIP, classe, "sendAll",
							"Error while sending a message", LogType.ERREUR);
					erreur = true;
				}
			}
		}
		if (!erreur)
			listener.logMessage(ServerChat.SERVERIP, classe, "sendAll",
					"Sending complete.", LogType.SUCCESS);
	}

	/**
	 * Synchronized method that delete a output from a client from the list.
	 * 
	 * @param i
	 *            The client number corresponding to his output in the list.
	 */
	synchronized public void delClient(int i) {
		PrintWriter out;
		if (tabClients.get(i) != null) {
			if ((out = tabClients.remove(i)) != null) {
				out.close();
				nbClients--;
			} else
				listener.logMessage(ServerChat.SERVERIP, classe, "sendClient",
						"Fail to delete an output.", LogType.ERREUR);
		} else {
			listener.logMessage(ServerChat.SERVERIP, classe, "sendClient",
					"This output client doesn't exist.", LogType.ERREUR);
		}
	}

	/**
	 * Synchronized method that add an output of a client to the list.
	 * 
	 * @param out
	 *            the output to add.
	 * @return the number of the client in the list.
	 */
	synchronized public Integer addClient(PrintWriter out) {
		if (out == null) {
			listener.logMessage(ServerChat.SERVERIP, classe, "addClient",
					"NullPointer on output from a client.", LogType.ERREUR);
			return null;
		}
		if (tabClients.add(out)) {
			nbClients++;
			return nbClients - 1;
		}
		return null;
	}

	/**
	 * Synchronized method that return the number of clients in the list.
	 * 
	 * @return the number of clients
	 */
	synchronized public int getNbClients() {
		return nbClients;
	}

}
