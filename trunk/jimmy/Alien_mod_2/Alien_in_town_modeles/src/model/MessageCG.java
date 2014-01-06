package model;

import model.Avatar.Condition;
import utils.ParamCheck;

public class MessageCG {
	
	//Only here to show the possible type of message.
	public enum ClientGame{ CHAT, SET_TYPE_GAME, SET_MIN_PLAYER, SET_MAX_PLAYER, START_GAME, NEW_GAME };
	
	// The chat message format for a client.
	public static final String CG_CHAT_NAME = "cg_chat";
	public static final int CG_CHAT_NUM_PARAMS = 5;
	public static final int CG_CHAT_PLAYER_NAME = 1;
	public static final int CG_CHAT_AVATAR_NAME = 2;
	public static final int CG_CHAT_AVATAR_STATE = 3;
	public static final int CG_CHAT_POST_NAME = 4;
	public static final int CG_CHAT_MESSAGE = 5;
	
	public static String chat(Ipv4 ip, Integer port, Integer id_game, String p_name,
			String a_name, Condition a_state, String post_name, String message) {
		
		if (!ParamCheck.notNull(new Object[]{ip, port, p_name, a_name, a_state, post_name, message}))
			return null;
		if (!ParamCheck.notEmpty(new String[]{a_name, post_name, p_name, message}))
			return null;

		String SEPA = Message.new_separator();
		String elem_son = Message.concat(SEPA, new String[] { p_name, a_name,
				a_state.toString(), post_name, message});
		String elem_father = Message.createMessage(ip, port, id_game,
				CG_CHAT_NAME, elem_son);
		
		return elem_father;

	}
	
	// The TypeGameMessage format for a client.
	public enum GameType { NORMAL };
	public static final String CG_TYPE_GAME_NAME = "cg_type_game";
	public static final int CG_TYPE_GAME_NUM_PARAMS = 1;
	public static final int CG_TYPE_GAME_TYPE = 1;
	
	public static String type_game(Ipv4 ip, Integer port, Integer id_game, GameType type){
		if (!ParamCheck.notNull(new Object[]{ip, port, type}))
			return null;

		String SEPA = Message.new_separator();
		String elem_son = Message.concat(SEPA, new String[] { type.toString()});
		String elem_father = Message.createMessage(ip, port, id_game,
				CG_TYPE_GAME_NAME, elem_son);
		
		return elem_father;
	}
	
	// The MaxPlayerMessage format for a client.
	public static final String CG_MAX_PLAYER_NAME = "cg_max_player";
	public static final int CG_MAX_PLAYER_NUM_PARAMS = 1;
	public static final int CG_MAX_PLAYER_MAX = 1;
	
	public static String max_player(Ipv4 ip, Integer port, Integer id_game, Integer max){
		if (!ParamCheck.notNull(new Object[]{ip, port, max}))
			return null;

		String SEPA = Message.new_separator();
		String elem_son = Message.concat(SEPA, new String[] { max.toString()});
		String elem_father = Message.createMessage(ip, port, id_game,
				CG_MAX_PLAYER_NAME, elem_son);
		
		return elem_father;
	}
	
	// The MinPlayerMessage format for a client.
	public static final String CG_MIN_PLAYER_NAME = "cg_min_player";
	public static final int CG_MIN_PLAYER_NUM_PARAMS = 1;
	public static final int CG_MIN_PLAYER_MIN = 1;
	
	public static String min_player(Ipv4 ip, Integer port, Integer id_game, Integer min){
		if (!ParamCheck.notNull(new Object[]{ip, port, min}))
			return null;

		String SEPA = Message.new_separator();
		String elem_son = Message.concat(SEPA, new String[] { min.toString()});
		String elem_father = Message.createMessage(ip, port, id_game, 
				CG_MIN_PLAYER_NAME, elem_son);
		
		return elem_father;
	}
	
	// The StartGameMessage format for a client.
	public static final String CG_START_GAME_NAME = "cg_start_game";
	public static final int CG_START_GAME_NUM_PARAMS = 0;
	
	public static String start_game(Ipv4 ip, Integer port, Integer id_game){
		if (!ParamCheck.notNull(new Object[]{ip, port}))
			return null;
		
		String elem_father = Message.createMessage(ip, port, id_game,
				CG_START_GAME_NAME, null);
		
		return elem_father;
	}
	
	// The NewGameMessage format for a client.
	public static final String CG_NEW_GAME_NAME = "cg_new_game";
	public static final int CG_NEW_GAME_NUM_PARAMS = 1;
	public static final int CG_NEW_GAME_TYPE = 1;
	
	public static String new_game(Ipv4 ip, Integer port, Integer id_game, GameType pref){
		if (!ParamCheck.notNull(new Object[]{ip, port, pref}))
			return null;
		
		String SEPA = Message.new_separator();
		String elem_son = Message.concat(SEPA, new String[] { pref.toString()});
		String elem_father = Message.createMessage(ip, port, id_game, 
				CG_NEW_GAME_NAME, elem_son);
		
		return elem_father;
	}
	
}
