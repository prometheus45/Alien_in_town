package com.example.fragments;

import java.util.ArrayList;

import model.Client;
import model.Game;

import com.example.displaytest.R;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class AliveListFragment extends ListFragment {
	
	private ListView listview;
	Game g;
	
	public static AliveListFragment newInstance(Game g) {
        AliveListFragment f = new AliveListFragment();

        Bundle args = new Bundle();
        args.putSerializable("game", g);
        f.setArguments(args);

        return f;
    }	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.alive_layout, container, false);
		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		listview = getListView();
		if (getArguments() != null){
			g = (Game) getArguments().getSerializable("game");
			updateView(g);
		}else{
			Log.e("<<<<<<ALIVE>>>>>>", "BUNDLE NULL");
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void updateView(Game g){
		ArrayList<Client> playersAlive = g.getAlive();
		ArrayList<String> playersAlivesName = new ArrayList<String>();
		for (int i=0; i<playersAlive.size();i++){
			playersAlivesName.add(playersAlive.get(i).getAvatar().getName());
		}
		listview.setAdapter(new ArrayAdapter(this.getActivity(),android.R.layout.simple_list_item_1,playersAlivesName));
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
	}
	
}
