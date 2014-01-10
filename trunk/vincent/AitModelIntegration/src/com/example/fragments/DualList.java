package com.example.fragments;

import com.example.displaytest.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DualList extends Fragment {

	private DeadListFragment dl;
	private AliveListFragment al;
	
	public static DualList newInstance(Bundle b){
		DualList dl = new DualList();
		
		dl.setArguments(b);
		
		return dl;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		dl = DeadListFragment.newInstance(getArguments());
		al = AliveListFragment.newInstance(getArguments());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ViewGroup frame = (ViewGroup) inflater.inflate(R.layout.dual_lists, container, false);
		if (savedInstanceState == null) {
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.add(R.id.deadView, dl);
	        ft.add(R.id.aliveView, al).commit();
        }
		return frame;
	}

	public DeadListFragment getDeadList() {
		return dl;
	}

	public AliveListFragment getAliveList() {
		return al;
	}

	public void setDeadList(DeadListFragment dl) {
		this.dl = dl;
	}

	public void setAliveList(AliveListFragment al) {
		this.al = al;
	}
	
	
}
