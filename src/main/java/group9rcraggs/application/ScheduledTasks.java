package group9rcraggs.application;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import group9rcraggs.application.domain.Page;

@Component
public class ScheduledTasks {
	
	@Autowired 
	private group9rcraggs.application.repository.PageRepository pageRepo;
	
	Tracking track = new Tracking();
	Page ww = new Page();
	String ignore="";
	
	///* This method runs automatically every 5 seconds to check for page differences *///
    @Scheduled(fixedDelay = 5000)
    public void reportCurrentTime() {
    	
    	///* Downloads source code to file *///
    	 track.sourceCodeToFile("https://idomu.ax.lt", "idomu.ax.lt_1");
         
    	///* Checks if page exists*///
            if(pageRepo.existsById(3)) {
            	ww = pageRepo.findById(3);
            ignore = ww.getLinesIgnored();
 		    ignore = ignore.substring(1);
 		    ignore = ignore.substring(0, ignore.length()-1);
 		 ///* Gets lines ignored from database and converts to array to use in compareFilesIgnoreLines *///
 		    String[] integerStrings = ignore.split(", "); 
 		    int[] integers = new int[integerStrings.length]; 
 		    ArrayList<Integer> arr = new ArrayList<Integer>();
 		    for (int i = 0; i < integers.length; i++){
 		        arr.add(Integer.parseInt(integerStrings[i])); 
 		    }
 		 ///* Checks if file other than ignored lines changes *///
         if(!track.compareFilesIgnoreLines("idomu.ax.lt_0", "idomu.ax.lt_1", arr)) {
         	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
         	LocalDateTime now = LocalDateTime.now(); 
         	///* Updates last updated time to current time *///
         	ww.setLastUpdated(dtf.format(now));
         	///* Saves to database *///
         	pageRepo.save(ww);
         }
            }
}
}