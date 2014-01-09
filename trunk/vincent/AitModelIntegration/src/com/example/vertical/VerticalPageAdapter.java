package com.example.vertical;

import model.Game;

import com.example.fragments.AliveListFragment;
import com.example.fragments.ChatFragment;
import com.example.fragments.ChatInfosFragment;
import com.example.fragments.DeadListFragment;
import com.example.fragments.InformationsFragment;
import com.example.main.UniPageAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class VerticalPageAdapter extends UniPageAdapter {
	
	private DeadListFragment dl;
	private AliveListFragment al;
	private ChatInfosFragment ci;

	public VerticalPageAdapter(FragmentManager fm, Game g) {
		super(fm,g);
		ci = ChatInfosFragment.newInstance(g);
		dl = DeadListFragment.newInstance(g);
		al = AliveListFragment.newInstance(g);
		POS_ALIVE = 0;
		POS_DEAD = 2;
		POS_CHAT = 1;
		POS_INFOS = 1;
	}
	
	@Override
	public Fragment getItem(int arg0) {
		switch (arg0) {
		case 0:
			return al;
		case 1:
			return ci;
		case 2:
			return dl;
		}
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public AliveListFragment getAliveFragment() {
		return al;
	}

	@Override
	public DeadListFragment getDeadFragment() {
		return dl;
	}

	@Override
	public InformationsFragment getInfosFragment() {
		return ci.getInfosfragment();
	}

	@Override
	public ChatFragment getChatFragment() {
		return ci.getChatfragment();
	}
	
}
