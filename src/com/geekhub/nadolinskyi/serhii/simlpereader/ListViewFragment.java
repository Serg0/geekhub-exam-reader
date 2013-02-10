package com.geekhub.nadolinskyi.serhii.simlpereader;


import com.geekhub.nadolinskyi.serhii.simlpereader.constants.Constants;
import com.geekhub.nadolinskyi.serhii.simlpereader.data.DataProvider;
import com.geekhub.nadolinskyi.serhii.simlpereader.models.ArticlesArray;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ListViewFragment extends Fragment {
	public static final String LOG_TAG = "ListViewFragment";

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_list_view, null);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
/*	 for future FT container id
 * 		((ViewGroup)getView().getParent()).getId();
 */
		new GetArticlesTask().execute(Constants.FEED_URL);
		
		
	}
	
	 protected class GetArticlesTask extends
	  AsyncTask<String, Void, ArticlesArray> {

	ProgressDialog progress;

	@Override
	protected void onPreExecute() {
	  super.onPreExecute();
	  progress = ProgressDialog.show(getActivity(),
	      null, getString(R.string.progress_dialog), true,
	      false);
	}

	@Override
	protected ArticlesArray doInBackground(String... feedsURL) {
		//TODO remove or include in logic feedsURL transition
	  try {
	    return DataProvider.getContent();
	        
	  } catch (Exception e) {
	    e.printStackTrace();
	    return null;
	  }
	  
	}

	@Override
	protected void onPostExecute(ArticlesArray result) {
	  super.onPostExecute(result);
	  processGetContentResult(result);
	  progress.dismiss();

	}

	
}
	 private void processGetContentResult(ArticlesArray result) {
			// TODO Auto-generated method stub
			// TODO Add if null handling, or create ServerDataResponse entity 
		}
			 
}
