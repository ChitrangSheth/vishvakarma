package com.example.bvm_mobile;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
//import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.support.v4.app.*;
import android.app.Activity;
//import android.app.Fragment;
//import android.app.FragmentManager;
import android.os.Bundle;
import android.text.GetChars;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FindPeopleFragment extends Fragment implements OnMapReadyCallback {
	SupportMapFragment fragment;
	View rootView;

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	public FindPeopleFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.mapfragment, container, false);
		fragment = new SupportMapFragment().newInstance();
		getFragmentManager().beginTransaction().replace(R.id.map_container, fragment).commit();
		
		//fragment = (SupportMapFragment) getFragmentManager().findFragmentById(
			//	R.id.map);
		fragment.getMapAsync(this);
		return rootView;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		setRetainInstance(true);
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

	}

	private GoogleMap googleMap;

	@Override
	public void onMapReady(GoogleMap map) {
		LatLng bvm = new LatLng(22.553091, 72.923658);

		map.setMyLocationEnabled(true);
		// map.animateCamera(CameraUpdateFactory.newLatLngZoom(bvm, 17));
		CameraPosition cameraPosition = new CameraPosition.Builder()
				.target(bvm) // Sets the center of the map to Mountain View
				.zoom(16) // Sets the zoom // Sets the orientation of the camera
							// to east
				.tilt(90) // Sets the tilt of the camera to 30 degrees
				.build(); // Creates a CameraPosition from the builder
		map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

		map.addMarker(new MarkerOptions().title("BVM")
				.snippet("Birla Vishwakarma Mahavidyalaya Engineering College")
				.position(bvm));
	}
}
