package com.geekhub.nadolinskyi.serhii.simlpereader.utils;

public class CommonUtils {
	
	
	  public static boolean IsHoneycombOrLater()
	  {
	     final int currentapiVersion = android.os.Build.VERSION.SDK_INT;
	       return currentapiVersion >= android.os.Build.VERSION_CODES.HONEYCOMB;
	  }
}
