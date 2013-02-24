package com.geekhub.nadolinskyi.serhii.simlpereader.constants;

import java.util.ArrayList;
import java.util.Arrays;

public class Constants {

	String[] titles = { "Alfa", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot",
			"Golf", "Hotel", "India", "Juliet", "Kilo", "Lima", "Mike",
			"November", "Oscar", "Papa", "Quebec", "Romeo", "Sierra", "Tango",
			"Uniform", "Victor", "Whiskey", "X-ray", "Yankee", "Zulu" };

	public static final ArrayList<String> titleList = new ArrayList<String>(Arrays.asList(
			"Alfa", "Bravo", "Charlie", "Delta", "Echo", "Foxtrot", "Golf",
			"Hotel", "India", "Juliet", "Kilo", "Lima", "Mike", "November",
			"Oscar", "Papa", "Quebec", "Romeo", "Sierra", "Tango", "Uniform",
			"Victor", "Whiskey", "X-ray", "Yankee", "Zulu"));

	public static final String DUMMY_CONTENT_FILE_PATH = "file:///android_asset/dummyText.html";
	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
	public static final String FEED_URL = "https://dl.dropbox.com/u/12330241/dummyJson/feed.html";
	
	
	public static final String JSON_RESULT = "file:///android_asset/dummyText.html";
	
	
	public static final String XML_CHANNEL 						= "channel";
	public static final String XML_ITEM 						= "item";
	public static final String XML_TITLE 						= "title";
	public static final String XML_LINK 						= "link";
	public static final String XML_COMMENTS 					= "comments";
	public static final String XML_PUB_DATE 					= "pubDate";
	public static final String XML_CREATOR	 					= "dc:creator";
	public static final String XML_CATEGORY 					= "category";
	public static final String XML_DESCTRIPTION					= "description";
	public static final String XML_CONTENT						= "content:encoded";
	public static final String XML_COMMENTS_RSS					= "wfw:commentRss";
	public static final String XML_COMMENTS_SPLASH				= "slash:comments";
	
	public static final String URL_FEED 						= "http://newswatch.nationalgeographic.com/feed";
	public static final String URL_FEED_NEXT_PAGE				="/?paged=";
	
	public static final String BK_CONTENT						="com.geekhub.nadolinskyi.serhii.simlpereader.bk.content";
	
	
	
	
	
}
