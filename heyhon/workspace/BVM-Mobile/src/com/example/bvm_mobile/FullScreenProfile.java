package com.example.bvm_mobile;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.bvm_mobile.adapter.GeneralNewsAdapter;
import com.pkmmte.circularimageview.CircularImageView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class FullScreenProfile extends Activity {
	String name;
	String f_name ,email,dept, aot,photo_link,desg,pdf_link,qual;
	TextView txtvw_name,txtvw_des,txtvw_qual,txtvw_aot,txtvw_email;
	ImageView img;
	Bitmap bitmap;
	CircularImageView cir;
	JSONParser jParse = new JSONParser();
	
	private static String url_general_notice = "faculty_script.php";
	
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_FACULTY1 = "facultyprofile";
	private static final String TAG_NAME = "name";
	private static final String TAG_EMAIL = "email";
	private static final String TAG_DEPT = "department";
	private static final String TAG_AOT = "AOT";
	private static final String TAG_PHOTO = "photo";
	private static final String TAG_DESG = "designation";
	private static final String TAG_PDF = "pdf_link";
	private static final String TAG_QUAL = "qual";
	
	
	JSONArray details = null;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		url_general_notice = this.getResources().getString(
				R.string.website_name)
				+ url_general_notice;
		Bundle prof= getIntent().getExtras();
		name = prof.getString("name");
		
		new loaddetails().execute();
		
		setContentView(R.layout.fullscreen_profile);
		txtvw_name=(TextView)findViewById(R.id.fac_name);
		txtvw_des=(TextView)findViewById(R.id.designation);
		txtvw_qual=(TextView)findViewById(R.id.qualification);
		txtvw_aot=(TextView)findViewById(R.id.area_of_specialization);
		txtvw_email=(TextView)findViewById(R.id.email_id);
		cir=(CircularImageView)findViewById(R.id.imgNetwork);
	
		
		
	}
	
	class loaddetails extends AsyncTask<String, String, String> {
		ProgressDialog ring;
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			ring=ProgressDialog.show(FullScreenProfile.this, "Please wait...", "Downloading faculty information");
			ring.setCancelable(true);
		}

		@Override
		protected String doInBackground(String... args) {
			// Building Parameters
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("name", name));
			// getting JSON string from URL
			JSONObject json = jParse.makeHttpRequest(url_general_notice,"GET", params,false);

			// Check your log cat for JSON reponse
			Log.e("All Details: ", json.toString());

			try {
				// Checking for SUCCESS TAG
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					// products found
					// Getting Array of Products
					details = json.getJSONArray(TAG_FACULTY1);

					// looping through All Products
					for (int i = 0; i < details.length(); i++) {
						JSONObject d = details.getJSONObject(i);

						f_name = d.getString(TAG_NAME);
						dept = d.getString(TAG_DEPT);
						aot = d.getString(TAG_AOT);
						photo_link = d.getString(TAG_PHOTO);
						email = d.getString(TAG_EMAIL);
						desg = d.getString(TAG_DESG);
						pdf_link = d.getString(TAG_PDF);
						qual= d.getString(TAG_QUAL);
						
					}
				} else {
					// no products found
					// Launch Add New product Activity

				}
			} catch (JSONException e) {
				Log.e("loadall", "json exceptio"+e.toString());
			}

			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			ring.dismiss();
			try{
			URL image_url = new URL("http://10.70.90.128/site/data/faculty/"+photo_link);
			//URL url = new URL("http://10.70.90.178/site/data/photos/"+_filePaths.get(position));
			HttpURLConnection conn = (HttpURLConnection) image_url.openConnection();
			conn.setDoInput(true);
			conn.connect();
			InputStream input = conn.getInputStream();
			bitmap = BitmapFactory.decodeStream(input);	
			}
			catch(Exception e){}
			//img.setImageBitmap(bitmap);
			txtvw_name.setText(f_name);
			txtvw_des.setText(desg);
			txtvw_qual.setText(qual);
			txtvw_aot.setText(aot);
			txtvw_email.setText(email);
			
		}
	}

}
