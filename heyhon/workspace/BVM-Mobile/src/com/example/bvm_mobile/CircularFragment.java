package com.example.bvm_mobile;

import java.util.ArrayList;
import com.example.bvm_mobile.helper.LoadAllNotice;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class CircularFragment extends Fragment {

	ListView listview;
	LoadAllNotice load;
	ArrayList<String> listcopy = new ArrayList<String>();
	private static String url_general_notice = "circ_script.php";
	ArrayList<String> file_list = new ArrayList<String>();

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.e("seminafrag", "starting oncreateview");
		View rootView = inflater.inflate(R.layout.homefragment_listview,
				container, false);

		listview = (ListView) rootView.findViewById(R.id.listview1);
		
		load=new LoadAllNotice(getActivity(),url_general_notice,listview);
		load.execute();
		listview=load.getlistview();
		listcopy=load.getlist();
		file_list=load.getfile_list();
		
		Log.e("seminafrag", "listview generated");
		
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getActivity(), FullScreenNews.class);
				i.putExtra("key", listcopy.get(position));
				i.putExtra("filename", file_list.get(position));
				startActivity(i);
			}
		});
		return rootView;
	}
}
