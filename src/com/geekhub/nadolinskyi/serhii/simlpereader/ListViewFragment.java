package com.geekhub.nadolinskyi.serhii.simlpereader;


import java.util.ArrayList;

import org.holoeverywhere.widget.Toast;

import com.geekhub.nadolinskyi.serhii.simlpereader.constants.Constants;
import com.geekhub.nadolinskyi.serhii.simlpereader.data.DataProvider;
import com.geekhub.nadolinskyi.serhii.simlpereader.models.Article;
import com.geekhub.nadolinskyi.serhii.simlpereader.models.ArticlesArray;
import com.geekhub.nadolinskyi.serhii.simlpereader.utils.ArticlesArrayAdapter;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ListViewFragment extends Fragment {
	public static final String LOG_TAG = "ListViewFragment";
	private ListView listView;
	private ArticlesArrayAdapter adapter;
	private ArrayList<Article> articlesArray;
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_list_view, null);
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
/*	TODO
 *  for future FT container id
 * 		((ViewGroup)getView().getParent()).getId();
 */
		listView = (ListView) getView().findViewById(R.id.listView);
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
		if ((result != null) && ( result.getArticlesArray() != null)){ 
		 articlesArray = result.getArticlesArray();
		 adapter = new ArticlesArrayAdapter(getActivity(), articlesArray);
		 
		 listView.setAdapter(adapter);
		 listView.setOnItemClickListener(listViewItemOnClickListener);
		 }else{
		Toast.makeText(getActivity(), "Invalid server responce", Toast.LENGTH_LONG).show();	 
		 }
		 
		}
			 
	 private OnItemClickListener listViewItemOnClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View view, int position,
				long id) {
			Article article = articlesArray.get(position);
			createNewContentFragment(article);
			
		}
	};
	
	private void createNewContentFragment(Article article){
		
		ContentFragment newFragment = new ContentFragment();
		Bundle args = new Bundle();
		args.putString(Constants.BK_CONTENT, article.getContent());
		newFragment.setArguments(args);
		createNewFragment(newFragment);
		
	}
	
	private void createNewTitlesFragment(boolean showLikes) {
		
//		TODO
		
	}
	
	private void createNewFragment(Fragment newFragment){
		FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
		 ft.addToBackStack(getTag());
         ft.replace(R.id.rootLayout, newFragment, getTag());
         ft.commit();
	}
}
