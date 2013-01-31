package com.example.skydrivetest1;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class SkyDriveSetting extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sky_drive_setting);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_sky_drive_setting, menu);
		return true;
	}

}
