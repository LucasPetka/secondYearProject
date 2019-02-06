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
	
    @Scheduled(fixedDelay = 5000)
    public void reportCurrentTime() {
    	
    
    	 track.sourceCodeToFile("https://idomu.ax.lt", "idomu.ax.lt_1");
         
            if(pageRepo.existsById(3)) {
            	ww = pageRepo.findById(3);
            ignore = ww.getLinesIgnored();
 		    ignore = ignore.substring(1);
 		    ignore = ignore.substring(0, ignore.length()-1);
 		    // The string you want to be an integer array.
 		    String[] integerStrings = ignore.split(", "); 
 		    // Splits each spaced integer into a String array.
 		    int[] integers = new int[integerStrings.length]; 
 		    // Creates the integer array.
 		    ArrayList<Integer> arr = new ArrayList<Integer>();
 		    for (int i = 0; i < integers.length; i++){
 		        arr.add(Integer.parseInt(integerStrings[i])); 
 		    }
         if(!track.compareFilesIgnoreLines("idomu.ax.lt_0", "idomu.ax.lt_1", arr)) {
         	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
         	LocalDateTime now = LocalDateTime.now(); 
         	ww.setLastUpdated(dtf.format(now));
         	pageRepo.save(ww);
         }
            }
}
}