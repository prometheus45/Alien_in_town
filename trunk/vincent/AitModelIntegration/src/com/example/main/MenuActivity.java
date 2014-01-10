package com.example.main;

import com.example.displaytest.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
	}

	public void displayGameList(View v){
		Intent intent = new Intent(this, GameListActivity.class);
		startActivity(intent);
	}
	public void createGame(View v){
		
	}
	
}
