import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
 
@SuppressWarnings("serial")
public class ServerInterface extends JFrame {
    private JTextArea messagesArea;
    private JButton sendButton;
    private JTextField message;
    private JButton startServer;
    private ServerChatControlInterface server;
 
    public ServerInterface(final int port) {
        super("ServerInterface");
        
        JPanel panelFields = new JPanel();
        panelFields.setLayout(new BoxLayout(panelFields,BoxLayout.X_AXIS));
 
        JPanel panelFields2 = new JPanel();
        panelFields2.setLayout(new BoxLayout(panelFields2,BoxLayout.X_AXIS));
 
        //Here we will have the text messages screen
        messagesArea = new JTextArea();
        messagesArea.setColumns(30);
        messagesArea.setRows(10);
        messagesArea.setEditable(false);
 
        //Here we add a button send for a possible interaction between the server and all the clients.
        sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get the message from the text view
                String messageText = message.getText();
                // add message to the message area
                messagesArea.append("\n" + messageText);
                // send the message to the client
                System.out.println("ici");
                
                server.sendAll(message.getText(), "");
                
                System.out.println("et la");
                System.out.println(server.getNbClients());
                // clear text
                message.setText("");
            }
        });
 
        startServer = new JButton("Start");
        startServer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // disable the start button
                startServer.setEnabled(false);
 
                //Create the object WriteMessageListener asked by the TCPServer constructor
                server = new ServerChatControlInterface(port, new ServerChatControlInterface.WriteMessageListener() {
					
					@Override
					public void writeMessage(String message) {
						messagesArea.append("\n "+message);
					}
					
				});
                server.start();
 
            }
        });
 
        //the box where the user enters the text (EditText is called in Android)
        message = new JTextField();
        message.setSize(200, 20);
 
        //add the buttons and the text fields to the panel
        panelFields.add(messagesArea);
        panelFields.add(startServer);
 
        panelFields2.add(message);
        panelFields2.add(sendButton);
 
        getContentPane().add(panelFields);
        getContentPane().add(panelFields2);
 
 
        getContentPane().setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));
 
        setSize(300, 170);
        setVisible(true);
    }
}