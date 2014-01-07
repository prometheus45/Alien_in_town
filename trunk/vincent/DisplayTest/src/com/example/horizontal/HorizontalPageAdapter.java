package com.example.horizontal;

import com.example.fragments.ChatInfosFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class HorizontalPageAdapter extends FragmentPagerAdapter {

	private ChatInfosFragment cf;
	private DualList dl;

	public HorizontalPageAdapter(FragmentManager fm) {
		super(fm);
		dl = new DualList();
		cf = new ChatInfosFragment();
	}

	@Override
	public Fragment getItem(int arg0) {
		switch (arg0) {
		case 0:
			return cf;
		case 1:
			return dl;
		}
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 2;
	}

}
