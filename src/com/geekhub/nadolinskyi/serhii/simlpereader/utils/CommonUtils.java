package com.geekhub.nadolinskyi.serhii.simlpereader.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.text.format.DateUtils;


public class CommonUtils {
	
	
	  public static boolean IsHoneycombOrLater()
	  {
	     final int currentapiVersion = android.os.Build.VERSION.SDK_INT;
	       return currentapiVersion >= android.os.Build.VERSION_CODES.HONEYCOMB;
	  }
	  
	  public static String extractFirstImage(String htmlFragment){
		  Document doc = Jsoup.parse(htmlFragment);
		  Element pic = doc.select("img").first();
		  String picString = "";
		  if (pic != null){
			  picString =  pic.attr("src");
			  
		  }
		  return picString;
		  /*Pattern pattern =
				   Pattern.compile( "<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>" );
				Matcher matcher = pattern.matcher( htmlFragment );
				if(matcher.matches()) {
					return matcher.group(1);
				   System.err.println(
				      "OK:\n" +
				      "1: '" + matcher.group(1) + "'\n" +
				      "2: '" + matcher.group(2) + "'\n" +
				      "3: '" + matcher.group(3) + "'\n" );
				}else{
					return "";
				}*/
	  }
	  
	  
	  public static String formatDateToRelativeFormat(String time){
		  String relativeTime = "";
          DateFormat formatter ; 
          Date date ; 
          formatter = new SimpleDateFormat("dd-MM-yyyy");
          try {
			date = (Date)formatter.parse(time);
			
			long dtMili = System.currentTimeMillis();
		    relativeTime = (String) DateUtils.getRelativeTimeSpanString(dtMili - date.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		  return relativeTime;
	  }
}
