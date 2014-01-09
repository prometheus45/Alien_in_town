package model;

import model.Avatar.Condition;
import model.Avatar.Type;
import utils.ParamCheck;

public class MessageCS {

	// Only here to show the possible type of message.
	public enum ClientService {
		DISCONNECT, CONNECT, IDENTIFICATION, INSCRIPTION
	};

	// The disconnect message format for a client.
	public static final String CS_DISCONNECT_NAME = "cs_disconnect";
	public static final int CS_DISCONNECT_NUM_PARAMS = 4;
	public static final int CS_DISCONNECT_PLAYER_NAME = 1;
	public static final int CS_DISCONNECT_AVATAR_NAME = 2;
	public static final int CS_DISCONNECT_AVATAR_STATE = 3;
	public static final int CS_DISCONNECT_POST_NAME = 4;

	public static String disconnect(Ipv4 ip, Integer port, Integer id_game, String p_name,
			String a_name, Condition a_state, String post_name) {
		if (!ParamCheck.notNull(new Object[] { ip, port, p_name, a_name,
				a_state, post_name }))
			return null;
		if (!ParamCheck.notEmpty(new String[] { p_name, a_name, post_name }))
			return null;

		String SEPA = Message.new_separator();
		String elem_son = Message.concat(SEPA, new String[] { p_name, a_name,
				a_state.toString(), post_name });
		String elem_father = Message.createMessage(ip, port, id_game,
				CS_DISCONNECT_NAME, elem_son);
		return elem_father;

	}

	// The connect message format for a client.
	public static final String CS_CONNECT_NAME = "cs_connect";
	public static final int CS_CONNECT_NUM_PARAMS = 4;
	public static final int CS_CONNECT_PLAYER_NAME = 1;
	public static final int CS_CONNECT_PLAYER_TIME_BLOCK = 2;
	public static final int CS_CONNECT_AVATAR_NAME = 3;
	public static final int CS_CONNECT_AVATAR_TYPE = 4;

	public static String connect(Ipv4 ip, Integer port, Integer id_game, String p_name,
			Float time, String a_name, Type a_type) {
		if (!ParamCheck.notNull(new Object[] { ip, port, p_name, time, a_name,
				a_type }))
			return null;
		if (!ParamCheck.notEmpty(new String[] { p_name, a_name }))
			return null;
		if(time < 0)
			return null;

		String SEPA = Message.new_separator();
		String stime = Float.toString(time);
		String elem_son = Message.concat(SEPA, new String[] { p_name, stime,
				a_name, a_type.toString() });
		String elem_father = Message.createMessage(ip, port, id_game, CS_CONNECT_NAME,
				elem_son);
		return elem_father;

	}
	
	// The identification message format for a client.
	public static final String CS_IDENTIFICATION_NAME = "cs_identification";
	public static final int CS_IDENTIFICATION_NUM_PARAMS = 2;
	public static final int CS_IDENTIFICATION_USER = 1;
	public static final int CS_IDENTIFICATION_MDP = 2;
	
	public static String identification(Ipv4 ip, Integer port, Integer id_game, String player, String mdp){
		if (!ParamCheck.notNull(new Object[] { ip, port, player, mdp }))
			return null;
		if (!ParamCheck.notEmpty(new String[] { player, mdp }))
			return null;

		String SEPA = Message.new_separator();
		String elem_son = Message.concat(SEPA, new String[] { player, mdp });
		String elem_father = Message.createMessage(ip, port, id_game, CS_IDENTIFICATION_NAME,
				elem_son);
		return elem_father;		
	}
	
	// The inscription message format for a client.
	public static final String CS_INSCRIPTION_NAME = "cs_inscription";
	public static final int CS_INSCRIPTION_NUM_PARAMS = 2;
	public static final int CS_INSCRIPTION_USER = 1;
	public static final int CS_INSCRIPTION_MDP = 2;
	
	public static String inscription(Ipv4 ip, Integer port, Integer id_game, String player, String mdp){
		if (!ParamCheck.notNull(new Object[] { ip, port, player, mdp }))
			return null;
		if (!ParamCheck.notEmpty(new String[] { player, mdp }))
			return null;

		String SEPA = Message.new_separator();
		String elem_son = Message.concat(SEPA, new String[] { player, mdp });
		String elem_father = Message.createMessage(ip, port, id_game, CS_INSCRIPTION_NAME,
				elem_son);
		return elem_father;			
	}

}
