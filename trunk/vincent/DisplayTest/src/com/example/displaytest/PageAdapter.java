package com.example.displaytest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter {

	DeadListFragment df;
	AliveListFragment af;
	ChatFragment cf;
	
	public PageAdapter(FragmentManager fm) {
		super(fm);
		df = new DeadListFragment();
		af = new AliveListFragment();
		cf = new ChatFragment();
	}

	@Override
	public Fragment getItem(int arg0) {
		switch (arg0){
		case 0: return df;
		case 1: return cf;
		case 2: return af;
		}
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}

}
