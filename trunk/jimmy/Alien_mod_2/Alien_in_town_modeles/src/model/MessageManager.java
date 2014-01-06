package model;

import java.util.ArrayList;

import model.Avatar.Condition;
import model.Avatar.Type;
import model.MessageCG.GameType;
import model.MessageSS.Bool;
import model.MessageSS.LogType;
import model.exceptions.MessageHandlerCreationException;

public class MessageManager implements OnMessageReceive, SetMessageSend{
protected String message;

	public MessageManager() {
		message = null;
	}

	public void setMessage(String message) throws MessageHandlerCreationException{
		if(!Message.isMessage(message))
			throw new MessageHandlerCreationException();
		this.message = message;
	}
			
	public void receive_result(){
		if(message == null)
			return;
		
		//CG
		if(Message.getTypeMessage(message).equals(MessageCG.CG_CHAT_NAME))
			onCGChatReceive();
		if(Message.getTypeMessage(message).equals(MessageCG.CG_MAX_PLAYER_NAME))
			onCGMaxPlayerReceive();		
		if(Message.getTypeMessage(message).equals(MessageCG.CG_MIN_PLAYER_NAME))
				onCGMinPlayerReceive();
		if(Message.getTypeMessage(message).equals(MessageCG.CG_NEW_GAME_NAME))
			onCGNewGameReceive();		
		if(Message.getTypeMessage(message).equals(MessageCG.CG_TYPE_GAME_NAME))
				onCGTypeGameReceive();
		if(Message.getTypeMessage(message).equals(MessageCG.CG_START_GAME_NAME))
			onCGStartGameReceive();
		
		//CS
		if(Message.getTypeMessage(message).equals(MessageCS.CS_CONNECT_NAME))
			onCSConnectReceive();
		if(Message.getTypeMessage(message).equals(MessageCS.CS_DISCONNECT_NAME))
			onCSDisconnectReceive();
		if(Message.getTypeMessage(message).equals(MessageCS.CS_IDENTIFICATION_NAME))
			onCSIdentificationReceive();
		if(Message.getTypeMessage(message).equals(MessageCS.CS_INSCRIPTION_NAME))
			onCSInscriptionReceive();
		
		//SG
		if(Message.getTypeMessage(message).equals(MessageSG.SG_CHAT_NAME))
			onSGChatReceive();
		if(Message.getTypeMessage(message).equals(MessageSG.SG_ADD_AVATAR_NAME))
			onSGAddAvatarReceive();
		if(Message.getTypeMessage(message).equals(MessageSG.SG_REMOVE_AVATAR_NAME))
			onSGRemoveAvatarReceive();
		
		//SP
		if(Message.getTypeMessage(message).equals(MessageSP.SP_DISCONNECT_NAME))
			onSPDisconnectReceive();
		
		//SR
		if(Message.getTypeMessage(message).equals(MessageSR.SR_AVATARS_NAME))
			onSRAvatarsReceive();
		if(Message.getTypeMessage(message).equals(MessageSR.SR_STATES_NAME))
			onSRStatesReceive();
		if(Message.getTypeMessage(message).equals(MessageSR.SR_PLAYERS_NAME))
			onSRPlayersReceive();
		if(Message.getTypeMessage(message).equals(MessageSR.SR_POSTS_NAMES_NAME))
			onSRPostsNamesReceive();
		if(Message.getTypeMessage(message).equals(MessageSR.SR_TIME_NAME))
			onSRTimeReceive();
		if(Message.getTypeMessage(message).equals(MessageSR.SR_TYPES_NAME))
			onSRTypesReceive();
		
		//SS
		if(Message.getTypeMessage(message).equals(MessageSS.SS_CHAT_NAME))
			onSSChatReceive();
		if(Message.getTypeMessage(message).equals(MessageSS.SS_CONNECT_NAME))
			onSSConnectReceive();
		if(Message.getTypeMessage(message).equals(MessageSS.SS_DISCONNECT_NAME))
			onSSDisconnectReceive();
		if(Message.getTypeMessage(message).equals(MessageSS.SS_LOG_NAME))
			onSSLogReceive();
		if(Message.getTypeMessage(message).equals(MessageSS.SS_RECONNECT_NAME))
			onSSReconnectReceive();
		if(Message.getTypeMessage(message).equals(MessageSS.SS_IDENTIFICATION_NAME))
			onSSIdentificationReceive();
		if(Message.getTypeMessage(message).equals(MessageSS.SS_INSCRIPTION_NAME))
			onSSInscriptionReceive();
		if(Message.getTypeMessage(message).equals(MessageSS.SS_NEW_GAME_NAME))
			onSSNewGameReceive();
		
	}

