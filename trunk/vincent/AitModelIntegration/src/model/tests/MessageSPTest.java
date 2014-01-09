package model.tests;

import model.Ipv4;
import model.MessageSP;
import junit.framework.TestCase;

public class MessageSPTest extends TestCase {

	public void testDisconnect() {
		String message = MessageSP.disconnect(new Ipv4("0.0.0.0"), 1,0);	
		
		assertNotNull(message);
		assertNull(MessageSP.disconnect(null, 1,0));
		assertNull(MessageSP.disconnect(null, -1,0));
		assertNull(MessageSP.disconnect(new Ipv4("0.0.0.0"), null,0));
	}

}
