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

public class DeadListFragment extends ListFragment {

	private ListView listview;
	
	public static DeadListFragment newInstance(Bundle b) {
        DeadListFragment f = new DeadListFragment();
        
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
		View v = inflater.inflate(R.layout.dead_layout, container, false)
				;
		return v;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		listview = getListView();
		if (getArguments() != null){
			updateView(getArguments());
		}else{
			Log.e("<<<<<<DEAD>>>>>>", "BUNDLE NULL");
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void updateView(Bundle b){
		if (getActivity() != null ){
			ArrayList<String> playersDeadsName = b.getStringArrayList("pdName");
			listview.setAdapter(new ArrayAdapter(this.getActivity(),android.R.layout.simple_list_item_1,playersDeadsName));
		}
	}
}
