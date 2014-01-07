package com.example.horizontal;

import com.example.displaytest.R;
import com.example.fragments_simples.AliveListFragment;
import com.example.fragments_simples.DeadListFragment;

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
