package com.example.fragments;

import java.util.ArrayList;

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
	
	public static AliveListFragment newInstance(Bundle b) {
        AliveListFragment f = new AliveListFragment();
        f.setArguments(b);
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
			updateView(getArguments());
		}else{
			Log.e("<<<<<<ALIVE>>>>>>", "BUNDLE NULL");
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void updateView(Bundle b){
		/*
		*/
		ArrayList<String> playersAlivesName = b.getStringArrayList("paName");
		if (getActivity() != null){
			ArrayAdapter a = new ArrayAdapter(this.getActivity(),android.R.layout.simple_list_item_1,playersAlivesName);
			listview.setAdapter(a);
		}
	}
}
