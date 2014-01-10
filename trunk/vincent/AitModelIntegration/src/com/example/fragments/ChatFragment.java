package com.example.fragments;

import com.example.displaytest.R;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ChatFragment extends Fragment {
	

	public static ChatFragment newInstance(Bundle b){
		ChatFragment c = new ChatFragment();
		
		c.setArguments(b);
		
		return c;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.chat_layout, container, false);
		return v;
	}
	
	public void updateView(Bundle b){
		
	}
}
