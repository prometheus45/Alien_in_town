package com.example.horizontal;

import com.example.fragments.AliveListFragment;
import com.example.fragments.ChatFragment;
import com.example.fragments.ChatInfosFragment;
import com.example.fragments.DeadListFragment;
import com.example.fragments.DualList;
import com.example.fragments.InformationsFragment;
import com.example.main.UniPageAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class HorizontalPageAdapter extends UniPageAdapter {
	
	private ChatInfosFragment ci;
	private DualList dl;
	private Bundle b;

	public HorizontalPageAdapter(FragmentManager fm, Bundle b){
		super(fm,b);
		ci = ChatInfosFragment.newInstance(b);
		dl = DualList.newInstance(b);
		this.b = b;
		POS_ALIVE = 1;
		POS_DEAD = 1;
		POS_CHAT = 0;
		POS_INFOS = 0;
	}
	
	@Override
	public Fragment getItem(int arg0) {
		switch (arg0) {
		case 0:
			return ci;
		case 1:
			return dl;
		}
		return null;
	}

	@Override
	public int getCount() {
		return 2;
	}

	@Override
	public AliveListFragment getAliveFragment() {
		return dl.getAliveList();
	}

	@Override
	public DeadListFragment getDeadFragment() {
		return dl.getDeadList();
	}

	@Override
	public InformationsFragment getInfosFragment() {
		return ci.getInfosfragment();
	}

	@Override
	public ChatFragment getChatFragment() {
		return ci.getChatfragment();
	}

	public Bundle getBundle() {
		return b;
	}

	public void setBundle(Bundle b) {
		this.b = b;
	}
	
	

}
