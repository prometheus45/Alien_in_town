package com.example.fragments;

import model.Game;

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
	
	public static DualList newInstance(Game g){
		DualList dl = new DualList();
		
		//Passage dans un bundle
		Bundle args = new Bundle();
		args.putSerializable("game", g);
		
		dl.setArguments(args);
		
		return dl;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		dl = DeadListFragment.newInstance((Game)getArguments().getSerializable("game"));
		al = AliveListFragment.newInstance((Game)getArguments().getSerializable("game"));
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
	
	
}
