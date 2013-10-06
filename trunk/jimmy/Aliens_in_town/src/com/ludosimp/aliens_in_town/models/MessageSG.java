package com.ludosimp.aliens_in_town.models;

import com.ludosimp.aliens_in_town.utils.ParamCheck;

public class MessageSG {

	//Only here to show the possible type of message.
	public enum ServerGame{ CHAT };
	
	// The chat game message format for a server.
	public static final String SG_CHAT_NAME = "sg_chat";
	public static final int SG_CHAT_NUM_PARAMS = 1;
	public static final int SG_CHAT_MESSAGE = 1;
	
	public static String chat(Ipv4 ip, Integer port, String message) {
		if(!ParamCheck.notNull(new Object[]{ ip, port, message}))
			return null;
		if(message.trim().equals("")) return null;
		
		String SEPA = Message.new_separator();
		String elem_son = Message.concat(SEPA, new String[] { message });
		String elem_father = Message.createMessage(ip, port,
				SG_CHAT_NAME, elem_son);
		return elem_father;
	
	}
}
