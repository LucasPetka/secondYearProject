package group9rcraggs.application.controller;

import java.net.HttpURLConnection;
import java.net.URL;


public class Validation {
	
	
	public int httpStatus(String website) {
		
		try {
			URL url = new URL(website);
		
		HttpURLConnection http = (HttpURLConnection)url.openConnection();
		http.setConnectTimeout(10000);

		int statusCode = http.getResponseCode();
		return statusCode;
		//If any errors then return 0 - Will trigger an invalid URL on validation
		} catch (Exception e) 	{
			
		return 0;
		}
	}
	
	
	

}
