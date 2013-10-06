package com.ludosimp.aliens_in_town.server;

import java.io.PrintWriter;
import java.util.ArrayList;

import android.util.Log;

import com.ludosimp.aliens_in_town.models.Avatar;
import com.ludosimp.aliens_in_town.models.Avatar.Condition;
import com.ludosimp.aliens_in_town.models.Avatar.Type;
import com.ludosimp.aliens_in_town.models.Ipv4;
import com.ludosimp.aliens_in_town.models.Message;
import com.ludosimp.aliens_in_town.models.MessageCG;
import com.ludosimp.aliens_in_town.models.MessageCS;
import com.ludosimp.aliens_in_town.models.MessageManager;
import com.ludosimp.aliens_in_town.models.MessageSS;
import com.ludosimp.aliens_in_town.models.MessageSR.Turn;
import com.ludosimp.aliens_in_town.models.MessageSS.LogType;
import com.ludosimp.aliens_in_town.models.Player;
import com.ludosimp.aliens_in_town.server.ClientServer.OnReceived;

public class ClientMessageManager extends MessageManager{
private Ipv4 ip_client;
private Integer port_client;
private PrintWriter out;
private Avatar avatar;
private Player player;
private OnReceived listener;

	/**
	 * Client message manager uses to manage the message receive and the message to send
	 * on the client side.
	 * @param ip_client the ip of the client
	 * @param port_client the port of the client
	 * @param out the printwriter to send message to the server
	 * @param listener a callback class to send the message to the controller
	 * @param avatar the avatar model class
	 * @param player the player model class
	 */
	public ClientMessageManager(Ipv4 ip_client, Integer port_client, PrintWriter out, OnReceived listener, Avatar avatar, Player player){
		super();
		this.ip_client = ip_client;
		this.port_client = port_client;
		this.out = out;
		this.avatar = avatar;
		this.player = player;
		this.listener = listener;
	}

	//Only setters.
	public void setIp_client(Ipv4 ip_client) {
		this.ip_client = ip_client;
	}
	public void setPort_client(Integer port_client) {
		this.port_client = port_client;
	}
	public void setAvatar(Avatar avatar) {
		this.avatar = avatar;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}

	//Private method to send a message.
	private void send(String mess){
		if (out != null && !out.checkError()) {
			out.println(mess);
			out.flush();
		}
	}
	
	@Override
	public void onCGChatReceive() {
		listener.messageReceived(message);
	}

	@Override
	public void onSSReconnectReceive() {
		if(message == null)
			return;
		
		String poster = Message.getParam(message, MessageSS.SS_RECONNECT_POST_NAME);
		String p_name = Message.getParam(message, MessageSS.SS_RECONNECT_PLAYER_NAME);
		String a_name = Message.getParam(message, MessageSS.SS_RECONNECT_AVATAR_NAME);
		String type = Message.getParam(message, MessageSS.SS_RECONNECT_AVATAR_TYPE);
		
		avatar.setName(a_name);
		avatar.setType(type);
		avatar.setPoster(poster);
		player.setName(p_name);
	}

	@Override
	public void onSGChatReceive() {
		listener.messageReceived(message);
	}

	@Override
	public void onSPDisconnectReceive() {}

	@Override
	public void onSRAvatarsReceive() {}

	@Override
	public void onSRDeadsReceive() {}

	@Override
	public void onSRPlayersReceive() {}

	@Override
	public void onSRPostsNamesReceive() {}

	@Override
	public void onSRTimeReceive() {}

	@Override
	public void onSRTurnReceive() {}

	@Override
	public void onSSChatReceive() {
		listener.messageReceived(message);
	}
	
	@Override
	public void onSSConnectReceive() {
		this.sendCSConnect();
	}

	@Override
	public void onSSDisconnectReceive() {
		//this.sendCSConnect(); TODO
	}

	@Override
	public void onSSLogReceive() {}

	@Override
	public void onSRTypesReceive() {}

	@Override
	public void setCGChatSend(Ipv4 ip, Integer port, String player,
			String avatar, Condition state, String poster, String message) {
		String mess = MessageCG.chat(ip, port, player, avatar, state, poster, message);
		send(mess);
	}
	//The simplified method of the override method setCGChatSend.
	public void sendCGChat(String message){
		setCGChatSend(ip_client, port_client, player.getName(), avatar.getName(), avatar.getState(),
				 avatar.getPoster(), message);
	}

	@Override
	public void setCSConnectSend(Ipv4 ip, Integer port, String player,
			Float time, String avatar, Type type) {
		String mess = MessageCS.connect(ip, port, player, time, avatar, type);
		Log.e("ici", mess+"*");
		send(mess);	
	}
	
	//The simplified method of the override method setCSConnectSend.
	public void sendCSConnect(){
		setCSConnectSend(ip_client, port_client, player.getName(), player.getBlock(), avatar.getName(),
				avatar.getType());
	}

	@Override
	public void setCSDisconnectSend(Ipv4 ip, Integer port, String player,
			String avatar, Condition state, String poster) {
		String mess = MessageCS.disconnect(ip, port, player, avatar, state, poster);
		send(mess);
	}
	//The simplified method of the override method setCSDisconnectSend.
	public void sendCSDisconnect(){
		setCSDisconnectSend(ip_client, port_client, player.getName(), avatar.getName(), 
				avatar.getState(), avatar.getPoster());
	}
	
	@Override
	public void setCSReconnectSend(Ipv4 ip, Integer port) {
		String mess = MessageCS.reconnect(ip, port);
		send(mess);
	}
	//The simplified method of the override method setCSDisconnectSend.
	public void sendCSReconnect(){
		setCSReconnectSend(ip_client, port_client);
	}
	
	@Override
	public void setSGChatSend(Ipv4 ip, Integer port, String message) {}

	@Override
	public void setSPDisconnectSend(Ipv4 ip, Integer port) {}

	@Override
	public void setSRAvatarsSend(Ipv4 ip, Integer port,
			ArrayList<String> avatars) {}

	@Override
	public void setSRDeadsSend(Ipv4 ip, Integer port, ArrayList<String> deads) {}

	@Override
	public void setSRPlayersSend(Ipv4 ip, Integer port,
			ArrayList<String> players) {}

	@Override
	public void setSRPostsNamesSend(Ipv4 ip, Integer port,
			ArrayList<String> posts_names) {}

	@Override
	public void setSRTypesSend(Ipv4 ip, Integer port, ArrayList<String> types) {}

	@Override
	public void setSRTimeSend(Ipv4 ip, Integer port, Float time) {}

	@Override
	public void setSRTurnSend(Ipv4 ip, Integer port, Turn turn) {}

	@Override
	public void setSSChatSend(Ipv4 ip, Integer port, String message) {}

	@Override
	public void setSSConnectSend(Ipv4 ip, Integer port) {}

	@Override
	public void setSSDisconnectSend(Ipv4 ip, Integer port) {}

	@Override
	public void setSSLogSend(Ipv4 ip, Integer port, LogType type, String c,
			String f, String message) {}

	@Override
	public void setSSReconnectSend(Ipv4 ip, Integer port, String player,
			String avatar, Type type, String poster, Condition state) {}
	
}
