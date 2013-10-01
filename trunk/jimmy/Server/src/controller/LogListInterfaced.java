package controller;

import java.util.ArrayList;

import model.Ipv4;
import model.Log.LogType;
import model.Log;
import model.LogList;

/**
 * 
 * @author jimmy
 * 
 *         Extends LogList and add a callback interface to implement and call to
 *         intercept the log from the application.
 */
public class LogListInterfaced extends LogList {
	private LogAddListener listener;

	/**
	 * Constructor of the LogListInterfaced class.
	 * 
	 * @param mere
	 *            The LogList before improving it to a LogListInterfaced logs
	 *            list.
	 * @param listener
	 *            The interface implemented to call back.
	 */
	public LogListInterfaced(LogList mere, LogAddListener listener) {
		if (mere.listes != null)
			this.listes = mere.listes;
		else
			this.listes = new ArrayList<Log>();
		this.listener = listener;
	}

	/**
	 * The same method from his parent but he notifies his controller when a log
	 * is done.
	 */
	@Override
	synchronized public boolean add(String message, Log.LogType type) {
		if (type != LogType.MESSAGE)
			return false;

		Log l = new Log(message, type);

		listener.logAdd(l);

		return listes.add(l);
	}

	/**
	 * The same method from his parent but he notifies his controller when a log
	 * is done.
	 */
	@Override
	synchronized public boolean add(Ipv4 ip_server, String classe,
			String fonction, String message, LogType type) {
		if (type == LogType.SUCCESS || type == LogType.ERREUR) {
			Log l = new Log(ip_server, classe, fonction, message, type);
			listener.logAdd(l);
			return listes.add(l);
		}

		return false;
	}

	/**
	 * 
	 * @author jimmy
	 * 
	 *         A log listener.
	 */
	public interface LogAddListener {
		public void logAdd(Log log);
	}

}
