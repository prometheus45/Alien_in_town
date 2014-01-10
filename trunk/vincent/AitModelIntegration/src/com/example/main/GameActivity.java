package com.example.main;

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

import com.example.displaytest.R;
import com.example.horizontal.HorizontalPageAdapter;
import com.example.vertical.VerticalPageAdapter;

import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;

public class GameActivity extends FragmentActivity {
	
	
	// Use only to build a not null LogListener
	private LogListener l = new LogList.LogListener() {
		public void logMessage(String message) {
		}

		public void logMessage(String classe, String fonction, String message,
				LogType type) {
		}
	};
	// Use only to build a not null PrintWriter.
	private static Writer w = new PrintWriter(new BufferedWriter(new Writer() {
		public void write(char[] cbuf, int off, int len) throws IOException {
		}

		public void flush() throws IOException {
		}

		public void close() throws IOException {
		}
	}));
	// Use only to build a not null BufferedReader.
	private static Reader r = new Reader() {
		public int read(char[] cbuf, int off, int len) throws IOException {
			return 0;
		}

		public void close() throws IOException {
		}
	};

	Game g = new Game(l, 0);

	UniPageAdapter adapter;
	ViewPager pager;
	GameThread gt;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		//create game
		PrintWriter good_print = null;
		Client client = null;
		try {
			good_print = new PrintWriter(new BufferedWriter(w));
		} catch (Exception i) {

		}

		BufferedReader br = new BufferedReader(r);
		try {
			for (int i = 0; i < 7; i++) {
				client = new Client(new Ipv4("0.0.0.0"), 1, good_print, br,
						new Avatar("AlivePlayer" + i, "b" + i, Type.DOCTOR,
								Condition.ALIVE), new Player("a"));
				g.addClient(client);
			}
			for (int i = 0; i < 3; i++) {
				client = new Client(new Ipv4("0.0.0.0"), 1, good_print, br,
						new Avatar("DeadPlayer" + i, "c" + i, Type.DOCTOR,
								Condition.DEAD), new Player("a"));
				g.addClient(client);
			}
		} catch (IllegalArgumentException e) {
		} catch (ClientCreationException e) {
		}
		
		//////////////////////////////////////
		//CREATION DES FRAGMENTS
		
		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
			adapter = new VerticalPageAdapter(getSupportFragmentManager(),gameToBundle());
			pager = (ViewPager) findViewById(R.id.pager);
			pager.setAdapter(adapter);
			pager.setCurrentItem(1);
		} else {
			adapter =new HorizontalPageAdapter(getSupportFragmentManager(),gameToBundle());
			pager = (ViewPager) findViewById(R.id.pagerLand);
			pager.setAdapter(adapter);
		}
		
		gt = new GameThread();
		gt.execute();
		
	}
	
	public Bundle gameToBundle(){
		ArrayList<Client> playersAlive = g.getAlive();
		ArrayList<String> playersAlivesName = new ArrayList<String>();
		for (int i=0; i<playersAlive.size();i++){
			playersAlivesName.add(playersAlive.get(i).getAvatar().getName());
		}
		ArrayList<Client> playersDead = g.getDeads();
		ArrayList<String> playersDeadsName = new ArrayList<String>();
		for (int i=0; i<playersDead.size();i++){
			playersDeadsName.add(playersDead.get(i).getAvatar().getName());
		}
		Bundle b = new Bundle();
		b.putStringArrayList("paName", playersAlivesName);
		b.putStringArrayList("pdName", playersDeadsName);
		b.putFloat("gameTime", g.getTime());
		b.putString("playerAvatarName", g.getAvatarsNames().get(5));
		b.putString("playerAvatarType", g.getAvatarsTypes().get(5).toString());
		b.putString("playerAvatarState", g.getAvatarsStates().get(5).toString());
		
		return b;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onPause() {
		//gt.setRun(false);
		super.onPause();
	}

	@Override
	protected void onResume() {
		//gt.setRun(true);
		super.onResume();
	}
	
	public class GameThread extends AsyncTask<Void, Game, Void> {

		private boolean run = true;
		
		@Override
		protected Void doInBackground(Void... params) {
			//publishProgress(g);
			int i=0;
			while(g.getTime()<900000){
				if (run){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					g.setTime(g.getTime()+1);
					publishProgress(g);
					Log.d("[]",i+"");
				}
				i++;
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
		}

		@Override
		protected void onProgressUpdate(Game... values) {
			super.onProgressUpdate(values);
			adapter.updateViews(gameToBundle());
		}
		
		protected void setRun(boolean b){
			run = b;
		}
		
	}
	

}
