package com.geekhub.nadolinskyi.serhii.simlpereader.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.geekhub.nadolinskyi.serhii.simlpereader.constants.Constants;
import com.geekhub.nadolinskyi.serhii.simlpereader.models.Article;
import com.geekhub.nadolinskyi.serhii.simlpereader.models.ArticlesArray;


public class DataProvider {

	public static final String LOG_TAG = "DataProvider";
	
	
	public static final String JSON_PARSE_ROOT 				= "result";
	public static final String JSON_PARSE_ID 				= "id";
	public static final String JSON_PARSE_PICTURE 			= "picture";
	public static final String JSON_PARSE_TITLE 			= "title";
	public static final String JSON_PARSE_SUBTITLE 			= "subtitle";
	public static final String JSON_PARSE_CONTENT 			= "content";
	public static final String JSON_PARSE_DATE 				= "date";
	public static final String JSON_PARSE_LINK 				= "link";
	
	
	
	
	
	public static ArticlesArray getContent(){
		//TODO get data from db/net
		
		
		return getContentFromURL();
	}

	private static ArticlesArray getContentFromURL() {
		
		URL url;
		String feedString;
		try {
			url = new URL(Constants.FEED_URL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			feedString = convertStreamToString(con.getInputStream());
		} catch (MalformedURLException e) {
			Log.d(LOG_TAG, e.getMessage());
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			Log.d(LOG_TAG, e.getMessage());
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			Log.d(LOG_TAG, e.getMessage());
			e.printStackTrace();
			return null;
		}
		
		return parseJSONtoArticles(feedString);
		
		
	/*Random randomId = new Random();
		
		for (String title:Constants.titleList){
			Article article = new Article();
			
			article
			.setTitle(title)
			.setDate(getRandomDate())
			.setId(String.valueOf(randomId.nextInt()))
			.setLink(Constants.DUMMY_CONTENT_FILE_PATH);
			
			
			
			articlesArray.getArticlesArray().add(article);
		}*/
		
	}
	
	public static ArticlesArray parseJSONtoArticles(String feedString){
		if ((feedString == null)||(feedString.equals(""))){
			Log.d(LOG_TAG, "feedString is null");
			return null;
		}
		Log.d(LOG_TAG, "feedString is " + feedString);
		ArticlesArray articlesArray = new ArticlesArray();
		
		try {
			
			JSONArray jArray = (new JSONObject(feedString)).getJSONArray(JSON_PARSE_ROOT);

			
			for (int i = 0; i < jArray.length(); i++) {
				JSONObject oneObject = jArray.getJSONObject(i);

				Article article = new Article();
				
				article
					.setId(oneObject.getString(JSON_PARSE_ID))
					.setTitle(oneObject.getString(JSON_PARSE_TITLE))
					.setSubtitle(oneObject.getString(JSON_PARSE_SUBTITLE))
					.setContent(oneObject.getString(JSON_PARSE_CONTENT))
					.setDate(oneObject.getString(JSON_PARSE_DATE))
					.setLink(oneObject.getString(JSON_PARSE_LINK))
					.setPicture(oneObject.getString(JSON_PARSE_PICTURE));
				
				articlesArray.add(article);
				Log.d(LOG_TAG,"Got article titeled " + article.getTitle());
			
			}
		
		
		
		} catch (JSONException e) {
			Log.d(LOG_TAG, e.getMessage());
			e.printStackTrace();
			return null;
		}
		
		
		return null;
	}
	
	
	public static String convertStreamToString(InputStream is) throws Exception {
		  BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		  StringBuilder sb = new StringBuilder();
		  String line = null;
		  while ((line = reader.readLine()) != null) {
		    sb.append(line);
		  }
		  is.close();
		  return sb.toString();
		}
	
	/*private static String getRandomDate(){
		String randomDate = null;
		
		long generatedTimeMillis = System.currentTimeMillis();
		
		long range = 360522397L;
		Random r = new Random();
		long number = (long)(r.nextDouble()*range);
		generatedTimeMillis -= number;
		
		 SimpleDateFormat sdf = new SimpleDateFormat(Constants.DATE_FORMAT, Locale.US);
		 sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		 randomDate =  sdf.format(new Date(generatedTimeMillis));
		   
		return randomDate;
	}*/
	
	
	
}
