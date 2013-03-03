package com.geekhub.nadolinskyi.serhii.simlpereader;


import java.util.ArrayList;

import org.holoeverywhere.widget.Toast;

import com.geekhub.nadolinskyi.serhii.simlpereader.constants.Constants;
import com.geekhub.nadolinskyi.serhii.simlpereader.data.DataProvider;
import com.geekhub.nadolinskyi.serhii.simlpereader.models.Article;
import com.geekhub.nadolinskyi.serhii.simlpereader.models.ArticlesArray;
import com.geekhub.nadolinskyi.serhii.simlpereader.models.DataResponceEntity;
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
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ListViewFragment extends Fragment {
	public static final String LOG_TAG = "ListViewFragment";
	private ListView listView;
	private ArticlesArrayAdapter adapter;
	private static ArrayList<Article> articlesArray;
	private boolean isLoading = false;
	private ContentFragment contentFragment;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_list_view, null);
		
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
//		setRetainInstance(true);
/*	TODO
 *  for future FT container id
 * 		((ViewGroup)getView().getParent()).getId();
 */
		listView = (ListView) getView().findViewById(R.id.listView);
		listView.setOnScrollListener(scrollListener);
		if (DataProvider.hasLoadedArticles())
		{
			articlesArray = DataProvider.getContent(false, false).getArticlesArray();
		}else{
			articlesArray = new ArrayList<Article>();
		}
		
		adapter = new ArticlesArrayAdapter(getActivity(), articlesArray);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(listViewItemOnClickListener);
		
		 if (savedInstanceState != null) {
			if (getActivity().getSupportFragmentManager().findFragmentByTag(Constants.CONTENT_FRAGMENT_TAG) != null){
				contentFragment =  (ContentFragment)  getActivity().getSupportFragmentManager().findFragmentByTag(Constants.CONTENT_FRAGMENT_TAG);
			}
			 	
		    } else {
		    	/*contentFragment = new ContentFragment();
		        getActivity().getSupportFragmentManager().beginTransaction().add(R.id.rootLayout, contentFragment, Constants.LIST_VIEW_FRAGMENT_TAG).commit(); 
		    */
		    	}
		 
	}
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		if(contentFragment != null){
		getActivity().getSupportFragmentManager()
        .putFragment(outState, Constants.LIST_VIEW_FRAGMENT_TAG, contentFragment);
		}
	}
private	AbsListView.OnScrollListener scrollListener = new AbsListView.OnScrollListener() {
		
		@Override
		public void onScrollStateChanged(AbsListView view, int scrollState) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onScroll(AbsListView view, int firstVisibleItem,
				int visibleItemCount, int totalItemCount) {
			// TODO Auto-generated method stub
			boolean loadMore = /* maybe add a padding */
					firstVisibleItem + visibleItemCount >= totalItemCount;

		        if((loadMore)&&(!isLoading)) {
		            new GetArticlesTask().execute();
		            
		        }
		}
	};
	
	 protected class GetArticlesTask extends
	  AsyncTask<Void, Void, DataResponceEntity> {

//	ProgressDialog progress;
	View view = getActivity().getLayoutInflater().inflate(R.layout.progress_bar_footter, null);

	@Override
	protected void onPreExecute() {
	  super.onPreExecute();
	  isLoading = true;
	  
		listView.addFooterView(view);
/*	  progress = ProgressDialog.show(getActivity(),
	      null, getString(R.string.progress_dialog), true,
	      false);*/
	}

	@Override
	protected DataResponceEntity doInBackground(Void... params) {
		//TODO remove or include in logic feedsURL transition
	  try {
	    return DataProvider.getContent(false, true);
	        
	  } catch (Exception e) {
	    e.printStackTrace();
	    return null;
	  }
	  
	}

	@Override
	protected void onPostExecute(DataResponceEntity result) {
	  super.onPostExecute(result);
	  processGetContentResult(result);
	 /* progress.dismiss();*/
	  listView.removeFooterView(view);
	  isLoading = false;

	}

	
	
}
	 private void processGetContentResult(final DataResponceEntity result) {
			// TODO Add if null handling, or create ServerDataResponse entity 
		if ((result != null) && ( result != null)){ 
//		 articlesArray.addAll(result.getArticlesArray());
			switch (result.getResponceCode()) {
			case DataProvider.DATA_RESPONCE_OK:
//				articlesArray = result.getArticlesArray();
				if(adapter != null){
					
					
					getActivity().runOnUiThread(new Runnable() {
					    public void run() {
					    	
					    	adapter.addAllCompatible(result.getArticlesArray());
					    	adapter.notifyDataSetChanged();
					    }
					});
					/*listView.invalidate();
					listView.setAdapter(adapter);
					listView.refreshDrawableState();*/
					/*adapter = new ArticlesArrayAdapter(getActivity(), articlesArray);
					listView.setAdapter(adapter);
					listView.setOnItemClickListener(listViewItemOnClickListener);*/
					
						Log.d(LOG_TAG, "adapter.notifyDataSetChanged();");
						Log.d(LOG_TAG, "articlesArray.size()" + articlesArray.size());
				} 
				
				break;

			default:
				
				Toast.makeText(getActivity(), "Somthing went wrong", Toast.LENGTH_LONG).show();	 
				break;
			}
			
		 
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
	private ContentFragment newFragment;
	
	private void createNewContentFragment(Article article){
		
		contentFragment = new ContentFragment();
		Bundle args = new Bundle();
		args.putString(Constants.BK_CONTENT, article.getContent());
		contentFragment.setArguments(args);
		createNewFragment(contentFragment);
		
	}
	
	private void createNewTitlesFragment(boolean showLikes) {
		
//		TODO
		
	}
	
	private void createNewFragment(Fragment newFragment){
		 FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
		 ft.addToBackStack(getTag());
         ft.replace(R.id.rootLayout, newFragment, Constants.CONTENT_FRAGMENT_TAG);
         ft.commit();
	}
	
		
}
