package group9rcraggs.application;



import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import group9rcraggs.application.domain.Page;
import group9rcraggs.application.domain.Website;
import group9rcraggs.application.EmailService;

@Component
public class ScheduledTasks {
	
	@Autowired 
	private group9rcraggs.application.repository.PageRepository pageRepo;
	@Autowired 
	private group9rcraggs.application.repository.WebsiteRepository webRepo;

	@Autowired
    private EmailService emailService;
	
	Tracking track = new Tracking();
	
	
	///* This method runs automatically every 30 seconds to check for page differences *///
    @Scheduled(fixedDelay = 10000)
    public void scanForChanges() {
    	
    	String ignore="";
    	///* Downloads source code to file *///
    	 
     	for (Page ww : pageRepo.findAll()) {
     		if(ww.getChecked() == false) {
             	scanIfNotChecked(ww);
     		}
     		if(ww.getChecked() == true && ww.getTracking() == true) {
     		track.sourceCodeToFile(ww.getUrl(), track.linkToFileFormat(ww.getUrl())+"_1");
            ignore = ww.getLinesIgnored();
            ArrayList<Integer> arr = new ArrayList<Integer>();
 		    ignore = ignore.substring(1);
 		    ignore = ignore.substring(0, ignore.length()-1);
 		 ///* Gets lines ignored from database and converts to array to use in compareFilesIgnoreLines *///
 		    String[] integerStrings = ignore.split(", "); 
 		    int[] integers = new int[integerStrings.length]; 
 		    
 		    
 		    for (int i = 0; i < integers.length; i++){
 		    	try { arr.add(Integer.parseInt(integerStrings[i]));  } catch (Exception nfe) { }

 		        
 		    }
     		
 		 ///* Checks if file other than ignored lines changes *///
         if(!track.compareFilesIgnoreLines("pageDB/"+track.linkToFileFormat(ww.getUrl())+"_0", 
        		 "pageDB/"+track.linkToFileFormat(ww.getUrl())+"_1", arr)) {
         	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
         	LocalDateTime now = LocalDateTime.now();
         	
         	
        	//Emails user that page changed
         	alertUser(ww);

         	
         	///* Updates last updated time to current time *///
         	ww.setLastUpdated(dtf.format(now));
         	///* Saves to database *///
         	ww.setChecked(false);
         	pageRepo.save(ww);
         	scanIfNotChecked(ww);
         }
         
         
         }
     	}
}
    public void scanIfNotChecked(Page ww) {

    		if(ww.getTracking() == true) {
    		Tracking track = new Tracking();
 			track.sourceCodeToFile(ww.getUrl(), track.linkToFileFormat(ww.getUrl())+"_0");
 			try {
 				Thread.sleep(70000);
 			} catch (InterruptedException e) {
 				
 				e.printStackTrace();
 			}
 			track.sourceCodeToFile(ww.getUrl(), track.linkToFileFormat(ww.getUrl())+"_1");

 			ww.setFileName(track.linkToFileFormat(ww.getUrl())+"_0");

 	    	ArrayList<Integer> linesToBeIgnored = track.compareFiles("pageDB/"+track.linkToFileFormat(ww.getUrl())+"_0",
 	    			"pageDB/"+track.linkToFileFormat(ww.getUrl())+"_1");
 			ww.setLinesIgnored(linesToBeIgnored.toString());
 			ww.setChecked(true);
 			pageRepo.save(ww);
    		}
    		
        }

     
    @Scheduled(fixedDelay = 60000)
    public void periodicTasks() {
    	List<Website> websites = (List<Website>) webRepo.findAll();
    	List<Page> pages = (List<Page>) pageRepo.findAll();
    	
    	for(Website w : websites) {
    		int numPages=0;
    		for(Page p : pages) {
    			if(w.getId()==p.getOwner().getId()) {
    				numPages++;
    				w.setActivePages(numPages);
    			}
    		}
    	webRepo.save(w);
    	}
    	
    	
    }
    
     void alertUser(Page p) {
    	
    	//gets user who owns page
    	String email = p.getEmail();
    	
    	
    	emailService.sendEmail(p.getEmail(), "TEST", "SUB");
    }
    
}
    
