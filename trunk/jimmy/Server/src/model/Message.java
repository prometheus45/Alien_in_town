package model;

/**
 * 
 * @author jimmy
 * 
 *         This class use static method to extract and build messages. The
 *         format of the message must be: the address ip, the user_name, the
 *         user_avatar_name, the type of message, the message. With each one
 *         being separate by a same separator defined in this class.
 * 
 *         There is two types of messages : chat_message or control_message. The
 *         control message are specific messages where the client is not the
 *         user.
 * 
 *         Before using methods to extract it is preferable to use the method
 *         formatRespected to check.
 */
public class Message {
	// The format specific strings:
	public static final String CHAT_MESSAGE = "chat_message";
	public static final String CONTROL_MESSAGE = "control_message";
	public static final String CONTROL_MESSAGE_CONNECTION = "connection";
	public static final String CONTROL_MESSAGE_DISCONNECT = "disconnect";
	private static final String SEPARATION = "111sepa111";

	/**
	 * Return a Message with the format respected from all the necessaries
	 * parameters
	 * 
	 * @param ip
	 *            the sender's ip
	 * @param u
	 *            the sender's user name
	 * @param a
	 *            the sender's avatar name
	 * @param t
	 *            the type of message send
	 * @param m
	 *            the message send
	 * @return A string with the format Message respected.
	 */
	public static String createMessage(Ipv4 ip, String u, String a, String t,
			String m) {

		String ipstring;

		if (ip == null)
			ipstring = "";
		else
			ipstring = ip.toString();

		if (u == null)
			u = "";
		if (a == null)
			a = "";
		if (t == null)
			t = "";
		if (m == null)
			m = "";

		return ipstring + SEPARATION + u + SEPARATION + a + SEPARATION + t
				+ SEPARATION + m;
	}

	/**
	 * Return true if the format is respected.
	 * 
	 * @param message
	 *            the string to check
	 * @return true if the format is respected. False otherwise.
	 */
	public static boolean formatRespected(String message) {
		if (message == null)
			return false;

		// We need 5 values.
		if (!(message.split(SEPARATION).length == 5))
			return false;
		// If the user name or the user avatar name or the message is empty the
		// format is not respected.
		if (getUserNameFromSender(message).trim().equals("")
				|| getUserAvatarNameFromSender(message).trim().equals("")
				|| getMessageFromSender(message).trim().equals(""))
			return false;
		// If the type of the message is not one from the two the format is not
		// respected.
		if (!(getTypeMessageFromSender(message).equals(CONTROL_MESSAGE) || getTypeMessageFromSender(
				message).equals(CHAT_MESSAGE)))
			return false;
		// If the type of the message is control type then the message must be
		// connection or disconnection.
		if (getTypeMessageFromSender(message).equals(CONTROL_MESSAGE)
				&& !(getMessageFromSender(message).equals(
						CONTROL_MESSAGE_CONNECTION) || getMessageFromSender(
						message).equals(CONTROL_MESSAGE_DISCONNECT)))
			return false;

		try {
			getIpv4FromSender(message);
		} catch (IllegalArgumentException e) {
			return false;
		}

		return true;
	}

	/**
	 * Return the sender's ipv4 address.
	 * 
	 * @param message
	 *            a Message format string.
	 * @return the ip from the Message.
	 */
	public static Ipv4 getIpv4FromSender(String message) {
		if (message == null || message.trim().equals(""))
			return null;

		try {
			message = message.split(SEPARATION)[0];
		} catch (ArrayIndexOutOfBoundsException a) {
			return null;
		}

		return new Ipv4(message);
	}

	/**
	 * Return the sender's user name.
	 * 
	 * @param message
	 *            a Message format string.
	 * @return the user name from the Message
	 */
	public static String getUserNameFromSender(String message) {
		if (message == null || message.trim().equals(""))
			return null;

		try {
			message = message.split(SEPARATION)[1];
		} catch (ArrayIndexOutOfBoundsException a) {
			return null;
		}

		return message;
	}

	/**
	 * Return the sender's avatar name.
	 * 
	 * @param message
	 *            a Message format string.
	 * @return the avatar name from the Message.
	 */
	public static String getUserAvatarNameFromSender(String message) {
		if (message == null || message.trim().equals(""))
			return null;

		try {
			message = message.split(SEPARATION)[2];
		} catch (ArrayIndexOutOfBoundsException a) {
			return null;
		}

		return message;
	}

	/**
	 * Return the type of message send.
	 * 
	 * @param message
	 *            a Message format string.
	 * @return the type of message from the Message.
	 */
	public static String getTypeMessageFromSender(String message) {
		if (message == null || message.trim().equals(""))
			return null;

		try {
			message = message.split(SEPARATION)[3];
		} catch (ArrayIndexOutOfBoundsException a) {
			return null;
		}

		return message;
	}

	/**
	 * Return the message send.
	 * 
	 * @param message
	 *            a Message format string.
	 * @return the message from the Message.
	 */
	public static String getMessageFromSender(String message) {
		if (message == null || message.trim().equals(""))
			return null;

		try {
			message = message.split(SEPARATION)[4];
		} catch (ArrayIndexOutOfBoundsException a) {
			return null;
		}

		return message;
	}

}
