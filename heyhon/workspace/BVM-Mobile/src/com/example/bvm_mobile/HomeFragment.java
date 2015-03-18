package com.example.bvm_mobile;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.support.v4.app.*;

public class HomeFragment extends Fragment {
	TextView motto,text_mission,mission,text_vision,vision;
	public HomeFragment(){}
	
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.fragment_homescreen, container, false);
        motto=(TextView)rootView.findViewById(R.id.motto);
        text_mission=(TextView)rootView.findViewById(R.id.text_mission);
        mission=(TextView)rootView.findViewById(R.id.mission);
        text_vision=(TextView)rootView.findViewById(R.id.text_vision);
        vision=(TextView)rootView.findViewById(R.id.vision);
        
        String fontPath = "Dancing Script.ttf";
        String fontPath2 = "Reckoner.ttf";
        String fontPath3 = "MarketingScript.ttf";
         
         // text view labe
  
         // Loading Font Face
         Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), fontPath);
         Typeface tf2 = Typeface.createFromAsset(getActivity().getAssets(), fontPath2);
         Typeface tf3 = Typeface.createFromAsset(getActivity().getAssets(), fontPath3);
  
         // Applying font
         motto.setTypeface(tf);
         text_mission.setTypeface(tf2);
         mission.setTypeface(tf3);
         text_vision.setTypeface(tf2);
         vision.setTypeface(tf3);
        return rootView;
    }
}
