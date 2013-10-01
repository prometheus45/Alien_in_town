package model.tests;

import java.net.InetAddress;
import java.net.UnknownHostException;

import model.Ipv4;
import model.Log;
import model.Log.LogType;
import model.Message;
import model.exceptions.IllegalIpException;
import junit.framework.TestCase;

/**
 * @author jimmy
 *	Test for Log class.
 */
public class LogTest extends TestCase {
private final String G = "good";	//Means good value.

	//Test for the constructor for message message type log.
	public void testLogStringLogType() {
		
		//We create an ipv4 ip to test.
		Ipv4 good_ip=null;
		boolean good_ip_bool = true;
		Log log = null;
		try {
			good_ip = new Ipv4(InetAddress.getByName("123.123.123.123"));
		} catch (UnknownHostException e) {
			good_ip_bool = false;
		} catch (IllegalIpException e) {
			good_ip_bool = false;
		}
		assertTrue(good_ip_bool);
		
		//WE CREATE SOME BAD CASES HERE 
		
		//A null in ip.
		String semi_bad_mess0 = Message.createMessage(null,    G, G, Message.CHAT_MESSAGE,  G);
		
		//An empty string in user_name
		String semi_bad_mess1 = Message.createMessage(good_ip,"", G, Message.CHAT_MESSAGE,  G);
		
		//An empty string in avatar_name
		String semi_bad_mess2 = Message.createMessage(good_ip, G,"", Message.CHAT_MESSAGE,  G);
		
		//A bad value for the type of message.
		String semi_bad_mess3 = Message.createMessage(good_ip, G, G,                "bad",  G);
		
		//An empty string at the end of the message.
		String semi_bad_mess4 = Message.createMessage(good_ip, G, G, Message.CHAT_MESSAGE,  "");
		
		//A bad value for the last param with a control message at the third position.
		String semi_bad_mess5 = Message.createMessage(good_ip, G, G, Message.CONTROL_MESSAGE, "bad");
		
		log = new Log(semi_bad_mess0, LogType.MESSAGE);
		assertEquals(log.getIp(), Log.DEFAULT_IP);
		assertEquals(log.getDetail1(), G);
		assertEquals(log.getDetail2(), G);
		assertEquals(log.getDetail3(), Message.CHAT_MESSAGE);
		assertEquals(log.getMessage(), G);
		
		log = new Log(semi_bad_mess1, LogType.MESSAGE);
		assertEquals(log.getIp().toString(), good_ip.toString());
		assertEquals(log.getDetail1(), Log.DEFAULT_STRING);
		assertEquals(log.getDetail2(), G);
		assertEquals(log.getDetail3(), Message.CHAT_MESSAGE);
		assertEquals(log.getMessage(), G);
		
		log = new Log(semi_bad_mess2, LogType.MESSAGE);
		assertEquals(log.getIp().toString(), good_ip.toString());
		assertEquals(log.getDetail1(), G);
		assertEquals(log.getDetail2(), Log.DEFAULT_STRING);
		assertEquals(log.getDetail3(), Message.CHAT_MESSAGE);
		assertEquals(log.getMessage(), G);
		
		log = new Log(semi_bad_mess3, LogType.MESSAGE);
		assertEquals(log.getIp().toString(), good_ip.toString());
		assertEquals(log.getDetail1(), G);
		assertEquals(log.getDetail2(), G);
		assertEquals(log.getDetail3(), Log.DEFAULT_STRING);
		assertEquals(log.getMessage(), G);
		
		log = new Log(semi_bad_mess4, LogType.MESSAGE);
		assertEquals(log.getIp().toString(), good_ip.toString());
		assertEquals(log.getDetail1(), G);
		assertEquals(log.getDetail2(), G);
		assertEquals(log.getDetail3(), Message.CHAT_MESSAGE);
		assertEquals(log.getMessage(), Log.DEFAULT_STRING);
		
		log = new Log(semi_bad_mess5, LogType.MESSAGE);
		assertEquals(log.getIp().toString(), good_ip.toString());
		assertEquals(log.getDetail1(), G);
		assertEquals(log.getDetail2(), G);
		assertEquals(log.getDetail3(), Message.CONTROL_MESSAGE);
		assertEquals(log.getMessage(), Log.DEFAULT_STRING);
		
		//A message null in parameter.
		log = new Log(null, LogType.MESSAGE);
		assertEquals(log.getIp(), Log.DEFAULT_IP);
		assertEquals(log.getDetail1(), Log.DEFAULT_STRING);
		assertEquals(log.getDetail2(), Log.DEFAULT_STRING);
		assertEquals(log.getDetail3(), Log.DEFAULT_STRING);
		assertEquals(log.getMessage(), Log.DEFAULT_STRING);
		
	}

	//Test for the constructor of system log.
	public void testLogIpv4StringStringStringLogType() {
		
		//Create an ipv4 to test.
		Ipv4 good_ip=null;
		boolean good_ip_bool = true;
		Log log = null;
		try {
			good_ip = new Ipv4(InetAddress.getByName("123.123.123.123"));
		} catch (UnknownHostException e) {
			good_ip_bool = false;
		} catch (IllegalIpException e) {
			good_ip_bool = false;
		}
		assertTrue(good_ip_bool);
		
		//Bad case, log type fail.
		log = new Log(good_ip, "classe", "fonction", "message", LogType.MESSAGE);
		
		//In this case all the params go to default.
		assertEquals(log.getIp().toString(), Log.DEFAULT_IP.toString());
		assertEquals(log.getDetail1(), Log.DEFAULT_STRING);
		assertEquals(log.getDetail2(), Log.DEFAULT_STRING);
		assertEquals(log.getDetail3(), "error");
		assertEquals(log.getMessage(), Log.DEFAULT_STRING);
		
		//A good case.
		log = new Log(good_ip, "classe", "fonction", "message", LogType.ERREUR);
		assertEquals(log.getIp().toString(), good_ip.toString());
		assertEquals(log.getDetail1(), "classe");
		assertEquals(log.getDetail2(), "fonction");
		assertEquals(log.getDetail3(), "error");
		assertEquals(log.getMessage(), "message");
	}

}
