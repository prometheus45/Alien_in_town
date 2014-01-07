package com.example.main;

import com.example.displaytest.R;
import com.example.fragments_simples.ChatFragment;
import com.example.fragments_simples.InformationsFragment;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ChatInfosFragment extends Fragment {
	
	
	private ChatFragment chatfragment;
	private InformationsFragment infosfragment;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		infosfragment = new InformationsFragment();
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
		//View v = inflater.inflate(R.layout.chat_infos, container, false);
		return frame;
	}

}
