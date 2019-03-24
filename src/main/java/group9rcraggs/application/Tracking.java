package group9rcraggs.application;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Tracking {


 
///*Gets source code from a given url and saves to file*///
 
public String sourceCodeToFile(String url, String fileName) {
String sourceCode="";
      String inputLine;
      try {
          URL data = new URL(url);


        ///* Opens connection *///
          HttpURLConnection con = (HttpURLConnection) data.openConnection(); 
        ///* Read webpage content *///
          BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
          FileOutputStream output = new FileOutputStream("pageDB/"+fileName);
          PrintWriter printer = new PrintWriter(output);
        ///* Reads line by line and concats to String sourceCode *///
         
          while ((inputLine = in.readLine()) != null) {
          sourceCode += inputLine+"\n";
          }
         
        ///* writes to file *///
          printer.write(sourceCode);
        ///* Closes PrintWriter *///
          printer.close();

          
          ///* close BufferedReader *///
          in.close();
         // /* close HttpURLConnection *///
          con.disconnect();
      } catch (Exception e) {
          e.printStackTrace();
      }
      
      return sourceCode;
}
///* Returns false if two strings aren't equal *///
	public static boolean diff(String string1, String string2) {
 
			if (string1.equals(string2)) return true;
			return false;
	}
	
	///* Returns array of line numbers that are different *///
	public ArrayList<Integer> compareFiles(String filename1, String filename2) {
 
		ArrayList<Integer> array = new ArrayList<Integer>();
 
			try {
				///* Takes two different files and calls diff on each line *///
				File file1 = new File(filename1);
				File file2 = new File(filename2);
				BufferedReader reader1 = new BufferedReader(new FileReader(file1));
				BufferedReader reader2 = new BufferedReader(new FileReader(file2));
				String line1;
				String line2;
				int i=1;  
					while ((line1 = reader1.readLine()) != null && ((line2 = reader2.readLine()) != null)) {
   
						if(!diff(line1, line2)) {
							///* Adds different line numbers to array to be ignored*///
							array.add(i);
						}
						i++; 
					}
					reader1.close();
					reader2.close();
			}
    catch(IOException io) {
    
    }
return array;
}
	
	
	
	
	
	
 
	///* Creates two temporary files for comparing *///
		public void createcomparedFile(String filename1, String filename2) {
	
			String sourceCode = "";
				try {
					
					FileOutputStream output1 = new FileOutputStream("pageDB/temp1.txt");
					FileOutputStream output2 = new FileOutputStream("pageDB/temp2.txt");
			        PrintWriter printer1 = new PrintWriter(output1);
			        PrintWriter printer2 = new PrintWriter(output2);		
					
					///* Takes two different files and calls diff on each line *///
					File file1 = new File(filename1);
					File file2 = new File(filename2);
					BufferedReader reader1 = new BufferedReader(new FileReader(file1));
					BufferedReader reader2 = new BufferedReader(new FileReader(file2));
					String line1;
					String line2; 
					
					
					// First file making temporary- deleting empty lines, spaces and tabs
					 while ((line1 = reader1.readLine()) != null) {
						 if (!line1.trim().isEmpty()) {
				          sourceCode += line1.trim().replaceAll(" +", " ") +"\n";
						 }
						 else {
					      sourceCode += line1.trim()+"";
						 }
				     }
					///* writes to file *///
			          printer1.write(sourceCode);
			        ///* Closes PrintWriter *///
			          printer1.close();
			          
			          
			          
			          
			       // Second file making temporary- deleting empty lines, spaces and tabs
			          while ((line2 = reader2.readLine()) != null) {
							 if (!line2.trim().isEmpty()) {
					          sourceCode += line2.trim().replaceAll(" +", " ") +"\n";
							 }
							 else {
							      sourceCode += line2.trim()+"";
								 }
					     }
						///* writes to file *///
				          printer2.write(sourceCode);
				        ///* Closes PrintWriter *///
				          printer2.close();
				          
				          
				          
				          
				          
				          

						reader1.close();
						reader2.close();
				}
	    catch(IOException io) {
	    
	    }
	}
	
		
		
		///* Creates two temporary files for comparing *///
				public void showDiffs(String filename1, String filename2) {
			 
					
			}	
	
		
		
		
		
	
	
	
	
 
	///* Returns false if files aren't the same, ignores lines*///
	public boolean compareFilesIgnoreLines(String filename1, String filename2, ArrayList<Integer> array) {
 

		try {
			File file1 = new File(filename1);
			File file2 = new File(filename2);
			BufferedReader reader1 = new BufferedReader(new FileReader(file1));
			BufferedReader reader2 = new BufferedReader(new FileReader(file2));
			String line1;
			String line2;
			int i=1;  
				while ((line1 = reader1.readLine()) != null && ((line2 = reader2.readLine()) != null)) {
					if(!diff(line1, line2) && !array.contains(i)) {
						reader1.close();
						reader2.close();
						return false;
					}
					i++;
				}
				reader1.close();
				reader2.close();
		}
    catch(IOException io) {
    
    }
return true;
 
}
	public String linkToFileFormat(String str) {
		String pageLink="";
		pageLink = str.replaceAll("/", "%%");
		return pageLink;
	}
	public String fileFormatToLink(String str) {
		String pageLink="";
		pageLink = str.replaceAll("%%", "/");
		return pageLink;
	}
	

}