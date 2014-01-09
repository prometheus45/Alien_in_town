package com.example.fragments;


import model.Game;

import com.example.displaytest.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class InformationsFragment extends Fragment{
	
	TextView textview;
	Game g;

	public static InformationsFragment newInstance(Game g){
		InformationsFragment i = new InformationsFragment();
		
		Bundle args = new Bundle();
		args.putSerializable("game", g);
		i.setArguments(args);
		
		return i;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.informations_layout, container, false);
		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		textview = (TextView) getView().findViewById(R.id.text);
		
		if (getArguments() != null){
			g = (Game) getArguments().getSerializable("game");
		}else{
			Log.e("<<<<<<INFOS>>>>>>", "BUNDLE NULL");
		}
		updateView(g);
	}
	
	public void updateView(Game g){
		textview.setText(g.getTime()+"\n\n\n"+g.getAvatarsNames().get(8)+"\n"+
				g.getAvatarsTypes().get(8).toString()+"\n"+g.getAvatarsStates().get(8).toString());
	}
	
}
