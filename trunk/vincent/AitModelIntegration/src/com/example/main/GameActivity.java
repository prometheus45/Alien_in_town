package com.example.main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;

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
			adapter = new VerticalPageAdapter(getSupportFragmentManager(),g);
			pager = (ViewPager) findViewById(R.id.pager);
			pager.setAdapter(adapter);
			pager.setCurrentItem(1);
		} else {
			adapter =new HorizontalPageAdapter(getSupportFragmentManager(),g);
			pager = (ViewPager) findViewById(R.id.pagerLand);
			pager.setAdapter(adapter);
		}
		
		GameThread gt = new GameThread();
		gt.execute();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}
	
	public class GameThread extends AsyncTask<Void, Game, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			while(g.getTime()<900000){
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				g.setTime(g.getTime()+666);
				publishProgress(g);
				//adapter.notifyDataSetChanged();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
		}

		@Override
		protected void onProgressUpdate(Game... values) {
			super.onProgressUpdate(values);
			adapter.updateViews(g);
		}
		
	}
	

}
