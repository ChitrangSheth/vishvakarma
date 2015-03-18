package com.example.bvm_mobile.adapter;

import java.util.ArrayList;
import com.example.bvm_mobile.R;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GeneralNewsAdapter extends ArrayAdapter<String>{
	
	ArrayList<String> list=new ArrayList<String>();
	ArrayList<String> list_dates = new ArrayList<String>();
	Activity activity;
	LayoutInflater inflater;
	public GeneralNewsAdapter(Activity abc,ArrayList<String> list,ArrayList<String> list_dates) {
		super(abc,R.layout.list_layout,list);
		this.list=list;
		this.activity=abc;
		this.list_dates=list_dates;
		shrinklist();
		Log.e("gnadapter", "inside constructer");
		//Log.e("gnadapter", this.list.get(0)+this.list.get(1));
		
		//inflater=LayoutInflater.from(abc);
		// TODO Auto-generated constructor stub
	}

	private void shrinklist() {
		// TODO Auto-generated method stub
		String str;
		for(int i=0;i<list.size();i++)
		{
			str=list.get(i);
			if(str.length()>40)
				str=str.substring(0, 40).concat("...");
			list.remove(i);
			list.add(i, str);
		}
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		inflater=activity.getLayoutInflater();
		View view=inflater.inflate(R.layout.list_layout,null);
        TextView textview=(TextView)view.findViewById(R.id.general_list);
        TextView date=(TextView)view.findViewById(R.id.text_date);
        textview.setText(list.get(position));
        date.setText("Uploaded on : "+list_dates.get(position));
        Log.e("gnadapter", "textview:"+list.get(position));
		return view;
	}

}
