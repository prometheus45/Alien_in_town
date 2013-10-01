package model;

/**
 * 
 * @author jimmy
 * 
 *         This class is use to replace and manage the possible exception and
 *         error during the server process.
 */
public class Log {

	// The possible case for log.
	public enum LogType {
		MESSAGE, ERREUR, SUCCESS
	};

	private LogType type;
	private Ipv4 ip;
	private String detail1;
	private String detail2;
	private String detail3;
	private String message;

	// Different values for missing informations in the log.
	public static final Ipv4 DEFAULT_IP = new Ipv4("0.0.0.0");
	public static final String DEFAULT_STRING = "O_O Vide";
	private static final String SUCCESS = "success";
	private static final String ERROR = "error";

	/**
	 * Constructor for a Log, only message type.
	 * 
	 * @param message
	 *            The message with the Message format respected at the maximum.
	 * @param type
	 *            Only TypeLog.MESSAGE accepted.
	 */
	public Log(String message, LogType type) {
		type = LogType.MESSAGE;

		if (message == null) {
			this.ip = DEFAULT_IP;
			this.detail1 = DEFAULT_STRING;
			this.detail2 = DEFAULT_STRING;
			this.detail3 = DEFAULT_STRING;
			this.message = DEFAULT_STRING;
		} else {
			if (!Message.formatRespected(message)) {
				// We make sure to keep the maximum of good informations.
				try {
					this.ip = Message.getIpv4FromSender(message);
				} catch (IllegalArgumentException i) {
					this.ip = null;
				}
				if (this.ip == null)
					this.ip = DEFAULT_IP;

				this.detail1 = Message.getUserNameFromSender(message);
				if (this.detail1 == null || this.detail1.trim().equals(""))
					this.detail1 = DEFAULT_STRING;

				this.detail2 = Message.getUserAvatarNameFromSender(message);
				if (this.detail2 == null || this.detail2.trim().equals(""))
					this.detail2 = DEFAULT_STRING;

				this.detail3 = Message.getTypeMessageFromSender(message);
				if (this.detail3 == null
						|| !(this.detail3.equals(Message.CHAT_MESSAGE) || this.detail3
								.equals(Message.CONTROL_MESSAGE)))
					this.detail3 = DEFAULT_STRING;

				this.message = Message.getMessageFromSender(message);
				if (this.message == null || this.message.trim().equals(""))
					this.message = DEFAULT_STRING;

				if (this.detail3.equals(Message.CONTROL_MESSAGE)
						&& !(this.message
								.equals(Message.CONTROL_MESSAGE_CONNECTION) || this.message
								.equals(Message.CONTROL_MESSAGE_DISCONNECT)))
					this.message = DEFAULT_STRING;

			} else {
				this.ip = Message.getIpv4FromSender(message);
				this.detail1 = Message.getUserNameFromSender(message);
				this.detail2 = Message.getUserAvatarNameFromSender(message);
				this.detail3 = Message.getTypeMessageFromSender(message);
				this.message = Message.getMessageFromSender(message);
			}
		}
	}

	/**
	 * Constructor for success or error log.
	 * 
	 * @param ip_server
	 *            The ip of the server. Can be null.
	 * @param classe
	 *            The Class in which the success or error appears.
	 * @param fonction
	 *            The fonction in which the success or error appears.
	 * @param message
	 *            The Message to explain the success or error.
	 * @param type
	 *            The type, only TypeLog.SUCCESS and TypeLog.ERREUR accepted.
	 */
	public Log(Ipv4 ip_server, String classe, String fonction, String message,
			LogType type) {
		if (type != LogType.ERREUR && type != LogType.SUCCESS) {
			this.type = LogType.ERREUR;
			ip = DEFAULT_IP;
			detail1 = DEFAULT_STRING;
			detail2 = DEFAULT_STRING;
			detail3 = ERROR;
			this.message = DEFAULT_STRING;
		} else {
			this.type = type;
			if (ip_server == null)
				ip = DEFAULT_IP;
			else
				ip = ip_server;
			if (classe == null || classe.trim().equals(""))
				detail1 = DEFAULT_STRING;
			else
				detail1 = classe;
			if (fonction == null || fonction.trim().equals(""))
				detail2 = DEFAULT_STRING;
			else
				detail2 = fonction;
			if (type == LogType.SUCCESS)
				this.detail3 = SUCCESS;
			else
				this.detail3 = ERROR;
			if (message == null || message.trim().equals(""))
				this.message = DEFAULT_STRING;
			else
				this.message = message;
		}
	}

	public LogType getType() {
		return type;
	}

	public void setType(LogType type) {
		this.type = type;
	}

	public Ipv4 getIp() {
		return ip;
	}

	public void setIp(Ipv4 ip) {
		this.ip = ip;
	}

	public String getDetail1() {
		return detail1;
	}

	public void setDetail1(String detail1) {
		this.detail1 = detail1;
	}

	public String getDetail2() {
		return detail2;
	}

	public void setDetail2(String detail2) {
		this.detail2 = detail2;
	}

	public String getDetail3() {
		return detail3;
	}

	public void setDetail3(String detail3) {
		this.detail3 = detail3;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
