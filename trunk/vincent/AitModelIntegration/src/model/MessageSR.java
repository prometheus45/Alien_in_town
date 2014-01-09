package model;

import java.util.ArrayList;

public class MessageSR {

	//Only here to show the possible type of message.
	public enum ServerRappel{ TIME, MORTS, TYPES, PLAYERS, AVATARS, POSTS_NAMES}

	// The time recall message format for a server.
	public static final String SR_TIME_NAME = "sr_time";
	public static final int SR_TIME_NUM_PARAMS = 1;
	public static final int SR_TIME = 1;
	
	public static String time(Ipv4 ip, Integer port, Integer id_game, Float time) {
		if (ip == null || port == null || time == null)
			return null;
		
		if(time < 0) return null;

		String SEPA = Message.new_separator();
		String stime = Float.toString(time);
		String elem_son = Message.concat(SEPA, new String[] { stime });
		String elem_father = Message.createMessage(ip, port, id_game,
				SR_TIME_NAME, elem_son);
		return elem_father;

	}
	
	// The dead recall message format for a server.
	public static final String SR_STATES_NAME = "sr_states";
	
	public static String states(Ipv4 ip, Integer port, Integer id_game,  ArrayList<String> states) {
		if (ip == null || port == null)
			return null;

		String elem_son=null;
		if(states != null && states.size()>0){
			String SEPA = Message.new_separator();
			elem_son = Message.concat(SEPA, states);
		}
		
		String elem_father = Message.createMessage(ip, port, id_game,
				SR_STATES_NAME, elem_son);
		return elem_father;

	}
	
	// The types recall message format for a server.
	public static final String SR_TYPES_NAME = "sr_types";
	
	public static String types(Ipv4 ip, Integer port, Integer id_game, ArrayList<String> types) {
		if (ip == null || port == null || types == null)
			return null;

		String elem_son=null;
		if(types.size()>0){
			String SEPA = Message.new_separator();
			elem_son = Message.concat(SEPA, types);
		}
		String elem_father = Message.createMessage(ip, port, id_game, 
				SR_TYPES_NAME, elem_son);
		return elem_father;

	}
	
	// The players recall message format for a server.
	public static final String SR_PLAYERS_NAME = "sr_players";

	public static String players(Ipv4 ip, Integer port, Integer id_game, ArrayList<String> players) {
		if (ip == null || port == null || players == null)
			return null;

		String elem_son=null;
		if(players.size()>0){
			String SEPA = Message.new_separator();
			elem_son = Message.concat(SEPA, players);
		}
		String elem_father = Message.createMessage(ip, port, id_game,
				SR_PLAYERS_NAME, elem_son);
		return elem_father;

	}
	
	// The avatars recall message format for a server.
	public static final String SR_AVATARS_NAME = "sr_avatars";

	public static String avatars(Ipv4 ip, Integer port, Integer id_game, ArrayList<String> avatars) {
		if (ip == null || port == null || avatars == null)
			return null;

		String elem_son=null;
		if(avatars.size()>0){
			String SEPA = Message.new_separator();
			elem_son = Message.concat(SEPA, avatars);
		}
		String elem_father = Message.createMessage(ip, port, id_game,
				SR_AVATARS_NAME, elem_son);
		return elem_father;

	}
	
	// The posts_names recall message format for a server.
	public static final String SR_POSTS_NAMES_NAME = "sr_posts_names";

	public static String posts_names(Ipv4 ip, Integer port, Integer id_game, ArrayList<String> posts_names) {
		if (ip == null || port == null || posts_names == null)
			return null;

		String elem_son=null;
		if(posts_names.size()>0){
			String SEPA = Message.new_separator();
			elem_son = Message.concat(SEPA, posts_names);
		}
		String elem_father = Message.createMessage(ip, port, id_game,
				SR_POSTS_NAMES_NAME, elem_son);
		return elem_father;

	}
	
}
