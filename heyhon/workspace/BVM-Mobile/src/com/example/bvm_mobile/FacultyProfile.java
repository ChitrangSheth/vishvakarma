package com.example.bvm_mobile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.bvm_mobile.adapter.ExpandableListAdapter;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;
import android.support.v4.app.*;

public class FacultyProfile extends Fragment {

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		/*prepareListData();

		listAdapter = new ExpandableListAdapter(getActivity(), listDataHeader,
				listDataChild);

		// setting list adapter
		expListView.setAdapter(listAdapter);*/
	}
	ProgressDialog ringProgressDialog;
	ExpandableListAdapter listAdapter;
	AnimatedExpandableListView expListView;
	List<String> listDataHeader = new ArrayList<String>();
	String[] list_to_convert;
	HashMap<String, List<String>> listDataChild;

	String fac_name, dept_name, d_id;
	JSONParser jParser = new JSONParser();
	List<String> list_name;
	List<String> list_1;
	List<String> list_2;
	List<String> list_3;
	List<String> list_4;
	List<String> list_5;
	List<String> list_6;
	List<String> list_7;
	List<String> list_8;
	List<String> list_9;
	boolean clickable=false;

	private static String url_general_notice = "fac_name.php";
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_FACULTY = "facultyprofile";
	private static final String TAG_NAME = "name";
	private static final String TAG_DEPT = "department";

	JSONArray names = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		url_general_notice = getActivity().getResources().getString(
				R.string.website_name)
				+ url_general_notice;
		View rootView = inflater.inflate(R.layout.fragment_faculty, container,
				false);

		expListView = (AnimatedExpandableListView) rootView.findViewById(R.id.lvExp);
		
		new loadnames().execute();
		// Listview Group click listener
		expListView
				.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

					@Override
					public boolean onGroupClick(ExpandableListView parent,
							View v, int groupPosition, long id) {
						// Toast.makeText(getApplicationContext(),
						// "Group Clicked " +
						// listDataHeader.get(groupPosition),
						// Toast.LENGTH_SHORT).show();
						Log.e("facprof","inside group click listener");
						/*if(expListView.isGroupExpanded(groupPosition))
							expListView.collapseGroup(groupPosition);
						else
							expListView.expandGroup(groupPosition, true);*/
						if(expListView.isGroupExpanded(groupPosition))
							expListView.collapseGroupWithAnimation(groupPosition);
						else
							expListView.expandGroupWithAnimation(groupPosition);
						return true;
					}
				});

		// Listview on child click listener
		expListView
				.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

					@Override
					public boolean onChildClick(ExpandableListView parent,
							View v, int groupPosition, int childPosition,
							long id) {
						// TODO Auto-generated method stub
						Toast.makeText(
								getActivity(),
								listDataHeader.get(groupPosition)
										+ " : "
										+ listDataChild.get(
												listDataHeader
														.get(groupPosition))
												.get(childPosition),
								Toast.LENGTH_SHORT).show();
						Intent profile = new Intent(getActivity(),
								FullScreenProfile.class);
						profile.putExtra(
								"name",
								listDataChild.get(
										listDataHeader.get(groupPosition)).get(
										childPosition));

						startActivity(profile);
						return false;
					}
				});
		/*expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
			
			@Override
			public void onGroupExpand(int groupPosition) {
				// TODO Auto-generated method stub
				
			}
		});
		expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
			
			@Override
			public void onGroupCollapse(int groupPosition) {
				// TODO Auto-generated method stub
				
			}
		});*/
		return rootView;
	}

	private void prepareListData() {
		list_to_convert = getResources().getStringArray(R.array.dept_names);
		listDataHeader = new ArrayList<String>(Arrays.asList(list_to_convert));
		listDataChild = new HashMap<String, List<String>>();

		// Adding child data
		/*
		 * List<String> top250 = new ArrayList<String>();
		 * top250.add("The Shawshank Redemption"); top250.add("The Godfather");
		 * top250.add("The Godfather: Part II"); top250.add("Pulp Fiction");
		 * top250.add("The Good, the Bad and the Ugly");
		 * top250.add("The Dark Knight"); top250.add("12 Angry Men");
		 */

		/*
		 * List<String> nowShowing = new ArrayList<String>();
		 * nowShowing.add("The Conjuring"); nowShowing.add("Despicable Me 2");
		 * nowShowing.add("Turbo"); nowShowing.add("Grown Ups 2");
		 * nowShowing.add("Red 2"); nowShowing.add("The Wolverine");
		 * 
		 * List<String> comingSoon = new ArrayList<String>();
		 * comingSoon.add("2 Guns"); comingSoon.add("The Smurfs 2");
		 * comingSoon.add("The Spectacular Now"); comingSoon.add("The Canyons");
		 * comingSoon.add("Europa Report");
		 */

		// listDataChild.put(dname,list_name);
		listDataChild.put(listDataHeader.get(0), list_1); // Header, Child data
		listDataChild.put(listDataHeader.get(1), list_2);
		listDataChild.put(listDataHeader.get(2), list_3);
		listDataChild.put(listDataHeader.get(3), list_4);
		listDataChild.put(listDataHeader.get(4), list_5);
		listDataChild.put(listDataHeader.get(5), list_6);
		listDataChild.put(listDataHeader.get(6), list_7);
		listDataChild.put(listDataHeader.get(7), list_8);
		listDataChild.put(listDataHeader.get(8), list_9);
		// listDataChild.put(listDataHeader.get(2), list_3);

		/*
		 * listAdapter = new ExpandableListAdapter(getActivity(),
		 * listDataHeader, listDataChild);
		 * 
		 * // setting list adapter expListView.setAdapter(listAdapter);
		 */
	}

	class loadnames extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			 ringProgressDialog= ProgressDialog.show(getActivity(), "Please wait ...", "Downloading faculty list", true);
			 ringProgressDialog.setCancelable(true);
		}

		protected String doInBackground(String... args) {

			// getting JSON string from URL
			JSONObject json = jParser.makeHttpRequest(url_general_notice,
					"GET", null, true);

			// Check your log cat for JSON reponse
			Log.e("All Names: ", json.toString());

			try {
				// Checking for SUCCESS TAG
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					// products found
					// Getting Array of Products
					names = json.getJSONArray(TAG_FACULTY);

					list_1 = new ArrayList<String>();
					list_2 = new ArrayList<String>();
					list_3 = new ArrayList<String>();
					list_4 = new ArrayList<String>();
					list_5 = new ArrayList<String>();
					list_6 = new ArrayList<String>();
					list_7 = new ArrayList<String>();
					list_8 = new ArrayList<String>();
					list_9 = new ArrayList<String>();

					// looping through All Products
					for (int i = 0; i < names.length(); i++) {
						JSONObject c = names.getJSONObject(i);

						// Storing each json item in variable
						// String id = c.getString(TAG_ID);

						if (c.getString(TAG_DEPT).equals("CE")) {
							list_1.add(c.getString(TAG_NAME));
						} else if (c.getString(TAG_DEPT).equals("CP")) {
							list_2.add(c.getString(TAG_NAME));
						} else if (c.getString(TAG_DEPT).equals("EE")) {
							list_3.add(c.getString(TAG_NAME));
						} else if (c.getString(TAG_DEPT).equals("EL")) {
							list_4.add(c.getString(TAG_NAME));
						} else if (c.getString(TAG_DEPT).equals("ET")) {
							list_5.add(c.getString(TAG_NAME));
						} else if (c.getString(TAG_DEPT).equals("IT")) {
							list_6.add(c.getString(TAG_NAME));
						} else if (c.getString(TAG_DEPT).equals("MT")) {
							list_7.add(c.getString(TAG_NAME));
						} else if (c.getString(TAG_DEPT).equals("ME")) {
							list_8.add(c.getString(TAG_NAME));
						} else if (c.getString(TAG_DEPT).equals("PR")) {
							list_9.add(c.getString(TAG_NAME));
						}
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

		protected void onPostExecute(String fileurl) {

			prepareListData();
			ringProgressDialog.dismiss();
			listAdapter = new ExpandableListAdapter(getActivity(),
					listDataHeader, listDataChild);

			// setting list adapter
			expListView.setAdapter(listAdapter);
			//expListView.setClickable(true);
			

		}

	}
}
