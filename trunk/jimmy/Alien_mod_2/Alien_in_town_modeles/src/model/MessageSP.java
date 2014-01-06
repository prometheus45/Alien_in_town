package model;

public class MessageSP {
	
	//Only here to show the possible type of message.
	public enum ServerProblem { DISCONNECT };
	
	// The disconnect problem message for a server.
	public static final String SP_DISCONNECT_NAME = "sp_disconnect";
	public static final int SP_DISCONNECT_NUM_PARAMS = 0;
	
	public static String disconnect(Ipv4 ip, Integer port, Integer id_game) {
		if (ip == null || port == null)
			return null;
		if(port < 0)return null;
		
		String elem_father = Message.createMessage(ip, port, id_game,
				SP_DISCONNECT_NAME, null);
		return elem_father;

	}
	
}
