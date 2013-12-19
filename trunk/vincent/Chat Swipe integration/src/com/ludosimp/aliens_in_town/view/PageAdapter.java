package com.ludosimp.aliens_in_town.view;

import com.ludosimp.aliens_in_town.activities.AliveListFragment;
import com.ludosimp.aliens_in_town.activities.ChatClientFragment;
import com.ludosimp.aliens_in_town.activities.DeadListFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PageAdapter extends FragmentPagerAdapter {

	private DeadListFragment df;
	private AliveListFragment af;
	private ChatClientFragment cf;

	public PageAdapter(FragmentManager fm) {
		super(fm);
		cf = new ChatClientFragment();
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
