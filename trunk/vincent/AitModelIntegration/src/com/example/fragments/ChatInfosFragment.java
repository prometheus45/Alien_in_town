package com.example.fragments;

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

	public static ChatInfosFragment newInstance(Bundle b) {
		ChatInfosFragment cif = new ChatInfosFragment();
		
		cif.setArguments(b);
		
		return cif;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null){
			infosfragment = InformationsFragment.newInstance(getArguments());
			chatfragment = ChatFragment.newInstance(getArguments());
		}else{
			Log.e("<<<<<<CHATINFOS>>>>>>", "BUNDLE NULL");
		}
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

	public void setChatfragment(ChatFragment chatfragment) {
		this.chatfragment = chatfragment;
	}

	public void setInfosfragment(InformationsFragment infosfragment) {
		this.infosfragment = infosfragment;
	}
	
}
