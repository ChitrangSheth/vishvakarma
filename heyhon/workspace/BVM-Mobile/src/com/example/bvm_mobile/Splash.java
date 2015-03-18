package com.example.bvm_mobile;

import java.io.File;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.net.http.HttpResponseCache;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Splash extends Activity {
	ImageView imageview;
	Button b;
	public Splash() {
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		
	}

	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();	
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		settingcache();
		imageview=(ImageView)findViewById(R.id.splash_image);
		Thread timer=new Thread(){
			public void run(){
				try{
					sleep(1500);
				}
				catch(Exception e)
				{}
				finally{
					Intent intent=new Intent("com.example.bvm_mobile.MAINACTIVITY");
					startActivity(intent);
				}
			}
		};
		timer.start();
		/*try
		{
			Thread.sleep(3000);
		}
		catch(Exception e)
		{}*/
		
	}
	private void settingcache() {
		// TODO Auto-generated method stub
		try {
            File httpCacheDir = new File(getApplicationContext().getExternalCacheDir()
                    , "http");
            long httpCacheSize = 10 * 1024 * 1024; // 10 MiB
            HttpResponseCache.install(httpCacheDir, httpCacheSize);
            Log.e("mainactiv","cache will save "+httpCacheDir);
          } catch (IOException e) {
            Log.e("mainactiv","io problem in setting cache"+e.toString());
          }     
	}

}