	@Override
	public void setCGChatSend(Ipv4 ip, Integer port, String player,
			String avatar, Condition state, String poster, String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCGTypeGameSend(Ipv4 ip, Integer port, GameType type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCGMaxPlayerSend(Ipv4 ip, Integer port, Integer max) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCGMinPlayerSend(Ipv4 ip, Integer port, Integer min) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCGStartGameSend(Ipv4 ip, Integer port) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCGNewGame(Ipv4 ip, Integer port, GameType pref) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCSConnectSend(Ipv4 ip, Integer port, String player,
			Float time, String avatar, Type type) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCSDisconnectSend(Ipv4 ip, Integer port, String player,
			String avatar, Condition state, String poster) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCSIdentificationSend(Ipv4 ip, Integer port, String player,
			String mdp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCSInscriptionSend(Ipv4 ip, Integer port, String player,
			String mdp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSGChatSend(Ipv4 ip, Integer port, String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSGAddAvatarSend(Ipv4 ip, Integer port, String a_name,
			String a_poster, Type type, Condition c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSGRemoveAvatarSend(Ipv4 ip, Integer port, String a_name) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSRAvatarsSend(Ipv4 ip, Integer port,
			ArrayList<String> avatars) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSRStatesSend(Ipv4 ip, Integer port, ArrayList<String> states) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSRPlayersSend(Ipv4 ip, Integer port,
			ArrayList<String> players) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSRPostsNamesSend(Ipv4 ip, Integer port,
			ArrayList<String> posts_names) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSRTypesSend(Ipv4 ip, Integer port, ArrayList<String> types) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSRTimeSend(Ipv4 ip, Integer port, Float time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSPDisconnectSend(Ipv4 ip, Integer port) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSSChatSend(Ipv4 ip, Integer port, String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSSConnectSend(Ipv4 ip, Integer port) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSSDisconnectSend(Ipv4 ip, Integer port) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSSLogSend(Ipv4 ip, Integer port, LogType type, String c,
			String f, String message) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSSReconnectSend(Ipv4 ip, Integer port, String player,
			String avatar, Type type, String poster, Condition state) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSSIdentificationSend(Ipv4 ip, Integer port) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSSInscriptionSend(Ipv4 ip, Integer port, Bool result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSSNewGameSend(Ipv4 ip, Integer port, Ipv4 ip2, Integer port2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCGChatReceive() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCGTypeGameReceive() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCGMaxPlayerReceive() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCGMinPlayerReceive() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCGStartGameReceive() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCGNewGameReceive() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCSConnectReceive() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCSDisconnectReceive() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCSInscriptionReceive() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onCSIdentificationReceive() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSGChatReceive() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSGAddAvatarReceive() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSGRemoveAvatarReceive() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSRAvatarsReceive() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSRStatesReceive() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSRPlayersReceive() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSRPostsNamesReceive() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSRTypesReceive() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSRTimeReceive() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSPDisconnectReceive() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSSChatReceive() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSSConnectReceive() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSSDisconnectReceive() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSSLogReceive() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSSReconnectReceive() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSSIdentificationReceive() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSSInscriptionReceive() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onSSNewGameReceive() {
		// TODO Auto-generated method stub
		
	}

}