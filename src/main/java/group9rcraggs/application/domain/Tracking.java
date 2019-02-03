package group9rcraggs.application.domain;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;

public class Tracking {
	
	///*This only works for static webpages*///
	///*Need another way to track dynamic pages*///
	
	public static void main(String[]args) {
		
	   	String sourceCode = getSourceCode("http://www.gov.uk");
	      String hashedSourceCode =(getSha256(sourceCode));
	}
	
	
	 ///*Hashes a given string with SHA256*///
	 public static String getSha256(String value) {
		    try{
		        MessageDigest md = MessageDigest.getInstance("SHA-256");
		        md.update(value.getBytes());
		        return bytesToHex(md.digest());
		    } catch(Exception ex){
		        throw new RuntimeException(ex);
		    }
		 }
		 private static String bytesToHex(byte[] bytes) {
		    StringBuffer result = new StringBuffer();
		    for (byte b : bytes) result.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
		    return result.toString();
		 }
		 
		 
		 ///*Gets source code from a given url*///
		 
		 public static String getSourceCode(String url) {
			 String sourceCode="";
				
		      String inputLine;
		      try {
		          URL data = new URL(url);


		          /* Open connection */
		          
		          HttpURLConnection con = (HttpURLConnection) data.openConnection(); 
		          /* Read webpage content */
		          BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		          /* Read line by line and concat to var sourceCode*/
		          while ((inputLine = in.readLine()) != null) {
		        	  sourceCode += inputLine+"\n";
		          }

		          /* close BufferedReader */
		          in.close();
		          /* close HttpURLConnection */
		          con.disconnect();
		      } catch (Exception e) {
		          e.printStackTrace();
		      }
		      
		      return sourceCode;
		 }
		


}
