package com.example.fragments;

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

	public static InformationsFragment newInstance(Bundle b){
		InformationsFragment i = new InformationsFragment();
	
		i.setArguments(b);
		
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
			updateView(getArguments());
		}else{
			Log.e("<<<<<<INFOS>>>>>>", "BUNDLE NULL");
		}
	}
	
	public void updateView(Bundle b){
		textview.setText(b.getFloat("gameTime")+"\n\n\n"+b.getString("playerAvatarName")+"\n"+
				b.getString("playerAvatarType")+"\n"+b.getString("playerAvatarState"));
	}
	
}
