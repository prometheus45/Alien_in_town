package server;

import java.net.*;
import java.io.*;
import java.util.*;
import model.InterfacesProperties;
import model.Ipv4;
import model.Log.LogType;
import model.LogList;

/**
 * 
 * @author jimmy The server class.
 */
public class ServerChat extends Thread {

	// The name of the class for the log.
	private final String classe = this.getClass().getName();

	// The log list.
	protected LogList logs;

	// The print mangager which contains the list of output from the client and
	// the synchronized methods.
	protected PrintWriterManager printer;

	// The server main object, his socket.
	protected ServerSocket ss;

	// The broadcast ip, meaning one by interface.
	protected ArrayList<Ipv4> broadcasts = new ArrayList<Ipv4>();

	// The server fixed ip in the network.
	protected static final Ipv4 SERVERIP = new Ipv4("192.168.43.102");

	// Some values for putting the server to sleep or stop him.
	private final int TIME_OUT = 5 * 1000;							//5sec before sleeping 2sec at max.
	private final int SLEEP_TIME_CONNECTION_DONE = 500;				//500ms.
	private final int SLEEP_TIME_CONNECTION_NOT_DONE = 2 * 1000;	//2sec.
	private boolean running = true;			//The boolean for the run. If put to false, the server is stop.
	private boolean first_client = false;   //Put to true when the first client connect.

	/**
	 * The constructor.
	 * 
	 * @param port
	 *            the port for the connection to the server. Send and receive.
	 */
	public ServerChat(int port) {

		// Initialize the log which will contains the messages from the chat and
		// the server possible errors.
		logs = new LogList();

		// Initialize the server socket.
		init_server(port);

		// Initialize the PrintWriterManager
		init_print_writer();

		// Initialize the broadcasts listes.
		init_broadcast_list();

	}

	/**
	 * Initialize the server. We try to connect the ServerSocket to the
	 * specified port.
	 * 
	 * @param port
	 *            The port in which connect the ServerSocket.
	 */
	protected void init_server(int port) {
		if (port <= 0)
			logs.add(SERVERIP, classe, "init_server", "Port " + port
					+ " inexistant.", LogType.ERREUR);
		else {
			try {
				ss = new ServerSocket(port);
				logs.add(SERVERIP, classe, "init_server",
						"The server is initialized.", LogType.SUCCESS);
			} catch (Exception e) {
				logs.add(SERVERIP, classe, "init_server",
						"Server initialization failed.", LogType.ERREUR);
			}
		}
	}

	/**
	 * Initialize the print writer manager with a listener so that he log
	 * message when necessary
	 */
	protected void init_print_writer() {
		printer = new PrintWriterManager(new LogList.LogListener() {

			@Override
			public void logMessage(String message, LogType type) {
				logs.add(message, type);
			}

			@Override
			public void logMessage(Ipv4 ip, String classe, String fonction,
					String message, LogType type) {
				logs.add(ip, classe, fonction, message, type);
			}

		});
	}

	/**
	 * Initialize the broadcast list.
	 */
	protected void init_broadcast_list() {
		InterfacesProperties iProp = new InterfacesProperties();
		ArrayList<Ipv4> address = iProp.getIpv4AddressFromInterfaces();
		for (int i = 0; i < address.size(); i++)
			broadcasts.add(iProp.getIpv4InterfaceProperties(address.get(i))[1]);
	}

	/**
	 * The server implementation of Runnable. The server connect new client and
	 * create a new thread for each one.
	 */
	@Override
	public void run() {
		super.run();

		// We don't try to add client if the serverSocket didn't connect to the
		// port.
		if (ss != null) {
			logs.add(SERVERIP, classe, "run", "Service launched.",
					LogType.SUCCESS);

			// Running stop in the add_new_thread method when nobody is
			// connected.
			while (running) {
				try {
					logs.add(SERVERIP, classe, "run",
							"Waiting for a new connection.", LogType.SUCCESS);
					/*
					 * if(_nbClients < 1){ for(int i=0; i<broadcasts.size();
					 * i++){ new ThreadBroadcast(broadcasts.get(i), "hi");
					 * ///////TODO broadcast. } }
					 */

					add_new_thread();

				} catch (IOException e) {
					logs.add(SERVERIP, classe, "run",
							"Error IOException during the running.",
							LogType.ERREUR);
				}
			}
		} else {
			logs.add(SERVERIP, classe, "run",
					"Service not launched. Service socket not initialized.",
					LogType.ERREUR);
		}
	}

	/**
	 * Launch a new thread client.
	 * 
	 * @throws IOException
	 */
	private void add_new_thread() throws IOException {

		// Set the time to wait for a new client.
		ss.setSoTimeout(TIME_OUT);

		try {

			// We wait for a new client and implement is logListener callback
			// methods.
			new ThreadChat(ss.accept(), printer, new LogList.LogListener() {

				@Override
				public void logMessage(String message, LogType type) {
					logs.add(message, type);
				}

				@Override
				public void logMessage(Ipv4 ip, String classe, String fonction,
						String message, LogType type) {
					logs.add(ip, classe, fonction, message, type);
				}
			});

			// If we are here one client have been add so we set to true the
			// first client boolean if not already done
			if (!first_client)
				first_client = true;

			// Success log.
			logs.add(SERVERIP, classe, "run", "New connection done",
					LogType.SUCCESS);

			// We connected someone but we put the server to sleep for some time.
			try {
				Thread.sleep(SLEEP_TIME_CONNECTION_DONE);
			} catch (InterruptedException e1) {
				logs.add(SERVERIP, classe, "add_new_thread",
						"The sleep of the thread has been interrupted.",
						LogType.ERREUR);
			}

		} catch (SocketTimeoutException e) {
			// This exception is throws when no client add during the specified
			// time.

			// If a client has already been connected but there is no client we
			// stop the server.
			if (first_client && printer.getNbClients() == 0) {
				logs.add(SERVERIP, classe, "add_new_thread", "Serveur fini.",
						LogType.SUCCESS);
				running = false;
				ss.close();
			}

			//We didn't connected someone and we put the server to sleep for some time.
			try {
				Thread.sleep(SLEEP_TIME_CONNECTION_NOT_DONE);
			} catch (InterruptedException e1) {
				logs.add(SERVERIP, classe, "add_new_thread",
						"Sleeping of the thread interruption.", LogType.ERREUR);
			}

		}
	}

}
