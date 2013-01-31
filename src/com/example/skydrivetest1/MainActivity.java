package com.example.skydrivetest1;

import java.util.Arrays;
import java.util.HashMap;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.widget.Toast;

import com.example.config.Config;
import com.example.config.SkyDriveApplication;
import com.microsoft.live.LiveAuthClient;
import com.microsoft.live.LiveAuthException;
import com.microsoft.live.LiveAuthListener;
import com.microsoft.live.LiveConnectClient;
import com.microsoft.live.LiveConnectSession;
import com.microsoft.live.LiveStatus;

public class MainActivity extends Activity {

	private SkyDriveApplication sdapp;
	private LiveAuthClient authClient;
	private ProgressDialog progress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		sdapp = (SkyDriveApplication) getApplication();
		authClient = new LiveAuthClient(sdapp, Config.CLIENT_ID);
		sdapp.setAuthClient(authClient);
		
		//wait a period of time to load login action.
		new Handler().postDelayed(new Runnable() {
            public void run() {
                /* Create an Intent that will start the Main WordPress Activity. */
            	progress = ProgressDialog.show(MainActivity.this, "", "Loading Please wait...");

        		AuthUser(); 
            }
        }, 2900);
		//progress = ProgressDialog.show(this, "", "Loading Please wait...");

		//AuthUser();
	}

	
	private void AuthUser(){
		authClient.initialize(Arrays.asList(Config.SCOPES), new LiveAuthListener() {
            @Override
            public void onAuthError(LiveAuthException exception, Object userState) {
            	showToast("Error occurs when try to login");
            }

            @Override
            public void onAuthComplete(LiveStatus status,
                                       LiveConnectSession session,
                                       Object userState) {
                if (status == LiveStatus.CONNECTED) {
                	gotoSkyDriveActivity(session);
                } else {
                	authClient.login(MainActivity.this, Arrays.asList(Config.SCOPES), new LiveAuthListener(){

						@Override
						public void onAuthComplete(LiveStatus status,
								LiveConnectSession session, Object userState) {
							// TODO Auto-generated method stub
							progress.dismiss();
							if(status ==LiveStatus.CONNECTED){
								gotoSkyDriveActivity(session);
								finish();
							}
							else{
								showToast("Login did not connect. Status is " + status + ".");
							}
						}

						@Override
						public void onAuthError(LiveAuthException exception,
								Object userState) {
							// TODO Auto-generated method stub
							finish();
						}
                		
                	});
                }
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	private void gotoSkyDriveActivity(LiveConnectSession session){
		sdapp.setSession(session);
        sdapp.setConnectClient(new LiveConnectClient(session));
        startActivity(new Intent(getApplicationContext(),SkyDriveActivity.class));
	}
	
	private void showToast(String message) {
		Toast.makeText(this, message, Toast.LENGTH_LONG).show();
	}
}
