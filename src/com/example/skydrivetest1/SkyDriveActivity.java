package com.example.skydrivetest1;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TabHost;

import com.example.config.SkyDriveApplication;
import com.microsoft.live.LiveAuthClient;

@SuppressWarnings("deprecation")
public class SkyDriveActivity extends TabActivity {
	private LiveAuthClient AuthClient;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sky_drive);
		
		SkyDriveApplication sdapp = (SkyDriveApplication)getApplication();
		AuthClient = sdapp.getAuthClient();
		
		TabHost tabHost = getTabHost();
		TabHost.TabSpec spec;
		Intent intent;
		
		intent = new Intent(this,SkyDriveSetting.class);
		//intent = new Intent(this,SkyDriveFiles.class);
		spec = tabHost.newTabSpec("files").setIndicator(getString(R.string.file_tab)).setContent(intent);
		tabHost.addTab(spec);
		
		//intent = new Intent(this,SkyDriveRecent.class);
		spec = tabHost.newTabSpec("recent").setIndicator(getString(R.string.recent_tab)).setContent(intent);
		tabHost.addTab(spec);
		//
		//intent = new Intent(this,SkyDriveShared.class);
		spec = tabHost.newTabSpec("shared").setIndicator(getString(R.string.share_tab)).setContent(intent);
		tabHost.addTab(spec);
		
		intent = new Intent(this,SkyDriveSetting.class);
		spec = tabHost.newTabSpec("setting").setIndicator(getString(R.string.set_tab)).setContent(intent);
		tabHost.addTab(spec);
		
		tabHost.setCurrentTab(0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_sky_drive, menu);
		return true;
	}

}
