package com.ludosimp.aliens_in_town.activities;

import com.example.aliens_in_town.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DualList extends Fragment {

	private DeadListFragment dl;
	private AliveListFragment al;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.dual_lists, container, false);
		return v;
	}
}
