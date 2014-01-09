package com.example.fragments;

import model.Game;

import com.example.displaytest.R;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ChatInfosFragment extends Fragment {
	
	
	private ChatFragment chatfragment;
	private InformationsFragment infosfragment;

	public static ChatInfosFragment newInstance(Game g) {
		ChatInfosFragment cif = new ChatInfosFragment();
		
		Bundle args = new Bundle();
		args.putSerializable("game", g);
		cif.setArguments(args);
		
		return cif;
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null){
			Game g = (Game) getArguments().getSerializable("game");
			infosfragment = InformationsFragment.newInstance(g);
			chatfragment = ChatFragment.newInstance(g);
		}else{
			Log.e("<<<<<<CHATINFOS>>>>>>", "BUNDLE NULL");
		}
		chatfragment = new ChatFragment();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup frame = (ViewGroup) inflater.inflate(R.layout.chat_infos, container, false);
        if (savedInstanceState == null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
	            ft.add(R.id.infosViewVert, infosfragment);
	            ft.add(R.id.chatViewVert, chatfragment).commit();
            }else{
            	ft.add(R.id.infosView, infosfragment);
	            ft.add(R.id.chatView, chatfragment).commit();
            }
        }
		return frame;
	}
	public ChatFragment getChatfragment() {
		return chatfragment;
	}
	public InformationsFragment getInfosfragment() {
		return infosfragment;
	}

}
