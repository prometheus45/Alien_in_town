package model.tests;

import java.util.ArrayList;

import model.Avatar.Condition;
import model.Avatar.Type;
import model.Ipv4;
import model.Log;
import model.MessageCG;
import model.MessageCS;
import model.MessageSG;
import model.MessageSP;
import model.MessageSR;
import model.MessageSS;
import model.MessageSS.LogType;
import model.exceptions.LogCreationException;
import junit.framework.TestCase;

public class LogTest extends TestCase {

	public void testLog() {
		Ipv4 ip = new Ipv4("0.0.0.0");
		int port = 1;
		String p_name = "player";
		String a_name = "avatar";
		Type a_type = Type.DOCTOR;
		Condition a_state = Condition.DEAD;
		String post_name = "post";
		String message = "message";
		LogType type = LogType.ERROR;
		String classe = "classe";
		String fonction = "fonction";
		ArrayList<String> list = new ArrayList<String>();
		Float time = (float) System.currentTimeMillis();

		String message1 = MessageCG.chat(ip, port, 0,p_name, a_name, a_state,
				post_name, message);
		String message2 = MessageCS.connect(ip, port, 0,p_name, time, a_name,
				a_type);
		String message3 = MessageCS.disconnect(ip, port, 0,p_name, a_name,
				a_state, post_name);
		String message5 = MessageSG.chat(ip, port, 0,message);
		String message6 = MessageSP.disconnect(ip, port, 0);
		String message7 = MessageSR.avatars(ip, port, 0, list);
		String message8 = MessageSR.states(ip, port, 0, list);
		String message9 = MessageSR.players(ip, port, 0 ,list);
		String message10 = MessageSR.posts_names(ip, port, 0,list);
		String message11 = MessageSR.time(ip, port, 0,time);
		String message13 = MessageSR.types(ip, port, 0,list);
		String message14 = MessageSS.chat(ip, port,0, message);
		String message15 = MessageSS.connect(ip, port, 0);
		String message16 = MessageSS.disconnect(ip, port, 0);
		String message17 = MessageSS.log(ip, port, 0, type, classe, fonction,
				message);
		String message18 = MessageSS.reconnect(ip, port, 0, p_name, a_name,
				a_type, post_name, a_state);

		Log[] l = new Log[18];

		try {
			l[0] = new Log(message1);
			l[1] = new Log(message2);
			l[2] = new Log(message3);
			l[4] = new Log(message5);
			l[5] = new Log(message6);
			l[6] = new Log(message7);
			l[7] = new Log(message8);
			l[8] = new Log(message9);
			l[9] = new Log(message10);
			l[10] = new Log(message11);
			l[12] = new Log(message13);
			l[13] = new Log(message14);
			l[14] = new Log(message15);
			l[15] = new Log(message16);
			l[16] = new Log(message17);
			l[17] = new Log(message18);

		} catch (LogCreationException e) {
			assertFalse(true);
		}
		assertNotNull(l[0]);
		assertNotNull(l[1]);
		assertNotNull(l[2]);
		assertNotNull(l[4]);
		assertNotNull(l[5]);
		assertNotNull(l[6]);
		assertNotNull(l[7]);
		assertNotNull(l[8]);
		assertNotNull(l[9]);
		assertNotNull(l[10]);
		assertNotNull(l[12]);
		assertNotNull(l[13]);
		assertNotNull(l[14]);
		assertNotNull(l[15]);
		assertNotNull(l[16]);
		assertNotNull(l[17]);
	}

}
