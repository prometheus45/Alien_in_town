package server;

import java.net.*;
import java.io.*;

import model.Log.LogType;
import model.Message;
import model.LogList.LogListener;

/**
 * 
 * @author jimmy
 * 
 *         This class take care of the message from the client. And redistribute
 *         them by using the printwritermanager.
 */
class ThreadChat extends Thread {

	//The name of the class for the log.
	private final String classe = this.getClass().getName();

	//The socket of the client for the receive.
	private Socket socket;
	
	//The output of the client which is add to the list in the PrintWriterManager.
	private PrintWriter out;
	
	//The listener to the client message.
	private BufferedReader in;
	
	//The PrintWriterManager of the server which contains the synchronized methods.
	private PrintWriterManager manager;
	
	//The log listener to replace exceptions by log in the server process.
	private LogListener listener;
	
	//The number of the client in the list of client.(in PrintWriterManager).
	private Integer numClient;

	/**
	 * Constructor to initialize a thread client. If error the client will not
	 * be add to the chat.
	 * 
	 * @param socket
	 *            The socket of the client.
	 * @param manager
	 *            The manager of the instance.
	 * @param listener
	 *            The listener to add log of error and success in the instance
	 *            logs.
	 */
	ThreadChat(Socket socket, PrintWriterManager manager, LogListener listener) {
		this.socket = socket;
		this.manager = manager;
		this.listener = listener;

		try {

			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					socket.getOutputStream())), true);
			if (out == null)
				listener.logMessage(ServerChat.SERVERIP, classe, "Constructor",
						"The output from the client is null. Client banned.",
						LogType.ERREUR);
			else
				in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
			if (in == null)
				listener.logMessage(ServerChat.SERVERIP, classe, "Constructor",
						"The input from the client is null. Client banned.",
						LogType.ERREUR);
			else
				numClient = manager.addClient(out);
			if (numClient == null)
				listener.logMessage(
						ServerChat.SERVERIP,
						classe,
						"Constructor",
						"Impossible to add the client to the list. Client banned.",
						LogType.ERREUR);
			else {
				listener.logMessage(ServerChat.SERVERIP, classe, "Constructor",
						"The client has been add to the list", LogType.SUCCESS);
				this.start();
			}

		} catch (IOException e) {
			listener.logMessage(ServerChat.SERVERIP, classe, "Constructor",
					"Error during proccess of adding a client. Client banned.",
					LogType.ERREUR);
		}
	}

	/**
	 * Thread run of this class. Launch in the constructor. The method wait for
	 * message of the client and take care of distributing them between all the
	 * client.
	 */
	public void run() {
		String message = "";
		try {

			boolean running = true;

			while (running) {
				message = in.readLine();

				// Management of the messages.
				if (message != null && !message.trim().equals("")) {

					// We check the format of the message
					if (Message.formatRespected(message)) {

						// If the format is correct we send the message if is
						// type is chat_message.
						if (Message.getTypeMessageFromSender(message).equals(
								Message.CHAT_MESSAGE)) {
							listener.logMessage(message, LogType.MESSAGE);
							manager.sendAll(message);
						} else
						// If he isn't we check if it's a disconnect and if it
						// is we stop the process.
						if (Message.getMessageFromSender(message).equals(
								Message.CONTROL_MESSAGE_DISCONNECT)) {
							try {
								manager.delClient(numClient);  // Remove the client from the list
								socket.close();
							} catch (IOException e) {
								listener.logMessage(ServerChat.SERVERIP, classe, "run",
										"Error occured here while closing the socket of the client "
												+ numClient, LogType.ERREUR);
							}
							running = false;
						} else
							listener.logMessage("Message not possible:"
									+ message, LogType.ERREUR);
					} else {
						listener.logMessage(ServerChat.SERVERIP, classe, "run",
								"Format not respected:" + message,
								LogType.ERREUR);
					}
				}

				message = null; // We put the message back to null;
			}

		} catch (Exception e) {
			listener.logMessage(ServerChat.SERVERIP, classe, "run",
					"Difficult error to read. Client banned.", LogType.ERREUR);
		} finally { // Often call when the client disconnect without us
					// obtaining the information.
			try {
				manager.delClient(numClient); // Remove the client from the list
				socket.close(); // Close the socket if not already.
			} catch (IOException e) {
				listener.logMessage(ServerChat.SERVERIP, classe, "run",
						"Error occured while closing the socket of the client "
								+ numClient, LogType.ERREUR);
			}
		}
	}
}
