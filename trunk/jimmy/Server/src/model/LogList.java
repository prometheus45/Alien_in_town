package model;

import java.util.ArrayList;

import model.Log.LogType;

/**
 * 
 * @author jimmy
 * 
 *         A lig of log class.
 */
public class LogList {
	public ArrayList<Log> listes;

	/**
	 * Constructor
	 */
	public LogList() {
		listes = new ArrayList<Log>();
	}

	/**
	 * Constructor with data already existing. The LogList continue with this
	 * list.
	 * 
	 * @param listes
	 *            The list to continue with.
	 */
	public LogList(ArrayList<Log> listes) {
		this.listes = listes;
	}

	/**
	 * Synchronized method to get a log from the list.
	 * 
	 * @param i
	 *            The index of the element to get.
	 * @return The element wanted in the list. Null otherwise.
	 */
	synchronized public Log get(Integer i) {
		if (i < 0 || i >= listes.size())
			return null;

		return listes.get(i);
	}

	/**
	 * Synchronized method to add a message log type.
	 * 
	 * @param message
	 *            The message with the Message format respected at the maximum.
	 * @param type
	 *            LogType, in fact only LogType.Message is accepted in this
	 *            method.
	 * @return true if the add works, false otherwise.
	 */
	synchronized public boolean add(String message, LogType type) {
		if (type == null || type != LogType.MESSAGE)
			return false;

		Log l = new Log(message, type);

		return listes.add(l);
	}

	/**
	 * Synchronized method to add a success or error log type.
	 * 
	 * @param ip_server
	 *            The ip_server for the log.
	 * @param classe
	 *            The class name for the log.
	 * @param fonction
	 *            The fonction name for the log.
	 * @param message
	 *            The message of log.
	 * @param type
	 *            The type of log. SUCCESS or ERREUR accepted.
	 * @return true if the add works. false otherwise.
	 */
	synchronized public boolean add(Ipv4 ip_server, String classe,
			String fonction, String message, LogType type) {
		if (type == null)
			return false;

		if (type == LogType.SUCCESS || type == LogType.ERREUR) {
			Log l = new Log(ip_server, classe, fonction, message, type);

			if (l != null)
				return listes.add(l);
		}
		return false;
	}

	/**
	 * Synchronized method to remove a log type.
	 * 
	 * @param i
	 *            The index of the log to remove from the list.
	 * @return The log remove from the list.
	 */
	synchronized public Log remove(Integer i) {
		return listes.remove((int) i);
	}

	/**
	 * 
	 * @author jimmy Listener use as a callback to replace all exceptions and
	 *         success messages in the server by logs.
	 */
	public interface LogListener {
		public void logMessage(String message, LogType type);

		public void logMessage(Ipv4 ip, String classe, String fonction,
				String message, LogType type);
	}

}
