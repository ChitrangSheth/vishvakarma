package com.example.bvm_mobile;

/**
 * Created by Pinkal on 2/23/2015.
 */
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.util.Log;

public class JSONParser {

	static InputStream is = null;
	static JSONObject jObj = null;
	static String json = "";
	// constructor
	public JSONParser() {

	}

	// function get json from url
	// by making HTTP POST or GET mehtod
	public JSONObject makeHttpRequest(String url, String method,
			List<NameValuePair> params, boolean usinghttpconnection) {
			
		if (usinghttpconnection) {
			URL urll = null;
			try {
				urll = new URL(url);

			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				Log.e("JSON Parser", "malformd url" + e1.toString());
			}
			HttpURLConnection urlConnection = new HttpURLConnection(urll) {

				@Override
				public void connect() throws IOException {
					// TODO Auto-generated method stub

				}

				@Override
				public boolean usingProxy() {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public void disconnect() {
					// TODO Auto-generated method stub

				}
			};

			try {
				urlConnection.addRequestProperty("Cache-Control",
						"only-if-cached");

				urlConnection = (HttpURLConnection) urll.openConnection();
				is = new BufferedInputStream(urlConnection.getInputStream());
				Log.e("json pars", is.toString());
			} catch (FileNotFoundException e2) {
				// the resource was not cached
				Log.e("JSON Parser", "filenot found" + e2.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				Log.e("JSON Parser", "io problem" + e.toString());
			}
		}

		else {
			// Making HTTP request
			try {

				// check for request method
				if (method == "POST") {
					// request method is POST
					// defaultHttpClient
					DefaultHttpClient httpClient = new DefaultHttpClient();
					HttpPost httpPost = new HttpPost(url);
					httpPost.setEntity(new UrlEncodedFormEntity(params));

					HttpResponse httpResponse = httpClient.execute(httpPost);
					HttpEntity httpEntity = httpResponse.getEntity();
					is = httpEntity.getContent();

				} else if (method == "GET") {
					// request method is GET
					DefaultHttpClient httpClient = new DefaultHttpClient();
					String paramString = URLEncodedUtils
							.format(params, "utf-8");
					url += "?" + paramString;
					HttpGet httpGet = new HttpGet(url);

					HttpResponse httpResponse = httpClient.execute(httpGet);
					HttpEntity httpEntity = httpResponse.getEntity();
					is = httpEntity.getContent();
				}

			} catch (UnsupportedEncodingException e) {
				Log.e("JSON Parser", "unsupported encod.. " + e.toString());
			} catch (ClientProtocolException e) {
				Log.e("JSON Parser", "client protocol" + e.toString());
			} catch (IOException e) {
				Log.e("JSON Parser", "io problem " + e.toString());
			}
		}
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					is, "iso-8859-1"), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			json = sb.toString();
		} catch (Exception e) {
			Log.e("Buffer Error", "Error converting result " + e.toString());
		}

		// try parse the string to a JSON object
		try {
			jObj = new JSONObject(json);
		} catch (JSONException e) {
			Log.e("JSON Parser", "Error parsing data " + e.toString());
		}

		// return JSON String
		return jObj;

	}
}
