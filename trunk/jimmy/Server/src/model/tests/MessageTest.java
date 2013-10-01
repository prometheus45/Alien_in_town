package model.tests;

import model.Ipv4;
import model.Message;
import junit.framework.TestCase;
/**
 * 
 * @author jimmy
 * Test for the Message class.
 */
public class MessageTest extends TestCase {
	private final String SEPARATION = "111sepa111";
	private final Ipv4 ip = new Ipv4("162.120.120.120");

	//The test for the static constructor of Message format.
	public void testCreateMessage() {
		String totest = Message.createMessage(ip, "titi", "toto",
				Message.CHAT_MESSAGE, "message");
		assertEquals(totest, ip + SEPARATION + "titi" + SEPARATION + "toto"
				+ SEPARATION + Message.CHAT_MESSAGE + SEPARATION + "message");
	}

	//Some bad address to test.
	private final String bad_address1 = 
			"162.120.120.120111sepa111Toto111sepa111 111sepa111chat_message111sepa111Slt";
	private final String bad_address2 = 
			"162.120.120.120111sepa111Toto111sepa111111sepa111chat_message111sepa111Slt";
	private final String bad_address3 = 
			"162.120.120.120111sepa111Toto111sepa111Titi111sepa111control_message111sepa111Slt";
	
	//Some good address to test.
	private final String good_address1 = 
			"162.120.120.120111sepa111Toto111sepa111Titi111sepa111chat_message111sepa111Slt";
	private final String good_address2 = 
			"162.120.120.120111sepa111Toto111sepa111Titi111sepa111control_message111sepa111connection";
	private final String good_address3 = 
			"162.120.120.120111sepa111Toto111sepa111Titi111sepa111control_message111sepa111disconnect";

	//Test to see if the formatRespected method works.
	public void testFormatRespected() {
		assertEquals(Message.formatRespected(bad_address1),false);
		assertEquals(Message.formatRespected(bad_address2),false);
		assertEquals(Message.formatRespected(bad_address3),false);
		assertEquals(Message.formatRespected(good_address1),true);
		assertEquals(Message.formatRespected(good_address2),true);
		assertEquals(Message.formatRespected(good_address3),true);
	}

	public void testGetIpv4FromSender() {
		assertEquals(ip.toString(), Message.getIpv4FromSender(good_address1).toString());
	}

	public void testGetUserNameFromSender() {
		assertEquals("Toto", Message.getUserNameFromSender(good_address1));
	}

	public void testGetUserAvatarNameFromSender() {
		assertEquals("Titi", Message.getUserAvatarNameFromSender(good_address1));
	}

	public void testGetTypeMessageFromSender() {
		assertEquals("chat_message", Message.getTypeMessageFromSender(good_address1));
	}

	public void testGetMessageFromSender() {
		assertEquals("Slt", Message.getMessageFromSender(good_address1));
	}

}
