package com.example.graphictest;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Graphics extends Activity  {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		CanvasTest ct = new CanvasTest(this);    
		setContentView(ct);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.graphics, menu);
		return true;
	}

}
