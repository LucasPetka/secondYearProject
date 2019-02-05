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


 
///*Gets source code from a given url*///
 
public String sourceCodeToFile(String url, String fileName) {
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
         
          printer.write(sourceCode);
          printer.close();

          
          /* close BufferedReader */
          in.close();
          /* close HttpURLConnection */
          con.disconnect();
      } catch (Exception e) {
          e.printStackTrace();
      }
      
      return sourceCode;
}
 
public static boolean diff(String string1, String string2) {
 
if (string1.equals(string2)) return true;
return false;
 
}
public ArrayList<Integer> compareFiles(String filename1, String filename2) {
 
ArrayList<Integer> array = new ArrayList();
 
try {
    File file1 = new File(filename1);
    File file2 = new File(filename2);
    BufferedReader reader1 = new BufferedReader(new FileReader(file1));
    BufferedReader reader2 = new BufferedReader(new FileReader(file2));
    String line1;
    String line2;
int i=1;  
while ((line1 = reader1.readLine()) != null && ((line2 = reader2.readLine()) != null)) {
   
if(!diff(line1, line2)) {
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

}