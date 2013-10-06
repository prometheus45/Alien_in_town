package com.ludosimp.aliens_in_town.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.aliens_in_town.R;
import com.ludosimp.aliens_in_town.models.Avatar;
import com.ludosimp.aliens_in_town.models.Avatar.Condition;
import com.ludosimp.aliens_in_town.models.Message;
import com.ludosimp.aliens_in_town.models.MessageCG;
import com.ludosimp.aliens_in_town.models.MessageSG;
import com.ludosimp.aliens_in_town.models.MessageSS;
import com.ludosimp.aliens_in_town.models.Player;
import com.ludosimp.aliens_in_town.models.Avatar.Type;
import com.ludosimp.aliens_in_town.server.ClientServer;
import com.ludosimp.aliens_in_town.utils.RandomUtil;
import com.ludosimp.aliens_in_town.view.ChatViewAdapter;

public class ChatClientActivity extends Activity {
	private ListView mList;
	private ArrayList<String> arrayList;
	private ChatViewAdapter mAdapter;
	private ClientServer mTcpClient;
	private Avatar avatar;
	private Player player;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		arrayList = new ArrayList<String>();

		final EditText editText = (EditText) findViewById(R.id.editText);
		Button send = (Button) findViewById(R.id.send_button);

		// relate the listView from java to the one created in xml
		mList = (ListView) findViewById(R.id.list);
		mAdapter = new ChatViewAdapter(this, arrayList);
		mList.setAdapter(mAdapter);

		/**
		 * CREATION ICI POUR LE MOMENT DE AVATAR ET PLAYER OBJETS TODO
		 */

		String[] liste = getResources().getStringArray(
				R.array.names_players_list);
		int hasard = RandomUtil.random(0, liste.length - 1);
		avatar = new Avatar(liste[hasard], liste[hasard], Type.DOCTOR, Condition.ALIVE);
		hasard = RandomUtil.random(0, liste.length - 1);
		//player = new Player(liste[hasard]);
		player = new Player("TestDisconnect");

		send.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				String message = editText.getText().toString();

				// add the text in the arrayList
				arrayList.add(avatar.getName() + ": " + message);

				// sends the message to the server
				if (mTcpClient != null) {
					mTcpClient.sendMessage(message);
				}

				// refresh the list
				mAdapter.notifyDataSetChanged();
				editText.setText("");
			}
		});

	}
	
	@Override
	public void onResume(){
		super.onResume();
		Log.e("test", "In onResume");
		new connectTask().execute("");
	}
	
	@Override
	public void onPause(){
		super.onPause();
		Log.e("test", "InPause");
		mTcpClient.sendDisconnect();
		mTcpClient.stopClient();
	}

	public class connectTask extends AsyncTask<String, String, ClientServer> {

		@Override
		protected ClientServer doInBackground(String... message) {

			// we create a TCPClient object and
			mTcpClient = new ClientServer(avatar, player, new ClientServer.OnReceived() {
				@Override
				// here the messageReceived method is implemented
				public void messageReceived(String message) {
					// this method calls the onProgressUpdate
					String type = Message.getTypeMessage(message);
					
					Log.e("message", message);
					Log.e("type", type);
					
					if(type.equals(MessageCG.CG_CHAT_NAME)){
						String poster = Message.getParam(message, MessageCG.CG_CHAT_POST_NAME);
						String from = Message.getParam(message, MessageCG.CG_CHAT_PLAYER_NAME);
						String mess = Message.getParam(message, MessageCG.CG_CHAT_MESSAGE);
						if(!player.getName().equals(from))
							publishProgress(poster + ": " + mess);
					}
					if(type.equals(MessageSG.SG_CHAT_NAME)){
						String mess = Message.getParam(message, MessageSG.SG_CHAT_MESSAGE);
						publishProgress(mess);
						Log.e("test", "envoie sg mess");
					}
					if(type.equals(MessageSS.SS_CHAT_NAME)){
						String mess = Message.getParam(message, MessageSS.SS_CHAT_MESSAGE);
						publishProgress(mess);
						Log.e("test", "envie ss mess");
					}else{
						Log.e("test", type+ " !="+MessageSS.SS_CHAT_MESSAGE);
					}
				}
			});
			mTcpClient.run();

			return null;
		}

		@Override
		protected void onProgressUpdate(String... values) {
			super.onProgressUpdate(values);

			// in the arrayList we add the messaged received from server
			arrayList.add(values[0]);
			// notify the adapter that the data set has changed. This means that
			// new message received
			// from server was added to the list
			mAdapter.notifyDataSetChanged();
		}

	}
}