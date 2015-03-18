package com.example.bvm_mobile.helper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.bvm_mobile.JSONParser;
import com.example.bvm_mobile.R;
import com.example.bvm_mobile.adapter.GeneralNewsAdapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.ListView;

public class LoadAllNotice extends AsyncTask<String, String, String> {
	
	ListView listview;
	String url;
	JSONParser jParser = new JSONParser();
	GeneralNewsAdapter adapter;
	ArrayList<String> list = new ArrayList<String>();
	ArrayList<String> listcopy = new ArrayList<String>();
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_NOTICES = "circular";
	private static final String TAG_ID = "id";
	private static final String TAG_TITLE = "title";
	private static final String TAG_FNAME = "file_name";
	private static final String TAG_DATE = "upload_date";
	JSONArray notices = null;
	ArrayList<String> file_list = new ArrayList<String>();
	ArrayList<String> list_dates = new ArrayList<String>();
	FragmentActivity activity;
	
	//public boolean okaytoexecute=false;
	ProgressDialog ringprogress;
	
	public LoadAllNotice(FragmentActivity activity,String url, ListView listview)
	{	this.activity=activity;
		if(url.startsWith("http:"))
			this.url=url;
		else
			this.url=activity.getResources().getString(R.string.website_name)+url;
		this.listview=listview;
	}
	public ListView getlistview()
	{
		return listview;
	}
	public ArrayList<String> getlist()
	{
		return listcopy;
	}
	public ArrayList<String> getfile_list()
	{
		return file_list;
	}
	
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		 ringprogress= ProgressDialog.show(activity, "Please wait ...", "Downloading News ...", true);
		 ringprogress.setCancelable(true);
	}
	@Override
	protected String doInBackground(String... args) {
		// TODO Auto-generated method stub
		// getting JSON string from URL
		JSONObject json = jParser.makeHttpRequest(url,
				"GET", null,true);

		// Check your log cat for JSON reponse
		Log.e("loadnoti ", json.toString());

		try {
			// Checking for SUCCESS TAG
			int success = json.getInt(TAG_SUCCESS);

			if (success == 1) {
				// products found
				// Getting Array of Products
				notices = json.getJSONArray(TAG_NOTICES);

				// looping through All Products
				for (int i = 0; i < notices.length(); i++) {
					JSONObject c = notices.getJSONObject(i);

					// Storing each json item in variable
					// String id = c.getString(TAG_ID);
					String title = c.getString(TAG_TITLE);
					String file_name = c.getString(TAG_FNAME);
					String file_date = c.getString(TAG_DATE);

					list.add(i, title);
					listcopy.add(i, title);
					Log.e("json", list.get(i));
					file_list.add(i, file_name);
					list_dates.add(i, file_date);
				}
			} else {
				// no products found
				// Launch Add New product Activity

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return null;
	}
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		setadapter();
		ringprogress.dismiss();
		//okaytoexecute=true;
		
	}
	public void setadapter() {
		// TODO Auto-generated method stub
		adapter = new GeneralNewsAdapter(activity, list,list_dates);

		listview.setAdapter(adapter);
		// textview.setText("january");

		Log.e("loadnoti", "adapter set");
	}

}
