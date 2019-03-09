package group9rcraggs.application;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import group9rcraggs.application.domain.Notifications;
import group9rcraggs.application.domain.Page;
import group9rcraggs.application.domain.Website;

@Component
public class ScheduledTasks {
	
	@Autowired 
	private group9rcraggs.application.repository.PageRepository pageRepo;
	@Autowired 
	private group9rcraggs.application.repository.WebsiteRepository webRepo;
	@Autowired 
	private group9rcraggs.application.repository.NotificationsRepository alertRepo;
	
	Tracking track = new Tracking();
	
	
	///* This method runs automatically every 30 seconds to check for page differences *///
    @Scheduled(fixedDelay = 30000)
    public void scanForChanges() {
    	String ignore="";
    	///* Downloads source code to file *///
    	 
     	for (Page ww : pageRepo.findAll()) {
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
         	

         	Notifications alert = new Notifications();
         	alert.setPage(ww);
         	alert.setTitle(ww.getName() + " was updated");
         	alert.setText(ww.getName() + " was updated at: " + dtf.format(now));
         	alert.setOwner(ww.getOwner().getOwner());
         	
         	
         	
         	alertRepo.save(alert);
         	
         	
         	
         	///* Updates last updated time to current time *///
         	ww.setLastUpdated(dtf.format(now));
         	///* Saves to database *///
         	ww.setChecked(false);
         	pageRepo.save(ww);
         }
         else {
        	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
          	LocalDateTime now = LocalDateTime.now();
       
	        Notifications alert = new Notifications();
	        alert.setPage(ww);
	      	alert.setTitle(ww.getName() + " was not updated");
	      	alert.setText(ww.getName() + " was checked at: " + dtf.format(now));
	      	alert.setOwner(ww.getOwner().getOwner());
	      	
	      	alertRepo.save(alert);
         }
         
         
         }
     	}
}
    @Scheduled(fixedDelay = 30000)
    public void scanIfNotChecked() {
    	List<Page> pages = (List<Page>) pageRepo.findAll();
    	pages.parallelStream().forEach((ww) -> {
    		if(ww.getChecked() == false && ww.getTracking() == true) {
    		Tracking track = new Tracking();
 			track.sourceCodeToFile(ww.getUrl(), track.linkToFileFormat(ww.getUrl())+"_0");
 			try {
 				Thread.sleep(150000);
 			} catch (InterruptedException e) {
 				
 				e.printStackTrace();
 			}
 			track.sourceCodeToFile(ww.getUrl(), track.linkToFileFormat(ww.getUrl())+"_1");
 	 	   //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
 	 	   //LocalDateTime now = LocalDateTime.now();  
 			
 			//ww.setLastUpdated(dtf.format(now));
 			ww.setFileName(track.linkToFileFormat(ww.getUrl())+"_0");

 	    	
 	    	ArrayList<Integer> linesToBeIgnored = track.compareFiles("pageDB/"+track.linkToFileFormat(ww.getUrl())+"_0",
 	    			"pageDB/"+track.linkToFileFormat(ww.getUrl())+"_1");
 			ww.setLinesIgnored(linesToBeIgnored.toString());
 			ww.setChecked(true);
 			pageRepo.save(ww);
    		}
        });
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
    
}
    
