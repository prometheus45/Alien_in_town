package com.ludosimp.aliens_in_town.activities;

import com.example.aliens_in_town.R;
import com.ludosimp.aliens_in_town.view.DualPageAdapter;
import com.ludosimp.aliens_in_town.view.PageAdapter;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;

public class MainActivity extends FragmentActivity {
	
	FragmentPagerAdapter adapter;
	ViewPager pager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
			adapter = new PageAdapter(getSupportFragmentManager());
			pager = (ViewPager) findViewById(R.id.pager);
			pager.setAdapter(adapter);
			pager.setCurrentItem(1);
		}else{
			adapter = new DualPageAdapter(getSupportFragmentManager());
			pager = (ViewPager) findViewById(R.id.pagerLand);
			pager.setAdapter(adapter);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
