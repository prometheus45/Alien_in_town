package com.example.main;

import java.util.ArrayList;
import java.util.List;

import com.example.displaytest.R;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class GameListActivity extends ListActivity {

	private ListView listview;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_gamelist);
		 listview = getListView();
		 
		 ArrayList<String> ar = new ArrayList<String>();
		 ar.add("Game 1 (5/8)");
		 ar.add("Game 2 (0/8)");
		 ar.add("Game 3 (0/8)");
		 ar.add("Game 4 (0/8)");
		 displayList(ar);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void displayList(List list){
		listview.setAdapter(new ArrayAdapter(this,android.R.layout.simple_list_item_1,list));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = new Intent(this, GameActivity.class);
		startActivity(intent);
		finish();
	}
	
}
