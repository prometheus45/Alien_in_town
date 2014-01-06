package model.tests;

import model.Avatar.Type;
import model.Ipv4;
import model.Message;
import model.MessageCG;
import model.MessageCS;
import model.Avatar.Condition;
import junit.framework.TestCase;

public class MessageCSTest extends TestCase {

	public void testDisconnect() {
		Condition c = Condition.ALIVE;
		//good message;
		assertNotNull(MessageCS.disconnect(new Ipv4("0.0.0.0"), 1,0, "a", "b", c, "post"));
		assertNull(MessageCS.disconnect(null, 1, 0,"a", "b", c, "post"));
		assertNull(MessageCS.disconnect(new Ipv4("0.0.0.0"), -2, 0,"a", "b", c, "post"));
		assertNull(MessageCS.disconnect(new Ipv4("0.0.0.0"), 1, 0,null, "b", c, "post"));
		assertNull(MessageCS.disconnect(new Ipv4("0.0.0.0"), 1, 0,"a", null, c, "post"));
		assertNull(MessageCS.disconnect(new Ipv4("0.0.0.0"), 1, 0,"a", "b", null, "post"));
		assertNull(MessageCS.disconnect(new Ipv4("0.0.0.0"), 1, 0,"a", "b", c, null));
		assertNull(MessageCS.disconnect(new Ipv4("0.0.0.0"), 1, 0,"", "b", c, "post"));
		assertNull(MessageCS.disconnect(new Ipv4("0.0.0.0"), 1,0, "a", "", c, "post"));
		assertNull(MessageCS.disconnect(new Ipv4("0.0.0.0"), 1, 0,"a", "b", c, ""));
		
		String message = MessageCS.disconnect(new Ipv4("0.0.0.0"), 1, 0,"a", "b", c, "post");
		assertNull(Message.getParam(message, 0));
		assertTrue(Message.getParam(message, MessageCG.CG_CHAT_AVATAR_NAME).equals("b"));
		assertTrue(Message.getParam(message, MessageCG.CG_CHAT_AVATAR_STATE).equals(c.toString()));
		assertTrue(Message.getParam(message, MessageCG.CG_CHAT_PLAYER_NAME).equals("a"));
		assertTrue(Message.getParam(message, MessageCG.CG_CHAT_POST_NAME).equals("post"));
		assertNull(Message.getParam(message, MessageCG.CG_CHAT_POST_NAME+1));
	}


	public void testConnect() {
		Type t = Type.DOCTOR;
		//good message;
		assertNotNull(MessageCS.connect(new Ipv4("0.0.0.0"), 1, 0,"a", 2f, "name", t));
		assertNull(MessageCS.connect(null, 1, 0,"a", 2f, "name", t));
		assertNull(MessageCS.connect(new Ipv4("0.0.0.0"), -2, 0,"a", 2f, "name", t));
		assertNull(MessageCS.connect(new Ipv4("0.0.0.0"), 1, 0,null, 2f, "name", t));
		assertNull(MessageCS.connect(new Ipv4("0.0.0.0"), 1, 0,"a", null, "name", t));
		assertNull(MessageCS.connect(new Ipv4("0.0.0.0"), 1, 0,"a", 2f, null, t));
		assertNull(MessageCS.connect(new Ipv4("0.0.0.0"), 1, 0,"a", 2f, "name", null));
		assertNull(MessageCS.connect(new Ipv4("0.0.0.0"), 1, 0,"", 2f, "name", t));
		assertNull(MessageCS.connect(new Ipv4("0.0.0.0"), 1, 0,"a", -2f, "name", t));
		assertNull(MessageCS.connect(new Ipv4("0.0.0.0"), 1, 0,"a", 2f, "", t));
		
		String message = MessageCS.connect(new Ipv4("0.0.0.0"), 1, 0,"a", 2f, "name", t);
		assertNull(Message.getParam(message, 0));
		assertTrue(Message.getParam(message, MessageCS.CS_CONNECT_AVATAR_NAME).equals("name"));
		assertTrue(Message.getParam(message, MessageCS.CS_CONNECT_AVATAR_TYPE).equals(t.toString()));
		assertTrue(Message.getParam(message, MessageCS.CS_CONNECT_PLAYER_NAME).equals("a"));
		assertTrue(Float.parseFloat(Message.getParam(message, MessageCS.CS_CONNECT_PLAYER_TIME_BLOCK))==2f);
		assertNull(Message.getParam(message, MessageCS.CS_CONNECT_AVATAR_TYPE+1));
	}
	
	public void testIdentification() {
		assertNotNull(MessageCS.identification(new Ipv4("0.0.0.0"), 1, 0,"user", "mdp"));
		assertNull(MessageCS.identification(new Ipv4("0.0.0.0"), 1, 0,null, "mdp"));
		assertNull(MessageCS.identification(new Ipv4("0.0.0.0"), 1, 0,"user", null));
		
		String message = MessageCS.identification(new Ipv4("0.0.0.0"), 1, 0,"user", "mdp");
		assertNull(Message.getParam(message, 0));
		assertTrue(Message.getParam(message, MessageCS.CS_IDENTIFICATION_USER).equals("user"));
		assertTrue(Message.getParam(message, MessageCS.CS_IDENTIFICATION_MDP).equals("mdp"));
		assertNull(Message.getParam(message, MessageCS.CS_IDENTIFICATION_MDP+1));
	}

	public void testInscription() {
		assertNotNull(MessageCS.inscription(new Ipv4("0.0.0.0"), 1, 0,"user", "mdp"));
		assertNull(MessageCS.inscription(new Ipv4("0.0.0.0"), 1, 0,null, "mdp"));
		assertNull(MessageCS.inscription(new Ipv4("0.0.0.0"), 1, 0,"user", null));
		
		String message = MessageCS.inscription(new Ipv4("0.0.0.0"), 1, 0,"user", "mdp");
		assertNull(Message.getParam(message, 0));
		assertTrue(Message.getParam(message, MessageCS.CS_INSCRIPTION_USER).equals("user"));
		assertTrue(Message.getParam(message, MessageCS.CS_INSCRIPTION_MDP).equals("mdp"));
		assertNull(Message.getParam(message, MessageCS.CS_INSCRIPTION_MDP+1));
	}

	

}
