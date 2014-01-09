package model.tests;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;

import model.Avatar;
import model.Client;
import model.Game;
import model.Ipv4;
import model.LogList;
import model.Player;
import model.Avatar.Condition;
import model.Avatar.Type;
import model.LogList.LogListener;
import model.MessageSS.LogType;
import model.exceptions.ClientCreationException;
import junit.framework.TestCase;

public class GameTest extends TestCase {
	
// Use only to build a not null LogListener
private LogListener l = new LogList.LogListener() {
	public void logMessage(String message) {}
	public void logMessage(String classe, String fonction,
			String message, LogType type) {
	}
};
// Use only to build a not null PrintWriter.
private static Writer w = new PrintWriter(new BufferedWriter(new Writer() {
	public void write(char[] cbuf, int off, int len) throws IOException {}
	public void flush() throws IOException {}
	public void close() throws IOException {}
}));
// Use only to build a not null BufferedReader.
private static Reader r = new Reader() {
	public int read(char[] cbuf, int off, int len) throws IOException {return 0;}
	public void close() throws IOException {}
};

	/**
	 * Private fonction only to add some client to test the game 
	 */
	private void initialize(Game g){
		// We add good client to the game.
		boolean success = true;
		PrintWriter good_print = null;
		Client client = null;
		try {
			good_print = new PrintWriter(new BufferedWriter(w));
		} catch (Exception i) {
			success = false;
		}
		assertTrue(success);
		BufferedReader br = new BufferedReader(r);
		success = true;
		try {
			for(int i=0; i<7; i++){
				client = new Client(new Ipv4("0.0.0.0"), 1, good_print, br,
						new Avatar("a", "b", Type.DOCTOR, Condition.ALIVE),
						new Player("a"));
				g.addClient(client);
			}
			for(int i=0; i<3; i++){
				client = new Client(new Ipv4("0.0.0.0"), 1, good_print, br,
						new Avatar("a", "b", Type.DOCTOR, Condition.DEAD),
						new Player("a"));
				g.addClient(client);
			}
		} catch (IllegalArgumentException e) {
			success = false;
		} catch (ClientCreationException e) {
			success = false;
		}
		assertTrue(success);
		//  ------------------------------------------------------------------------------ */		
	}
	
	
	public void testGetDeads() {
		Game g = new Game(l, 0);
		assertNotNull(g);
		
		initialize(g);
		
		ArrayList<Client> ld = g.getDeads();
		assertTrue(ld.size()==3);
	}

	public void testGetAlive() {
		Game g = new Game(l, 0);
		assertNotNull(g);
		
		initialize(g);
		
		ArrayList<Client> la = g.getAlive();
		assertTrue(la.size()==7);
	}

	public void testGetByAvatarName() {
		Game g = new Game(l, 0);
		assertNotNull(g);
		
		initialize(g);
		
		Client c = g.getByAvatarName("a");
		assertNotNull(c);
		
		c = g.getByAvatarName("b");
		assertNull(c);
		
	}

	public void testGetByPosterName() {
		Game g = new Game(l, 0);
		assertNotNull(g);
		
		initialize(g);
		
		Client c = g.getByPosterName("b");
		assertNotNull(c);
		
		c = g.getByPosterName("a");
		assertNull(c);
	}

	public void testSetAvatars() {
		Game g = new Game(l, 0);
		assertNotNull(g);
		
		initialize(g);
		
		ArrayList<String> names = new ArrayList<String>();
		names.add("a");
		names.add("b");
		
		ArrayList<String> types = new ArrayList<String>();
		types.add(Type.DOCTOR.toString());
		types.add(Type.MADMAN.toString());
		
		ArrayList<String> posts = new ArrayList<String>();
		posts.add("a");
		posts.add("b");
		
		ArrayList<String> states = new ArrayList<String>();
		states.add(Condition.ALIVE.toString());
		states.add(Condition.DEAD.toString());
		
		boolean success = g.setAvatars(names, posts, types, states);
		assertTrue(success);
		
		names.add("c");
		assertFalse(g.setAvatars(names, posts, types, states));
		
	}

	public void testGetAvatarsTypes() {
		fail("Not yet implemented");
	}

	public void testGetAvatarsNames() {
		fail("Not yet implemented");
	}

	public void testGetAvatarsStates() {
		fail("Not yet implemented");
	}

	public void testGetAvatarsPosts() {
		fail("Not yet implemented");
	}

}
