package com.example.main;

import com.example.fragments.AliveListFragment;
import com.example.fragments.ChatFragment;
import com.example.fragments.DeadListFragment;
import com.example.fragments.InformationsFragment;

import model.Game;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public abstract class UniPageAdapter extends FragmentPagerAdapter {

	protected Game g;
	public static int POS_ALIVE;
	public static int POS_DEAD;
	public static int POS_CHAT;
	public static int POS_INFOS;

	public UniPageAdapter(FragmentManager fm, Game g){
		super(fm);
		this.g = g;
	}

	public UniPageAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public abstract AliveListFragment getAliveFragment();
	public abstract DeadListFragment getDeadFragment();
	public abstract InformationsFragment getInfosFragment();
	public abstract ChatFragment getChatFragment();
	
	public void updateViews(Game g){
		getInfosFragment().updateView(g);
		getAliveFragment().updateView(g);
		getDeadFragment().updateView(g);
		getChatFragment().updateView(g);
	}

}
