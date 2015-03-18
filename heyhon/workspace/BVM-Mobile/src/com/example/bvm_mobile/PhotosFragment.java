package com.example.bvm_mobile;

import java.util.ArrayList;

import com.example.bvm_mobile.adapter.GridViewImageAdapter;
import com.example.bvm_mobile.helper.Utils;
//import android.app.*;
import android.support.v4.app.*;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import com.example.bvm_mobile.adapter.GridViewImageAdapter;
import com.example.bvm_mobile.helper.AppConstant;
import com.example.bvm_mobile.helper.Utils;

import java.util.ArrayList;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.GridView;

public class PhotosFragment extends Fragment {
	private Utils utils;
	private ArrayList<String> imagePaths = new ArrayList<String>();
	private GridViewImageAdapter adapter;
	private GridView gridView;
	private int columnWidth;
	public PhotosFragment(){}
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.activity_grid_view, container, false);
        gridView = (GridView) rootView.findViewById(R.id.grid_view);

		utils = new Utils(getActivity());
		// Initilizing Grid View
		InitilizeGridLayout();

				// loading all image paths from SD card
		imagePaths = utils.getFilePaths();

				// Gridview adapter
		adapter = new GridViewImageAdapter(getActivity(), imagePaths,
						columnWidth);

				// setting grid view adapter
		gridView.setAdapter(adapter);
		
		
        return rootView;
    }

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		

		
	}

	private void InitilizeGridLayout() {
		Resources r = getResources();
		float padding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
				AppConstant.GRID_PADDING, r.getDisplayMetrics());

		columnWidth = (int) ((utils.getScreenWidth() - ((AppConstant.NUM_OF_COLUMNS + 1) * padding)) / AppConstant.NUM_OF_COLUMNS);

		gridView.setNumColumns(AppConstant.NUM_OF_COLUMNS);
		gridView.setColumnWidth(columnWidth);
		gridView.setStretchMode(GridView.NO_STRETCH);
		gridView.setPadding((int) padding, (int) padding, (int) padding,
				(int) padding);
		gridView.setHorizontalSpacing((int) padding);
		gridView.setVerticalSpacing((int) padding);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
	}
	
}
