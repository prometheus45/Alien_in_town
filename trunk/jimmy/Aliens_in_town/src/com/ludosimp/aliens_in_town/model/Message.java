package com.ludosimp.aliens_in_town.model;

/**
 * 
 * @author jimmy This class use static method to extract and build messages
 *         based on the following format: - adress ip - seperation by : -*---*-
 *         - user_name - separation by : -*---*- again - user_avatar_name -
 *         separation by : -*---*- again - type of message - separation by :
 *         -*---*- again - message
 * 
 *         There is two types of messages : chat_message or control_message The
 *         control message are specific messages where the client is not the
 *         user.
 *         
 *         Before using methods to extract it is preferable to use the method formatRespected to check.
 */
public class Message {
	public static final String CHAT_MESSAGE = "chat_message";
	public static final String CONTROL_MESSAGE = "control_message";
	public static final String CONTROL_MESSAGE_CONNECTION = "connection";
	public static final String CONTROL_MESSAGE_DISCONNECT = "disconnect";
	private static final String SEPARATION = "111sepa111";

	// Return a message format from all the necessaries parameters
	public static String createMessage(Ipv4 ip, String u, String a, String t,
			String m) {
		return ip.toString() + SEPARATION + u + SEPARATION + a + SEPARATION + t
				+ SEPARATION + m;
	}

	// Return true if the format is respected.
	public static boolean formatRespected(String message) {
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

	// Return the ipv4 address of the sender.
	public static Ipv4 getIpv4FromSender(String message) {
		return new Ipv4(message.split(SEPARATION)[0]);
	}

	// Return the user name of the sender.
	public static String getUserNameFromSender(String message) {
		return message.split(SEPARATION)[1];
	}

	// Return the user avatar name of the sender
	public static String getUserAvatarNameFromSender(String message) {
		return message.split(SEPARATION)[2];
	}

	// Return the type of message from the sender.
	public static String getTypeMessageFromSender(String message) {
		return message.split(SEPARATION)[3];
	}

	// return the message from the sender.
	public static String getMessageFromSender(String message) {
		return message.split(SEPARATION)[4];
	}

}
