package com.example.main;

import com.example.fragments.AliveListFragment;
import com.example.fragments.ChatFragment;
import com.example.fragments.DeadListFragment;
import com.example.fragments.InformationsFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public abstract class UniPageAdapter extends FragmentPagerAdapter {

	public static int POS_ALIVE;
	public static int POS_DEAD;
	public static int POS_CHAT;
	public static int POS_INFOS;

	public UniPageAdapter(FragmentManager fm,Bundle b){
		super(fm);
	}

	@Override
	public Fragment getItem(int arg0) {
		return null;
	}

	@Override
	public int getCount() {
		return 0;
	}
	
	public abstract AliveListFragment getAliveFragment();
	public abstract DeadListFragment getDeadFragment();
	public abstract InformationsFragment getInfosFragment();
	public abstract ChatFragment getChatFragment();
	
	public void updateViews(Bundle b){
		getInfosFragment().updateView(b);
		getAliveFragment().updateView(b);
		getDeadFragment().updateView(b);
		getChatFragment().updateView(b);
	}

}
