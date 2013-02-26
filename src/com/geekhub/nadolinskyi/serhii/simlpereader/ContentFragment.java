package com.geekhub.nadolinskyi.serhii.simlpereader;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.geekhub.nadolinskyi.serhii.simlpereader.ListViewFragment.GetArticlesTask;
import com.geekhub.nadolinskyi.serhii.simlpereader.constants.Constants;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.widget.ListView;

public class ContentFragment extends Fragment{

	
	private WebView webView;
	private String content;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_content, null);
	}
	
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
//		setRetainInstance(true);
/*	 for future FT container id
 * 		((ViewGroup)getView().getParent()).getId();
 */
		webView = (WebView) getView().findViewById(R.id.webView);
		
		Bundle    inputBundle = getArguments();
	    if(inputBundle!=null)
	    {
	    	
	    	content = inputBundle.getString(Constants.BK_CONTENT);
	    	
	    	webView.getSettings().setBuiltInZoomControls(true);
	    	webView.getSettings().setLayoutAlgorithm(
					LayoutAlgorithm.SINGLE_COLUMN);
	    	
	    	try {
	    		String header = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>";
				String query = URLEncoder.encode(
						content,
						"utf-8").replaceAll("\\+", " ");
				
				webView.loadData(header +  query,
						"text/html; charset=UTF-8", null);
				
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	
	    }
		
		
	}
}
