import javax.swing.JFrame;


public class Main {

	public static final int PORT = 4444;
	
	public static void main(String args[]){

	        ServerInterface frame = new ServerInterface(PORT);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.pack();
	        frame.setVisible(true);
	}
}
