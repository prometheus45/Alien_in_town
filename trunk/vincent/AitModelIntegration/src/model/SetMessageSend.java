package model;

import java.util.ArrayList;

import model.Avatar.Condition;
import model.Avatar.Type;
import model.MessageCG.GameType;
import model.MessageSS.Bool;
import model.MessageSS.LogType;

public interface SetMessageSend{
	
	//CG messages
	public void setCGChatSend(Ipv4 ip, Integer port, String player, String avatar, Condition state, String poster, String message);
	public void setCGTypeGameSend(Ipv4 ip, Integer port, GameType type);
	public void setCGMaxPlayerSend(Ipv4 ip, Integer port, Integer max);
	public void setCGMinPlayerSend(Ipv4 ip, Integer port, Integer min);
	public void setCGStartGameSend(Ipv4 ip, Integer port);
	public void setCGNewGame(Ipv4 ip, Integer port, GameType pref);
	
	//CS messages
	public void setCSConnectSend(Ipv4 ip, Integer port, String player, Float time, String avatar, Type type);
	public void setCSDisconnectSend(Ipv4 ip, Integer port, String player, String avatar, Condition state, String poster);
	public void setCSIdentificationSend(Ipv4 ip, Integer port, String player, String mdp);
	public void setCSInscriptionSend(Ipv4 ip, Integer port, String player, String mdp);
	
	//SG messages
	public void setSGChatSend(Ipv4 ip, Integer port, String message);
	public void setSGAddAvatarSend(Ipv4 ip, Integer port, String a_name, String a_poster, Type type, Condition c);
	public void setSGRemoveAvatarSend(Ipv4 ip, Integer port, String a_name);
	
	//SR messages
	public void setSRAvatarsSend(Ipv4 ip, Integer port, ArrayList<String> avatars);
	public void setSRStatesSend(Ipv4 ip, Integer port, ArrayList<String> states);
	public void setSRPlayersSend(Ipv4 ip, Integer port, ArrayList<String> players);
	public void setSRPostsNamesSend(Ipv4 ip, Integer port, ArrayList<String> posts_names);
	public void setSRTypesSend(Ipv4 ip, Integer port, ArrayList<String> types);
	public void setSRTimeSend(Ipv4 ip, Integer port, Float time); 
	
	//SP messages
	public void setSPDisconnectSend(Ipv4 ip, Integer port);
	
	//SS messages
	public void setSSChatSend(Ipv4 ip, Integer port, String message);
	public void setSSConnectSend(Ipv4 ip, Integer port);
	public void setSSDisconnectSend(Ipv4 ip, Integer port);
	public void setSSLogSend(Ipv4 ip, Integer port, LogType type, String c, String f, String message);
	public void setSSReconnectSend(Ipv4 ip, Integer port, String player, String avatar, Type type, String poster, Condition state);
	public void setSSIdentificationSend(Ipv4 ip, Integer port);
	public void setSSInscriptionSend(Ipv4 ip, Integer port, Bool result);
	public void setSSNewGameSend(Ipv4 ip, Integer port, Ipv4 ip2, Integer port2);
	
}
