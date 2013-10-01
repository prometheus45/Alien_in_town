package com.ludosimp.aliens_in_town.server;

import android.util.Log;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

import com.ludosimp.aliens_in_town.model.Ipv4;
import com.ludosimp.aliens_in_town.model.Message;

public class TCPClient {

	private String serverMessage;
	private Ipv4 ip_client;
	public static final Ipv4 SERVERIP = new Ipv4("192.168.43.102");
	public static final int SERVERPORT = 4444;
	private OnMessageReceived mMessageListener = null;
	private boolean mRun = false;
	// private static final int TIME_OUT = 10 * 1000;

	PrintWriter out;
	BufferedReader in;

	/**
	 * Constructor of the class. OnMessagedReceived listens for the messages
	 * received from server
	 */
	public TCPClient(OnMessageReceived listener) {
		mMessageListener = listener;
	}

	/**
	 * Sends the message entered by client to the server
	 * 
	 */
	public void sendMessage(String user_name, String avatar_name, String message) {
		if (ip_client != null) {
			message = Message.createMessage(ip_client, user_name, avatar_name,
					Message.CHAT_MESSAGE, message);
			if (out != null && !out.checkError()) {
				out.println(message);
				out.flush();
			}
		}
	}
	
	public void sendDisconnect(String user_name, String avatar_name){
		if (ip_client != null) {
			String message = Message.createMessage(ip_client, user_name, avatar_name,
					Message.CONTROL_MESSAGE, Message.CONTROL_MESSAGE_DISCONNECT);
			if (out != null && !out.checkError()) {
				out.println(message);
				out.flush();
			}
		}		
	}

	public void stopClient() {
		mRun = false;
	}

	public void run() {

		mRun = true;
		// String serverip;
		try {
			/*
			 * ESSAI D'UTILISATION D'UN BROADCAST avec multicast socket. try{
			 * byte[] buf = new byte[1024]; //First we get the server ip.
			 * MulticastSocket socket = new MulticastSocket(SERVERPORT);
			 * DatagramPacket packet = new DatagramPacket(buf, buf.length);
			 * socket.setSoTimeout(TIME_OUT); socket.receive(packet); serverip =
			 * packet.getAddress().getHostAddress(); Log.v("test","ici1");
			 * }catch(InterruptedIOException e){ Log.v("test","ici"); serverip =
			 * SERVERIP; Log.v("test", "La socket n'a pas récupéré le message");
			 * }
			 */

			// here you must put the server ip address.
			InetAddress serverAddr = InetAddress.getByName(SERVERIP.toString());

			Log.e("TCP Client", "C: Connecting...");

			// create a socket to make the connection with the server
			Socket socket = new Socket(serverAddr, SERVERPORT);
			
			ip_client = new Ipv4(socket.getLocalAddress());
			Log.v("test", ip_client.toString());
			
			try {

				// send the message to the server
				out = new PrintWriter(new BufferedWriter(
						new OutputStreamWriter(socket.getOutputStream())), true);

				// receive the message which the server sends back
				in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));

				// in this while the client listens for the messages sent by the
				// server
				while (mRun) {
					serverMessage = in.readLine();
						
					
					Log.e("test", "a l'écoute");
					if (serverMessage != null && mMessageListener != null)
						mMessageListener.messageReceived(serverMessage);
					
					serverMessage = null;
				}

				
			} catch (Exception e) {

				Log.e("TCP", "S: Error", e);

			} finally {
				// the socket must be closed. It is not possible to reconnect to
				// this socket
				// after it is closed, which means a new socket instance has to
				// be created.
				socket.close();
			}

		} catch (Exception e) {

			Log.e("TCP", "C: Error", e);

		}

	}

	// Declare the interface. The method messageReceived(String message) will
	// must be implemented in the MyActivity
	// class at on asynckTask doInBackground
	public interface OnMessageReceived {
		public void messageReceived(String message);
	}
}