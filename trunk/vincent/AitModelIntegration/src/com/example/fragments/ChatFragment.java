package com.example.fragments;

import model.Game;

import com.example.displaytest.R;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ChatFragment extends Fragment {
	
	Game g;

	public static ChatFragment newInstance(Game g){
		ChatFragment c = new ChatFragment();
		
		Bundle args = new Bundle();
		args.putSerializable("game", g);
		c.setArguments(args);
		
		return c;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (getArguments() != null){
			g = (Game) getArguments().getSerializable("game");
		}else{
			Log.e("<<<<<<CHAT>>>>>>", "BUNDLE NULL");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.chat_layout, container, false);
		return v;
	}
	
	public void updateView(Game g){
		
	}
}
