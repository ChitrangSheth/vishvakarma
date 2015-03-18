package com.example.bvm_mobile;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class FullScreenNews extends Activity {

	String str,fname;
	TextView textview, download;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fullscreen_scroll);
		textview = (TextView) findViewById(R.id.fullnews);
		download = (TextView) findViewById(R.id.down_textview);

		Bundle extras = getIntent().getExtras();
		str = extras.getString("key");
		fname = extras.getString("filename");
		textview.setText(str);
		//textview.setMovementMethod(new ScrollingMovementMethod());
		download.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.divyashah.in/site/data/note/" + fname)));
				Toast.makeText(FullScreenNews.this, "your PDF will be downloaded now",
						Toast.LENGTH_SHORT).show();
			}
		});

	}

	public FullScreenNews() {
		// TODO Auto-generated constructor stub
	}

}
