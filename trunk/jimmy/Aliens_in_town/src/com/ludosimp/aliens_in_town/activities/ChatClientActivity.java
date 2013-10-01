package com.ludosimp.aliens_in_town.activities;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

import com.example.aliens_in_town.R;
import com.ludosimp.aliens_in_town.model.Avatar;
import com.ludosimp.aliens_in_town.model.Message;
import com.ludosimp.aliens_in_town.model.Player;
import com.ludosimp.aliens_in_town.server.TCPClient;
import com.ludosimp.aliens_in_town.utils.MathRandomSimplified;
import com.ludosimp.aliens_in_town.view.ChatViewAdapter;

public class ChatClientActivity extends Activity {
	private ListView mList;
	private ArrayList<String> arrayList;
	private ChatViewAdapter mAdapter;
	private TCPClient mTcpClient;
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
		int hasard = MathRandomSimplified.random(0, liste.length - 1);
		avatar = new Avatar(liste[hasard]);
		hasard = MathRandomSimplified.random(0, liste.length - 1);
		player = new Player(liste[hasard]);
		
		// connect to the server
		new connectTask().execute("");

		send.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				String message = editText.getText().toString();

				// add the text in the arrayList
				arrayList.add(avatar.getAvatar_name() + ": " + message);

				// sends the message to the server
				if (mTcpClient != null) {
					mTcpClient.sendMessage(player.getUser_name(),
							avatar.getAvatar_name(), message);
				}

				// refresh the list
				mAdapter.notifyDataSetChanged();
				editText.setText("");
			}
		});

	}

	public class connectTask extends AsyncTask<String, String, TCPClient> {

		@Override
		protected TCPClient doInBackground(String... message) {

			// we create a TCPClient object and
			mTcpClient = new TCPClient(new TCPClient.OnMessageReceived() {
				@Override
				// here the messageReceived method is implemented
				public void messageReceived(String message) {
					// this method calls the onProgressUpdate
					Log.e("avant", message);
					if (Message.formatRespected(message)) {
						Log.e("normalement apparait", message);
						Log.e("egal",player.getUser_name()+"*"+Message.getUserNameFromSender(message));
						if (!(Message.getUserNameFromSender(message).equals(
								player.getUser_name()))) {
							Log.e("le joueur est different", message);
							publishProgress(Message
									.getUserAvatarNameFromSender(message)
									+ ": "
									+ Message.getMessageFromSender(message));
						}
					}else{
						Log.e("Format not respected", message);
						publishProgress(Message
								.getUserAvatarNameFromSender(message)
								+ ": "
								+ Message.getMessageFromSender(message));					
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