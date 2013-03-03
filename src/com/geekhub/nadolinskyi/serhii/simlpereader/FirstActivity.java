package com.geekhub.nadolinskyi.serhii.simlpereader;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.geekhub.nadolinskyi.serhii.simlpereader.constants.Constants;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class FirstActivity extends SherlockFragmentActivity {

	
	ListViewFragment fragment;
	private FragmentTransaction ft;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		
		
	    if (savedInstanceState != null) {
	        fragment = (ListViewFragment) getSupportFragmentManager().findFragmentByTag(Constants.LIST_VIEW_FRAGMENT_TAG);
	    } else {
	        fragment = new ListViewFragment();
	        getSupportFragmentManager().beginTransaction().add(R.id.rootLayout, fragment, Constants.LIST_VIEW_FRAGMENT_TAG).commit(); 
	    }
/*		if (fragment == null){		
		fragment = new ListViewFragment();
		ft = getSupportFragmentManager().beginTransaction();
		ft.add(R.id.rootLayout, fragment);
//		ft.addToBackStack(LIST_VIEW_FRAGMENT_TAG);
		ft.commit();
		}*/

		
	}
	
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		getSupportFragmentManager()
        .putFragment(outState, Constants.LIST_VIEW_FRAGMENT_TAG, fragment);
	}

/*	@Override
	public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu menu) {
		// TODO Auto-generated method stub
		return super.onCreateOptionsMenu(menu);
	}*/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.

	    MenuInflater supportMenuInflater = getSupportMenuInflater();
//	  supportMenuInflater.inflate(R.menu.activity_first, menu);
	     MenuItem item = menu.findItem(R.id.menu_settings);
//	     item.setIcon(R.drawable.button_custom_black);
		getSupportMenuInflater().inflate(R.menu.activity_first, menu);
		return true;
	}

}
