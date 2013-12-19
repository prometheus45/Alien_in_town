package com.ludosimp.aliens_in_town.view;

import com.ludosimp.aliens_in_town.activities.ChatClientFragment;
import com.ludosimp.aliens_in_town.activities.DualList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class DualPageAdapter extends FragmentPagerAdapter {

	private ChatClientFragment cf;
	private DualList dl;

	public DualPageAdapter(FragmentManager fm) {
		super(fm);
		dl = new DualList();
		cf = new ChatClientFragment();
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
