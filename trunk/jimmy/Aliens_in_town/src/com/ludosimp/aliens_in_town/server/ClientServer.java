package com.ludosimp.aliens_in_town.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import com.ludosimp.aliens_in_town.models.Avatar;
import com.ludosimp.aliens_in_town.models.Ipv4;
import com.ludosimp.aliens_in_town.models.Player;

/**
 * @author jimmy
 * The client side of the server/client message management.
 */
public class ClientServer {
	
	//Fixed information till the broadcast works.
	public static final Ipv4 SERVERIP = new Ipv4("192.168.43.102");
	public static final int SERVERPORT = 4444;
	
	private Ipv4 ip_client;
	private Integer local_port;
	private Avatar avatar;
	private Player player;
	
	private BufferedReader in;
	private ClientMessageManager manager;
	private OnReceived listener = null;
	
	private boolean mRun = false;

	/**
	 * Constructor of the class. OnMessagedReceived listens for the messages
	 * received from server
	 */
	public ClientServer(Avatar avatar, Player player, OnReceived listener) {
		this.avatar = avatar;
		this.player = player;
		this.listener = listener;
	}

	/**
	 * Sends the message entered by client to the server
	 * 
	 */
	public void sendMessage(String message) {
		manager.sendCGChat(message);
	}

	/**
	 * Send a disconnect message to the server.
	 * @param user_name
	 * @param avatar_name
	 */
	public void sendDisconnect() {
		manager.sendCSDisconnect();
	}
	
	public void sendReconnect(String user_name){
		manager.sendCSReconnect();
	}

	public void stopClient() {
		mRun = false;
	}

	public void run() {

		mRun = true;
		try {
			/*
			 * ESSAI D'UTILISATION D'UN BROADCAST avec multicast socket. try{
			 * byte[] buf = new byte[1024]; //First we get the server ip.
			 * MulticastSocket socket = new MulticastSocket(SERVERPORT); TODO
			 * DatagramPacket packet = new DatagramPacket(buf, buf.length);
			 * socket.setSoTimeout(TIME_OUT); socket.receive(packet); serverip =
			 * packet.getAddress().getHostAddress(); Log.v("test","ici1");
			 * }catch(InterruptedIOException e){ Log.v("test","ici"); serverip =
			 * SERVERIP; Log.v("test", "La socket n'a pas récupéré le message");
			 * }
			 */

			// Here you must put the server ip address.
			InetAddress serverAddr = InetAddress.getByName(SERVERIP.toString());
			// Create a socket to make the connection with the server
			Socket socket = new Socket(serverAddr, SERVERPORT);

			//The local info.
			ip_client = new Ipv4(socket.getLocalAddress());
			local_port = socket.getLocalPort();

			try {

				// Sender to message.
				PrintWriter out = new PrintWriter(new BufferedWriter(
						new OutputStreamWriter(socket.getOutputStream())), true);

				// Receiver of message;
				in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));

				manager = new ClientMessageManager(ip_client,local_port, out, listener, avatar, player);

				String message;
				while (mRun) {
					message = in.readLine();

					if(message != null){
						manager.setMessage(message);
						manager.receive_result();
					}

					message = null;
				}
				
				socket.close();

			} catch (Exception e) {

			} finally {
				socket.close();
			}

		} catch (Exception e) {

		}

	}

	/*private boolean listen_with_timeout(float time_out){
		
	}*/
	/*private boolean identification() {

		
		String message = MessageCS.connect(ip_client, local_port, player.getUser_name(), 
				0f, avatar.getAvatar_name(), "avatar_type");
		
		//First we listen to the request of connection
		boolean connection_run = true;
		boolean request_done = false;
		float time_begin = System.currentTimeMillis(); // Time in ms.
		float time_out = 10; // 10sec.
		while (connection_run) {
			try {
				if (in.ready()) {
					String connection_mess= in.readLine();
					if(Message.getTypeMessage(connection_mess).equals(MessageSS.SS_CONNECT_NAME)){
						connection_run = false;
						request_done = true;
					}
				} else {
					if (((System.currentTimeMillis() - time_begin) / 1000) > time_out)
						connection_run = false;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if(!request_done)
			return false;
		
		// If the request is done we send the answer.
		
		boolean send_done = false;
		connection_run = true;
		time_begin = System.currentTimeMillis(); // Time in ms.
		while (connection_run) {
			out.println(message);
			out.flush();

			if (!out.checkError()) {
				connection_run = false;
				send_done = true;
			} else {
				if (((System.currentTimeMillis() - time_begin) / 1000) > time_out)
					connection_run = false;
			}
		}

		return send_done;
	}*/

	// Declare the interface. The method messageReceived(String message) will
	// must be implemented in the MyActivity
	// class at on asynckTask doInBackground
	public interface OnReceived {
		public void messageReceived(String message);
	}
}