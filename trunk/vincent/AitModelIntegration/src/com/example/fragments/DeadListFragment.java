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

public class DeadListFragment extends ListFragment {

	private ListView listview;
	Game g;
	
	public static DeadListFragment newInstance(Game g) {
        DeadListFragment f = new DeadListFragment();

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
		View v = inflater.inflate(R.layout.dead_layout, container, false)
				;
		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		listview = getListView();
		if (getArguments() != null){
			g = (Game)getArguments().getSerializable("game");
			updateView(g);
		}else{
			Log.e("<<<<<<DEAD>>>>>>", "BUNDLE NULL");
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void updateView(Game g){

		ArrayList<Client> playersDead = g.getDeads();
		ArrayList<String> playersDeadsName = new ArrayList<String>();
		for (int i=0; i<playersDead.size();i++){
			playersDeadsName.add(playersDead.get(i).getAvatar().getName());
		}
		listview.setAdapter(new ArrayAdapter(this.getActivity(),android.R.layout.simple_list_item_1,playersDeadsName));
	}
}
