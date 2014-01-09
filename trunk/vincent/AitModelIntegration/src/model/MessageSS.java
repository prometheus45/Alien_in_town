package model;

import utils.ParamCheck;
import model.Avatar.Condition;
import model.Avatar.Type;

public class MessageSS {
	
	//Only here to show the possible type of message.
	public enum ServerService{ CHAT, DISCONNECT, CONNECT, RECONNECT, LOG, IDENTIFICATION, INSCRIPTION, NEW_GAME};
	
	// The chat service message format for a server.
	public static final String SS_CHAT_NAME = "ss_chat";
	public static final int SS_CHAT_NUM_PARAMS = 1;
	public static final int SS_CHAT_MESSAGE = 1;
	
	public static String chat(Ipv4 ip, Integer port, Integer id_game, String message) {
		if (ip == null || port == null || message == null)
			return null;
		if (message.trim().equals(""))return null;
		if(port < 0)return null;
		
		String SEPA = Message.new_separator();
		String elem_son = Message.concat(SEPA, new String[] { message });
		String elem_father = Message.createMessage(ip, port, id_game,
				SS_CHAT_NAME, elem_son);
		return elem_father;

	}
	
	// The disconnect service message format for a server.
	public static final String SS_DISCONNECT_NAME = "ss_disconnect";
	public static final int SS_DISCONNECT_NUM_PARAMS = 0;
	
	public static String disconnect(Ipv4 ip, Integer port, Integer id_game) {
		if (ip == null || port == null)
			return null;
		if(port < 0)return null;
		
		String elem_father = Message.createMessage(ip, port, id_game,
				SS_DISCONNECT_NAME, null);
		return elem_father;

	}
	
	// The connect service message format for a server.
	public static final String SS_CONNECT_NAME = "ss_connect";
	public static final int SS_CONNECT_NUM_PARAMS = 0;
	
	public static String connect(Ipv4 ip, Integer port, Integer id_game) {
		if (ip == null || port == null)
			return null;
		
		if(port < 0) return null;
		
		String elem_father = Message.createMessage(ip, port, id_game,
				SS_CONNECT_NAME, null);
		return elem_father;

	}
	
	// The reconnect service message format for a server.
	public static final String SS_RECONNECT_NAME = "ss_reconnect";
	public static final int SS_RECONNECT_NUM_PARAMS = 5;	
	public static final int SS_RECONNECT_PLAYER_NAME = 1;
	public static final int SS_RECONNECT_AVATAR_NAME = 2;
	public static final int SS_RECONNECT_AVATAR_TYPE = 3;
	public static final int SS_RECONNECT_POST_NAME = 4;
	public static final int SS_RECONNECT_STATE = 5;
	
	public static String reconnect(Ipv4 ip, Integer port, Integer id_game, String p_name,
			String a_name, Type a_type, String post_name, Condition state) {
		if(!ParamCheck.notNull(new Object[]{ ip, port, p_name, a_name, a_type, post_name, state}))
			return null;
		if(!ParamCheck.notEmpty(new String[]{p_name, a_name, post_name}))
			return null;
		if(port < 0)return null;
		
		String SEPA = Message.new_separator();
		String elem_son = Message.concat(SEPA, new String[] { p_name, a_name,
				a_type.toString(), post_name, state.toString()});
		String elem_father = Message.createMessage(ip, port, id_game,
				SS_RECONNECT_NAME, elem_son);
		return elem_father;

	}
	
	// The log server message format.
	public static final String SS_LOG_NAME = "ss_log";
	public static final int SS_LOG_NUM_PARAMS = 4;	
	public static final int SS_LOG_TYPE = 1;
	public static final int SS_LOG_CLASSE = 2;
	public static final int SS_LOG_FONCTION = 3;
	public static final int SS_LOG_MESSAGE = 4;
	
	public enum LogType{ ERROR, SUCCESS };
	
	public static String log(Ipv4 ip, Integer port, Integer id_game, LogType type, String classe, String fonction, String message){
		if(!ParamCheck.notNull(new Object[]{ ip, port, type, classe, fonction, message}))
			return null;
		if(!ParamCheck.notEmpty(new String[]{classe, fonction, message}))
			return null;
		
		if(port < 0)return null;
		
		String SEPA = Message.new_separator();
		String elem_son = Message.concat(SEPA, new String[] { type.toString(), classe, fonction, message});
		String elem_father = Message.createMessage(ip, port, id_game, SS_LOG_NAME, elem_son);
		
		return elem_father;		
	}
	// The identification message format for a client.
	public static final String SS_IDENTIFICATION_NAME = "ss_identification";
	public static final int SS_IDENTIFICATION_NUM_PARAMS = 0;
	
	public static String identification(Ipv4 ip, Integer port, Integer id_game){
		if(!ParamCheck.notNull(new Object[]{ ip, port}))
			return null;
		
		String elem_father = Message.createMessage(ip, port, id_game, SS_IDENTIFICATION_NAME, null);
		
		return elem_father;	
	}
	
	// The inscription message format for a client.
	public static final String SS_INSCRIPTION_NAME = "ss_inscription";
	public static final int SS_INSCRIPTION_NUM_PARAMS = 1;
	public static final int SS_INSCRIPTION_RESULT = 1;
	public enum Bool { TRUE, FALSE };
	
	public static String inscription(Ipv4 ip, Integer port, Integer id_game, Bool result){
		if (!ParamCheck.notNull(new Object[] { ip, port, result }))
			return null;

		String SEPA = Message.new_separator();
		String elem_son = Message.concat(SEPA, new String[] { result.toString() });
		String elem_father = Message.createMessage(ip, port, id_game, SS_INSCRIPTION_NAME,
				elem_son);
		return elem_father;		
	}	
	
	// The NewGameMessage format for a client.
	public static final String SS_NEW_GAME_NAME = "ss_new_game";
	public static final int SS_NEW_GAME_NUM_PARAMS = 2;
	public static final int SS_NEW_GAME_IP = 1;
	public static final int SS_NEW_GAME_PORT = 2;
	
	public static String new_game(Ipv4 ip, Integer port, Integer id_game, Ipv4 ip2, Integer port2){
		if (!ParamCheck.notNull(new Object[]{ip, port, ip2, port2}))
			return null;
		
		String SEPA = Message.new_separator();
		String elem_son = Message.concat(SEPA, new String[] { ip2.toString(), port2.toString()});
		String elem_father = Message.createMessage(ip, port, id_game,
				SS_NEW_GAME_NAME, elem_son);
		
		return elem_father;
	}

	
}
