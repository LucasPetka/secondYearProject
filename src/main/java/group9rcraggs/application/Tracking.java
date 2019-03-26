package group9rcraggs.application;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
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
	
			
			String sourceCode1 = "";
			String sourceCode2 = "";
				try {
					
					FileOutputStream output1 = new FileOutputStream(filename1+"_temp1");
					FileOutputStream output2 = new FileOutputStream(filename2+"_temp2");
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
				          sourceCode1 += line1.trim().replaceAll(" +", " ") +"\n";
						 }
						 else {
					      sourceCode1 += line1.trim()+"";
						 }
				     }
					///* writes to file *///
			          printer1.write(sourceCode1);
			        ///* Closes PrintWriter *///
			          printer1.close();
			          
			          
			          
			          
			       // Second file making temporary- deleting empty lines, spaces and tabs
			          while ((line2 = reader2.readLine()) != null) {
							 if (!line2.trim().isEmpty()) {
					          sourceCode2 += line2.trim().replaceAll(" +", " ") +"\n";
							 }
							 else {
							      sourceCode2 += line2.trim()+"";
								 }
					     }
						///* writes to file *///
				          printer2.write(sourceCode2);
				        ///* Closes PrintWriter *///
				          printer2.close();
				          
				          
				          
				          
				          
				          

						reader1.close();
						reader2.close();
				}
	    catch(IOException io) {
	    
	    }
	}
		
		
		
		
		
		
		
	
		
		
		///* Creates two temporary files for comparing *///
				public void createArray(String filename1, String filename2, String answerfile) {
			 
					ArrayList<String> a1 = new ArrayList<String>();
					ArrayList<String> a2 = new ArrayList<String>();
					
					
					
					try {
						///* Takes two different files and calls diff on each line *///
						File file1 = new File(filename1);
						File file2 = new File(filename2);
						BufferedReader reader1 = new BufferedReader(new FileReader(file1));
						BufferedReader reader2 = new BufferedReader(new FileReader(file2));
						String line1;
						String line2;
						
							while ((line1 = reader1.readLine()) != null && ((line2 = reader2.readLine()) != null)) {

									a1.add(line1);
									a2.add(line2);
								 
							}
							
							String[] first = null;
							first = a1.toArray(new String[a1.size()]);
							String[] second = null;
							second = a2.toArray(new String[a2.size()]);

							
							int x1 = 0;
							int y1 = 0;
							int [][]matrix = new int [first.length+1] [second.length+1];
							
							for(var y=0; y<matrix.length; y++){

								for(var x=0; x<matrix[y].length; x++){
									matrix[y][x] = 0;
									
								}
							}
							
							
							
							for(var y=1; y<matrix.length; y++){
								for(var x=1; x<matrix[y].length; x++){
									if(first[y-1].equals(second[x-1])){
										matrix[y][x] = 1 + matrix[y-1][x-1];
									} else {
										matrix[y][x] = Math.max(matrix[y-1][x], matrix[y][x-1]);
									}
									x1 = x;
									y1 = y;
								}		
							}
							
							
							getDiff(matrix, first, second, x1-1, y1-1, answerfile);
							
							
							
							reader1.close();
							reader2.close();
					}
		    catch(IOException io) {
		    
		    }
				
			}	
	
		
				
	
	public void getDiff(int [][] matrix,  String[] a1, String[] a2, int x, int y, String answerfile) throws IOException {
		
		
		if(x>0 && y>0 && a1[y-1].equals(a2[x-1])){
			getDiff(matrix, a1, a2, x-1, y-1, answerfile);
			maakRij(x, y, ' ', a1[y-1], answerfile);
			
		} else {
			if(x>0 && (y==0 || matrix[y][x-1] >= matrix[y-1][x])){
				getDiff(matrix, a1, a2, x-1, y, answerfile);
				maakRij(x, 0, '+', a2[x-1], answerfile);
				
			} else if(y>0 && (x==0 || matrix[y][x-1] < matrix[y-1][x])){
				getDiff(matrix, a1, a2, x, y-1, answerfile);
				maakRij(0, y, '-', a1[y-1], answerfile);
				
			} else {
				return;
			}
		}
	}		
	

	
	public void maakRij(int x, int y, char type, String rij, String filename) throws IOException{
		
		
	    BufferedWriter writer = new BufferedWriter(new FileWriter("pageDB/"+ filename, true));
	    
	    writer.append("T: " + type + "  " + rij);
	    writer.append('\n');
	     
	    writer.close();


	}
	
	public void deleteTemp(String filename1, String filename2) {
		
		File file1 = new File(filename1);
		File file2 = new File(filename2);
        file1.delete();
        file2.delete();
		
		
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