package controller;

import graphic.ServerInterface;

import javax.swing.JFrame;

import server.ServerChat;
import model.Log;
import model.Log.LogType;
import model.Message;

/**
 * 
 * @author jimmy
 * 
 *         The extension of the serverChat. A swing interface is add and the
 *         LogList is transform in a LogListInterfaced.
 * 
 */
public class ServerChatControlInterface extends ServerChat {

	/**
	 * Constructor
	 * 
	 * @param port
	 *            The port uses by the server.
	 */
	public ServerChatControlInterface(int port) {
		super(port);

		// We construct the Swing interface.
		final ServerInterface frame = new ServerInterface(
				new ServerInterface.ServerSendListener() {

					@Override
					public void messageAdd(String message) {
						message = Message.createMessage(SERVERIP, "server",
								"server", Message.CHAT_MESSAGE, message);
						printer.sendAll(message);
					}

				});

		// We improved the LogList in an LogListInterfaced class.
		logs = new LogListInterfaced(logs,
				new LogListInterfaced.LogAddListener() {

					@Override
					public void logAdd(Log log) {
						if (log.getType() == LogType.ERREUR)
							frame.add_message(log.getIp().toString(),
									log.getDetail1(), log.getDetail2(),
									log.getMessage(), frame.STYLE_ERREUR_SIMPLE);
						else if (log.getType() == LogType.SUCCESS)
							frame.add_message(log.getIp().toString(),
									log.getDetail1(), log.getDetail2(),
									log.getMessage(),
									frame.STYLE_SUCCESS_SIMPLE);
						else if (log.getDetail3().equals(Message.CHAT_MESSAGE))
							frame.add_message(log.getIp().toString(),
									log.getDetail1(), log.getDetail2(),
									log.getMessage(),
									frame.STYLE_MESSAGE_SIMPLE);
						else if (log.getDetail3().equals(
								Message.CONTROL_MESSAGE))
							frame.add_message(log.getIp().toString(),
									log.getDetail1(), log.getDetail2(),
									log.getMessage(),
									frame.STYLE_MESS_SPE_SIMPLE);
					}

				});

		// We make the swing interface visible.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

}
