package model;

import java.io.Serializable;
import java.util.ArrayList;

import model.Avatar.Condition;
import model.Avatar.Type;
import model.LogList.LogListener;
import model.MessageSS.LogType;
import model.exceptions.AvatarSettingException;

public class Game extends ClientManager implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private float time;

	/**
	 * Constructor of a game, no parameter specified.
	 */
	public Game(LogListener l, int id) {
		super(l);
		this.setId(id);
		setTime(0);
	}

	/**
	 * Save the time when the game started.
	 */
	public void startGame() {
		setTime(System.currentTimeMillis());
	}

	/**
	 * Return the list of the clients with dead avatars. 
	 * @return a list of clients.
	 */
	public ArrayList<Client> getDeads() {
		ArrayList<Client> l = new ArrayList<Client>();
		Client inter;
		for (int i=0; i<this.nbClients; i++){
			inter = this.tabClients.get(i);
			if (inter.getAvatar().getState() == Condition.DEAD)
				l.add(inter);
		}
		return l;
	}
	
	/**
	 * Return the list of the clients with avatars alive.
	 * @return a list of clients
	 */
	public ArrayList<Client> getAlive() {
		ArrayList<Client> l = new ArrayList<Client>();
		Client inter;
		for (int i=0; i<this.nbClients; i++){
			inter = this.tabClients.get(i);
			if (inter.getAvatar().getState() == Condition.ALIVE)
				l.add(inter);
		}
		return l;
	}
	
	/**
	 * Get a client with a name matching the avatar name parameter
	 * @param name the name of the avatar to get
	 * @return a client if present otherwise null.
	 */
	public Client getByAvatarName(String name){
		Client inter;
		String m = "getByAvatarName";
		for (int i=0; i<this.nbClients; i++){
			inter = this.tabClients.get(i);
			if (inter.getAvatar().getName().equals(name))
				return inter;
		}
		
		l(m, "No avatar name matching", LogType.ERROR);
		
		return null;
	}
	
	/**
	 * Get a client with an avatar with a poser name matching the parameter
	 * @param poster the poster name of the avatar to get
	 * @return a client if present otherwise null
	 */
	public Client getByPosterName(String poster){
		Client inter;
		String m = "getByPosterName";
		for (int i=0; i<this.nbClients; i++){
			inter = this.tabClients.get(i);
			if (inter.getAvatar().getPoster().equals(poster))
				return inter;
		}
		
		l(m, "No poster name matching", LogType.ERROR);
		return null;
	}
	

	/**
	 * Generate the avatars of the clients list by building them with the parameters.
	 * @param names the names of the avatars
	 * @param posts the posts_names of the avatars
	 * @param types the types of the avatars.
	 * @param states the condition of the avatars.
	 */
	public boolean setAvatars(ArrayList<String> names, ArrayList<String> posts,
			ArrayList<String> types, ArrayList<String> states) {
		
		String m = "setAvatars";
		Client inter;
		System.out.println("le");
		Avatar a;
		for (int i=0; i<names.size(); i++){
			inter = this.tabClients.get(i);
			System.out.println("la");
			try {
				a = new Avatar(names.get(i), posts.get(i), types.get(i), states.get(i));
				System.out.println("lo");
				inter.setAvatar(a);
				System.out.println("ici");
			} catch (AvatarSettingException e) {
				l(m, e.getMessage(), LogType.ERROR);
				return false;
			} catch (ArrayIndexOutOfBoundsException e){
				l(m, e.getMessage(), LogType.ERROR);
				return false;
			}
			
		}
		return true;
		
	}
	
	/**
	 * Return the list of the avatars types.
	 */
	public ArrayList<Type> getAvatarsTypes(){
		ArrayList<Type> l = new ArrayList<Type>();
		Client inter;
		for (int i=0; i<this.nbClients; i++){
			inter = this.tabClients.get(i);
			l.add(inter.getAvatar().getType());
		}
		return l;
	}
	
	/**
	 * Return the list of the avatars names.
	 */
	public ArrayList<String> getAvatarsNames(){
		ArrayList<String> l = new ArrayList<String>();
		Client inter;
		for (int i=0; i<this.nbClients; i++){
			inter = this.tabClients.get(i);
			l.add(inter.getAvatar().getName());
		}
		return l;
	}
	
	/**
	 * Return the list of the avatars states.
	 */
	public ArrayList<Condition> getAvatarsStates(){
		ArrayList<Condition> l = new ArrayList<Condition>();
		Client inter;
		for (int i=0; i<this.nbClients; i++){
			inter = this.tabClients.get(i);
			l.add(inter.getAvatar().getState());
		}
		return l;
	}
	
	/**
	 * Return the list of the avatars posts_names.
	 */
	public ArrayList<String> getAvatarsPosts(){
		ArrayList<String> l = new ArrayList<String>();
		Client inter;
		for (int i=0; i<this.nbClients; i++){
			inter = this.tabClients.get(i);
			l.add(inter.getAvatar().getPoster());
		}
		return l;
	}

	public float getTime() {
		return time;
	}

	public void setTime(float time) {
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	

}
