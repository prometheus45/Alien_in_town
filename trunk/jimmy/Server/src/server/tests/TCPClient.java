package server.tests;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

import model.Ipv4;
import model.Message;

public class TCPClient extends Thread{

	private String serverMessage;
	private Ipv4 ip_client;
	public static final Ipv4 SERVERIP = new Ipv4("192.168.43.102");
	private OnMessageReceived mMessageListener = null;
	private boolean mRun = false;
	private int number;
	// private static final int TIME_OUT = 10 * 1000;

	PrintWriter out;
	BufferedReader in;

	/**
	 * Constructor of the class. OnMessagedReceived listens for the messages
	 * received from server
	 */
	public TCPClient(OnMessageReceived listener, int number) {
		mMessageListener = listener;
		this.number = number;
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
				System.out.println(ip_client + "ici");
				out.println(message);
				out.flush();
				if(out.checkError())
					System.out.println("erreur");
			}
		}
	}
	
	public void sendDisconnect(String user_name, String avatar_name){
		if (ip_client != null) {
			String message = Message.createMessage(ip_client, user_name, avatar_name,
					Message.CONTROL_MESSAGE, Message.CONTROL_MESSAGE_DISCONNECT);
			if (out != null && !out.checkError()) {
				out.println(message);
				System.out.println(ip_client + "ici");
				out.flush();
				if(out.checkError())
					System.out.println("erreur");
			}
		}		
	}

	public void stopClient() {
		mRun = false;
	}

	public void run() {
		mRun = true;
		try {
			InetAddress serverAddr = InetAddress.getByName(SERVERIP.toString());
			Socket socket = new Socket(serverAddr, 4444);
			ip_client = new Ipv4(socket.getLocalAddress());
			try {
				out = new PrintWriter(new BufferedWriter(
						new OutputStreamWriter(socket.getOutputStream())), true);
				in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				while (mRun) {
					this.sendMessage("user_name"+number, "avatar_name"+number, "a");
					serverMessage = in.readLine();
					if (serverMessage != null && mMessageListener != null)
						mMessageListener.messageReceived(serverMessage);
					serverMessage = null;
					Thread.sleep(2*1000);
				}
				
			} catch (Exception e) {
			} finally {
				socket.close();
			}

		} catch (Exception e) {
		}
	}

	public interface OnMessageReceived {
		public void messageReceived(String message);
	}
}