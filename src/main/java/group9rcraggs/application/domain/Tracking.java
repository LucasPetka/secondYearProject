package group9rcraggs.application.domain;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;

public class Tracking {
	
	///*This only works for static webpages*///
	///*Need another way to track dynamic pages*///
	
	public static void main(String[]args) {
		
	   	String sourceCode = getSourceCode("https://www.gov.uk", "gov4.html");
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
		 
		 public static String getSourceCode(String url, String fileName) {
			 String sourceCode="";
				
		      String inputLine;
		      try {
		          URL data = new URL(url);

		          /* Open connection */
		          
		          HttpURLConnection con = (HttpURLConnection) data.openConnection(); 
		          /* Read webpage content */
		          BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		          
		          
		          FileOutputStream output = new FileOutputStream(fileName);
		          PrintWriter printer = new PrintWriter(output);
		          /* Read line by line and concat to var sourceCode*/
		          while ((inputLine = in.readLine()) != null) {
		        	  sourceCode += inputLine+"\n";
		          }
		          /* Writes source code to file */
		          printer.println(sourceCode);
		          /* close BufferedReader */
		          in.close();
		          /* close HttpURLConnection */
		          con.disconnect();
		      } catch (Exception e) {
		          e.printStackTrace();
		      }
		      
		      return sourceCode;
		 }
		

		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
	//	 diff <(sed '/<a class="mkdf-video-banner-link" href="https:\/\/youtu.be\/VZ2VQoFX_8Y" 
	//	 data-rel="prettyPhoto\[864487452\]">/d;/123<head>/d' index.html.1) 
	//	 <(sed '/<a class="mkdf-video-banner-link" href="https:\/\/youtu.be\/VZ2VQoFX_8Y" data-rel="prettyPhoto\[272514594\]">
		 ///d;/321<head>/d' index.html.2)
		 
		 
		 
		 
		 

}
