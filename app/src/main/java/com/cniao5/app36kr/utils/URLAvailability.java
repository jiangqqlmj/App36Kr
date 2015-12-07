package com.cniao5.app36kr.utils;

import java.net.HttpURLConnection;
import java.net.URL;

public class URLAvailability {
	private static URL url;  
	private static HttpURLConnection con;  
	private static int state = -1;  
	  
	/** 
	   * 功能：检测当前URL是否可连接或是否有效, 
	   * 描述：最多连接网络 5 次, 如果 5 次都不成功，视为该地址不可用 
	   * @param urlStr 指定URL网络地址 
	   * @return URL 
	   */  
	public synchronized static URL isConnect(String urlStr) {  
	   if (urlStr == null || urlStr.length() <= 0) {                         
	    return null;                   
	   }  
	    try {  
	     url = new URL(urlStr);  
	     con = (HttpURLConnection) url.openConnection();  
	     state = con.getResponseCode();  
	     if (state == 404) {  
	    	 url = null;  
	     }  
	    }catch (Exception ex) {  
	     urlStr = null;  
	    }  
	   return url;  
	}  
}
