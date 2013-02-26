package com.geekhub.nadolinskyi.serhii.simlpereader.data;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

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


	private static boolean hasNextPage = true;
	private static int     nextPage = 1;
	private static ArticlesArray articlesArray = new ArticlesArray();
	
	
	public static ArticlesArray getContent(){
		//TODO get data from db/net
		
		
		return getContentFromURL();
	}

	private static ArticlesArray getContentFromURL() {
		Log.d(LOG_TAG, "Started to download ");
		URL url;
		String feedString;
		try {
			url = new URL(/*Constants.URL_FEED*/buildUrlFeedQuery());
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
		
//		return parseJSONtoArticles(feedString);
		return parseXMLtoArticles(feedString);
		
		
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
	
	/*public static ArticlesArray parseJSONtoArticles(String feedString){
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
		
		
		return articlesArray;
	}
	*/
	public static ArticlesArray parseXMLtoArticles(String feedString){
		if ((feedString == null)||(feedString.equals(""))){
			Log.d(LOG_TAG, "feedString is null");
			return null;
		}
		Log.d(LOG_TAG, "Started to parse ");
		ArticlesArray articlesArray = new ArticlesArray();
		
		 InputStream stream = new ByteArrayInputStream(feedString
		        .getBytes());
		
		 DocumentBuilderFactory domFactory = DocumentBuilderFactory
			        .newInstance();
//		 domFactory.setNamespaceAware(true);
		 
			try {
				DocumentBuilder builder = domFactory.newDocumentBuilder();
				 Document doc = builder.parse(stream);
			     NodeList channelNodeList = doc.getElementsByTagName(Constants.XML_CHANNEL);
			     Node channelNode = channelNodeList.item(0);
			     			     
			     Element channelElement = (Element) channelNode;
			     NodeList item_nodeList = channelElement.getElementsByTagName(Constants.XML_ITEM);
			     if (item_nodeList.getLength() == 0){
			    	 hasNextPage  = false;
			     }
			     
			     //Getting all "item elements"
			     for (int index = 0; index < item_nodeList
		                  .getLength(); index++) {
			    	 
			    	 Article article = new Article();
			    	 
			    	 Node node = item_nodeList.item(index);
			    	  Element element = (Element) node;
			    	
			    	  article.setTitle(getNodeValue(element, Constants.XML_TITLE))
			    	  		 .setLink(getNodeValue(element, Constants.XML_LINK))
			    	  		 .setComments(getNodeValue(element, Constants.XML_COMMENTS))
			    	  		 .setPubDate(getNodeValue(element, Constants.XML_PUB_DATE))
			    	  		 .setCreator(getNodeValue(element, Constants.XML_CREATOR))
			    	  		 .setDescription(getNodeValue(element, Constants.XML_DESCTRIPTION))
			    	  		 .setContent(getNodeValue(element, Constants.XML_CONTENT))
			    	  		 .setComments_rss(getNodeValue(element, Constants.XML_COMMENTS_RSS))
			    	  		 .setSplash_comments(getNodeValue(element, Constants.XML_COMMENTS_SPLASH));
			    	  Log.d(LOG_TAG, "TITLE: " + article.getTitle());
			    	  NodeList categoriesNodesList = element.getElementsByTagName(Constants.XML_CATEGORY);
			    	  for (int categories_index = 0; categories_index < categoriesNodesList
			                  .getLength(); categories_index++) {
			    		  Node categoryNode = categoriesNodesList.item(categories_index);
			    		  article.addCategory(categoryNode.getTextContent());
			    	  }
			    	  articlesArray.add(article);
//			    	  Log.d(LOG_TAG, "Got " + article.getCategories().size() +" elements of " + Constants.XML_CATEGORY);
			    	  }
			     Log.d(LOG_TAG, "Parce finished ");
			     return articlesArray;
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
	
	
	private static String getNodeValue(Element e, String nodeName){
		String nodeValue = "";
//		if (e.hasChildNodes()){
			NodeList nodeList = e.getElementsByTagName(nodeName);
//			Log.d(LOG_TAG, "Got " + nodeList.getLength() +" elements of " + nodeName);
			if (nodeList.getLength() != 0){
				nodeValue = nodeList.item(0).getTextContent();
//				nodeValue = nodeList.item(0).getNodeValue();
					if (nodeValue == null){
						nodeValue = "";
					}
				
				if (nodeList.getLength()>1){
//					Log.d(LOG_TAG, "Got " + nodeList.getLength() +" elements of " + e.getNodeName());
				}
				
			}
			
			
//		}
//		Log.d(LOG_TAG, "Got '" + nodeValue +"' value of " + nodeList.item(0).getNodeName());
		return nodeValue;
	}
	
private static String buildUrlFeedQuery(){
	Log.d(LOG_TAG, "Previous page is "+ nextPage);
	String query = Constants.URL_FEED + Constants.URL_FEED_NEXT_PAGE + String.valueOf(nextPage++);
	Log.d(LOG_TAG, "Next page is "+ nextPage);
	return query;
}
}
