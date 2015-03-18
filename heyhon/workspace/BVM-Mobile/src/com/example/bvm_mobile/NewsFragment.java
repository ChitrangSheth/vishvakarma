package com.example.bvm_mobile;

import java.util.Locale;
import android.os.Bundle;
//import android.app.*;
import android.support.v4.app.*;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NewsFragment extends Fragment {
	 
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}
	public NewsFragment(){}
	SectionsPagerAdapter mSectionsPagerAdapter;

	public static final String TAG = NewsFragment.class.getSimpleName();
	
	/**
	 * The {@link ViewPager} that will host the section contents.
	 */
	ViewPager mViewPager;

	
	/*public static TabbedActivity newInstance() {
		return new TabbedActivity();
	}*/
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        /*View rootView = inflater.inflate(R.layout.fragment_home, container, false); 
        return rootView;*/
		View v = inflater.inflate(R.layout.activity_item_one, container, false);
		mSectionsPagerAdapter = new SectionsPagerAdapter(
				getFragmentManager());
		
		mViewPager = (ViewPager) v.findViewById(R.id.pager);
		mViewPager.setAdapter(mSectionsPagerAdapter);
		
		return v;
    }
	
public class SectionsPagerAdapter extends FragmentStatePagerAdapter {
	
	Fragment fragment;
	/*	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		super.destroyItem(container, position, object);
        if (fragment != null) getFragmentManager().beginTransaction().remove(fragment).commit();

	}*/

		public SectionsPagerAdapter(FragmentManager fragmentManager) {
			super(fragmentManager);
		}

		@Override
		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a DummySectionFragment (defined as a static inner class
			// below) with the page number as its lone argument.
			/*Fragment fragment = new DummySectionFragment();
			Bundle args = new Bundle();
			args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);*/
			Log.e("homefrag", "inside getitem method");

			fragment=null;
			//FragmentManager fm=getChildFragmentManager();
			switch (position) {
			case 0:
				fragment=new GeneralNewsFragment();
				break;
			case 1:
				fragment = new CircularFragment();
				break;
			case 2:
				fragment = new SeminarFragment();
				break;

			default:
				break;
			}

			/*if (fragment != null) {
				fm.beginTransaction()
						.replace(R.id.frame_container, fragment).commit();
				
				// update selected item and title, then close the drawer
				
			} else {
				// error in creating fragment
				Log.e("homeActivity", "Error in creating fragment");
			}*/
			return fragment;
		}

		@Override
		public int getCount() {
			// Show 3 total pages.
			return 3;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			Locale l = Locale.getDefault();
			switch (position) {
			case 0:
				return getString(R.string.title_section1).toUpperCase(l);
			case 1:
				return getString(R.string.title_section2).toUpperCase(l);
			case 2:
				return getString(R.string.title_section3).toUpperCase(l);
			}
			return null;
		}
	}

	/**
	 * A dummy fragment representing a section of the app, but that simply
	 * displays dummy text.
	 */

	/*public static class DummySectionFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		/*public static final String ARG_SECTION_NUMBER = "section_number";

		public DummySectionFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_item_one_dummy,
					container, false);
			TextView dummyTextView = (TextView) rootView
					.findViewById(R.id.section_label);
			dummyTextView.setText(Integer.toString(getArguments().getInt(
					ARG_SECTION_NUMBER)));
			return rootView;
		}
	}*/
}

