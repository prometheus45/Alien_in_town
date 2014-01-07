package com.example.vertical;

import com.example.fragments_simples.AliveListFragment;
import com.example.fragments_simples.DeadListFragment;
import com.example.main.ChatInfosFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class VerticalPageAdapter extends FragmentPagerAdapter {

	private DeadListFragment df;
	private AliveListFragment af;
	private ChatInfosFragment cf;

	public VerticalPageAdapter(FragmentManager fm) {
		super(fm);
		cf = new ChatInfosFragment();
		af = new AliveListFragment();
		df = new DeadListFragment();

	}

	@Override
	public Fragment getItem(int arg0) {
		switch (arg0) {
		case 0:
			return df;
		case 1:
			return cf;
		case 2:
			return af;
		}
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}
}
