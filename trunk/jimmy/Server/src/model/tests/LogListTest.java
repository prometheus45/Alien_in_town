package model.tests;

import java.util.ArrayList;

import model.Ipv4;
import model.Log;
import model.LogList;
import model.Log.LogType;
import junit.framework.TestCase;

/**
 * 
 * @author jimmy
 * 
 * Test for the LogList class.
 */
public class LogListTest extends TestCase {

	//Test for the simple constructor.
	public void testLogList() {
		LogList list = new LogList();
		assertTrue(list != null);
	}

	//Test for the constructor with an array.
	public void testLogListArrayListOfLog() {
		Ipv4 ip = new Ipv4("0.0.0.0");
		
		ArrayList<Log> liste = new ArrayList<Log>();
		liste.add(new Log(ip, "classe1", "fonction", "message", LogType.ERREUR));
		liste.add(new Log(ip, "classe2", "fonction", "message", LogType.ERREUR));
		
		LogList list = new LogList(liste);
		assertEquals(list.get(0).getDetail1(), "classe1");
		assertEquals(list.get(1).getDetail1(), "classe2");
	}

	//Test the add for a message log type.
	public void testAddStringLogType() {
		LogList list = new LogList();
		
		//Case the case type null;
		assertFalse(list.add("message", null));
		
		//Case accept message null.
		assertTrue(list.add(null, LogType.MESSAGE));
		
		//Case refuse not good log types.
		assertFalse(list.add("message", LogType.ERREUR));
		assertFalse(list.add("message", LogType.SUCCESS));
		
		//Good case
		assertTrue(list.add("message", LogType.MESSAGE));
	}

	//Test the add for a success or error log.
	public void testAddIpv4StringStringStringLogType() {
		Ipv4 ip = new Ipv4("0.0.0.0");
		LogList list = new LogList();
		
		//Test the case type null.
		assertFalse(list.add(ip, null, "fonction", "message", null));
		
		//Test a bad log type.
		assertFalse(list.add(ip, "classe", "fonction", "message", LogType.MESSAGE));
		
		//Test a null insertion.
		assertTrue(list.add(null, null, null, null, LogType.SUCCESS));
		
		//Test the best possible log .
		assertTrue(list.add(ip, "classe", "fonction", "message", LogType.ERREUR));
	}

	//Test the remove method.
	public void testRemove() {
		Ipv4 ip = new Ipv4("0.0.0.0");
		
		ArrayList<Log> liste = new ArrayList<Log>();
		liste.add(new Log(ip, "classe1", "fonction", "message", LogType.ERREUR));
		liste.add(new Log(ip, "classe2", "fonction", "message", LogType.ERREUR));
		
		LogList list = new LogList(liste);
		
		//We remove two times the first element.
		assertTrue(list.remove(0).getDetail1().equals("classe1"));
		assertTrue(list.remove(0).getDetail1().equals("classe2"));
	}

}
