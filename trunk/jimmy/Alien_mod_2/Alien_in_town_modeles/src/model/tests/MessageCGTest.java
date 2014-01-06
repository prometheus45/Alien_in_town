package model.tests;

import model.Avatar.Condition;
import model.Ipv4;
import model.Message;
import model.MessageCG;
import model.MessageCG.GameType;
import junit.framework.TestCase;

public class MessageCGTest extends TestCase {

	public void testChat() {
		Condition c = Condition.ALIVE;
		//good message;
		assertNotNull(MessageCG.chat(new Ipv4("0.0.0.0"), 1, 0,"a", "b", c, "post", "m"));
		assertNull(MessageCG.chat(null, 1, 0,"a", "b", c, "post", "m"));
		assertNull(MessageCG.chat(new Ipv4("0.0.0.0"), -2, 0,"a", "b", c, "post", "m"));
		assertNull(MessageCG.chat(new Ipv4("0.0.0.0"), 1, 0,null, "b", c, "post", "m"));
		assertNull(MessageCG.chat(new Ipv4("0.0.0.0"), 1, 0,"a", null, c, "post", "m"));
		assertNull(MessageCG.chat(new Ipv4("0.0.0.0"), 1, 0,"a", "b", null, "post", "m"));
		assertNull(MessageCG.chat(new Ipv4("0.0.0.0"), 1, 0,"a", "b", c, null, "m"));
		assertNull(MessageCG.chat(new Ipv4("0.0.0.0"), 1, 0,"a", "b", c, "post", null));
		assertNull(MessageCG.chat(new Ipv4("0.0.0.0"), 1, 0,"", "b", c, "post", "m"));
		assertNull(MessageCG.chat(new Ipv4("0.0.0.0"), 1, 0,"a", "", c, "post", "m"));
		assertNull(MessageCG.chat(new Ipv4("0.0.0.0"), 1, 0,"a", "b", c, "", "m"));
		assertNull(MessageCG.chat(new Ipv4("0.0.0.0"), 1, 0,"a", "b", c, "post", ""));
		
		String message = MessageCG.chat(new Ipv4("0.0.0.0"), 1, 0,"a", "b", c, "post", "m");
		assertNull(Message.getParam(message, 0));
		assertNotNull(Message.getParam(message, MessageCG.CG_CHAT_AVATAR_NAME));
		assertNotNull(Message.getParam(message, MessageCG.CG_CHAT_AVATAR_STATE));
		assertNotNull(Message.getParam(message, MessageCG.CG_CHAT_MESSAGE));
		assertNotNull(Message.getParam(message, MessageCG.CG_CHAT_PLAYER_NAME));
		assertNotNull(Message.getParam(message, MessageCG.CG_CHAT_POST_NAME));
		assertNull(Message.getParam(message, MessageCG.CG_CHAT_MESSAGE+1));
	}
	
	public void testType_game() {
		assertNotNull(MessageCG.type_game(new Ipv4("0.0.0.0"), 1, 0,GameType.NORMAL));
		assertNull(MessageCG.type_game(new Ipv4("0.0.0.0"), 1, 0,null));
		
		String message = MessageCG.type_game(new Ipv4("0.0.0.0"), 1, 0,GameType.NORMAL);
		assertNull(Message.getParam(message, 0));
		assertNotNull(Message.getParam(message, MessageCG.CG_TYPE_GAME_TYPE));
		assertNull(Message.getParam(message, MessageCG.CG_TYPE_GAME_TYPE+1));
	}

	public void testMax_player() {
		assertNotNull(MessageCG.max_player(new Ipv4("0.0.0.0"), 1, 0,2));
		assertNull(MessageCG.max_player(new Ipv4("0.0.0.0"), 1,0, null));
		
		String message = MessageCG.max_player(new Ipv4("0.0.0.0"), 1, 0,2);
		assertNull(Message.getParam(message, 0));
		assertNotNull(Message.getParam(message, MessageCG.CG_MAX_PLAYER_MAX));
		assertNull(Message.getParam(message, MessageCG.CG_MAX_PLAYER_MAX+1));
	}

	public void testMin_player() {
		assertNotNull(MessageCG.min_player(new Ipv4("0.0.0.0"), 1, 0,2));
		assertNull(MessageCG.min_player(new Ipv4("0.0.0.0"), 1, 0,null));
		
		String message = MessageCG.min_player(new Ipv4("0.0.0.0"), 1, 0,2);
		assertNull(Message.getParam(message, 0));
		assertNotNull(Message.getParam(message, MessageCG.CG_MIN_PLAYER_MIN));
		assertNull(Message.getParam(message, MessageCG.CG_MIN_PLAYER_MIN+1));
	}

	public void testStart_game() {
		assertNotNull(MessageCG.start_game(new Ipv4("0.0.0.0"), 0,1));
	}

	public void testNew_game() {
		assertNotNull(MessageCG.new_game(new Ipv4("0.0.0.0"), 1,0, GameType.NORMAL));
		assertNull(MessageCG.new_game(new Ipv4("0.0.0.0"), 1, 0,null));
		
		String message = MessageCG.new_game(new Ipv4("0.0.0.0"), 1,0, GameType.NORMAL);
		assertNull(Message.getParam(message, 0));
		assertNotNull(Message.getParam(message, MessageCG.CG_NEW_GAME_TYPE));
		assertNull(Message.getParam(message, MessageCG.CG_NEW_GAME_TYPE+1));
	}
	
	public void testNew_vote() {
		assertNotNull(MessageCG.new_vote(new Ipv4("0.0.0.0"), 1,0, "Jean Charles Henry", "Raymond"));
		assertNull(MessageCG.new_vote(new Ipv4("0.0.0.0"), 1, 0,null,null));
	}
	
	public void testRemove_vote() {
		assertNotNull(MessageCG.remove_vote(new Ipv4("0.0.0.0"), 1,0, "Jean Charles Henry", "Raymond"));
		assertNull(MessageCG.remove_vote(new Ipv4("0.0.0.0"), 1, 0,null,null));
	}

}
