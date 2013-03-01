package com.geekhub.nadolinskyi.serhii.simlpereader;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;

public class FirstActivity extends FragmentActivity {

	private static final String LIST_VIEW_FRAGMENT_TAG = "com.geekhub.nadolinskyi.serhii.simlpereader.ListViewFragment";
	ListViewFragment fragment;
	private FragmentTransaction ft;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		
		
	   /* if (savedInstanceState != null) {
	        fragment = (ListViewFragment) getSupportFragmentManager().findFragmentByTag(LIST_VIEW_FRAGMENT_TAG);
	    } else {
	        fragment = new ListViewFragment();
	        getSupportFragmentManager().beginTransaction().add(R.id.rootLayout, fragment, LIST_VIEW_FRAGMENT_TAG).commit(); 
	    }*/
		fragment = new ListViewFragment();
		ft = getSupportFragmentManager().beginTransaction();
		ft.add(R.id.rootLayout, fragment);
//		ft.addToBackStack(LIST_VIEW_FRAGMENT_TAG);
		ft.commit();
		
	}
	
	
	/*@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		getSupportFragmentManager()
        .putFragment(outState, LIST_VIEW_FRAGMENT_TAG, fragment);
	}
*/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_first, menu);
		return true;
	}

}
