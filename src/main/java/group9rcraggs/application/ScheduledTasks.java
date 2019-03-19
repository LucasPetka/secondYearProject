package group9rcraggs.application;




import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
    	
    	
    	List<Page> pages = (List<Page>) pageRepo.findAll();
    	pages.parallelStream().forEach((ww) -> {
    		
    		String ignore="";
     		if(!ww.getChecked()) {
             	scanIfNotChecked(ww);
     		}
     		
        	///* Downloads source code to file to compare to original*///
     		if(ww.getChecked() == true && ww.getTracking() == true) {
     		track.sourceCodeToFile(ww.getUrl(), track.linkToFileFormat(ww.getUrl())+"_1");
            ignore = ww.getLinesIgnored();
            ArrayList<Integer> arr = new ArrayList<Integer>();
 		    ignore = ignore.substring(1);
 		    ignore = ignore.substring(0, ignore.length()-1);
 		 ///* Gets lines ignored from database and converts to array to use in compareFilesIgnoreLines *///
 		    String[] integerStrings = ignore.split(", "); 
 		    int[] integers = new int[integerStrings.length]; 
 		    
 		    try {
 		    for (int i = 0; i < integers.length; i++){
 		     arr.add(Integer.parseInt(integerStrings[i]));  
 		        
 		    }
 		    }
 		    catch(Exception e) {
 		    	
 		    }
 		   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
 		  LocalDateTime now = LocalDateTime.now();
 		 ///* Checks if file other than ignored lines changes *///
         if(!track.compareFilesIgnoreLines("pageDB/"+track.linkToFileFormat(ww.getUrl())+"_0", 
        		 "pageDB/"+track.linkToFileFormat(ww.getUrl())+"_1", arr)) {

        	//Emails user that page changed
         	alertUser(ww);

         	
         	///* Updates last updated time to current time *///
         	ww.setLastUpdated(dtf.format(now).toString());
         	///* Saves to database *///
         	ww.setChecked(false);
         	pageRepo.save(ww);
         	scanIfNotChecked(ww);
         }
         //Checks if it's time to nag user
         else {
        	 if(!ww.getLastUpdated().equals("Not Yet Tracked/Changed")) {
        	 checkElapsedTime(ww, ww.getLastUpdated(), 
        			 dtf.format(now));
        	 }
         }
         
         
         }
    	
});
    }
    
    public void scanIfNotChecked(Page p) {

    	//Checks if tracking is turned on - i.e user wants to track page
    		if(p.getTracking() == true) {
    		ArrayList<Integer> linesToBeIgnored = new ArrayList<Integer>();
    		Tracking track = new Tracking();
 			track.sourceCodeToFile(p.getUrl(), track.linkToFileFormat(p.getUrl())+"_0");
 			p.setFileName(track.linkToFileFormat(p.getUrl())+"_0");
 			//Updates page file name
 			pageRepo.save(p);
 			
 			try {
 				//60 seconds
 				Thread.sleep(30000);
 				createLinesIgnored(p, track, linesToBeIgnored, 1);
 				//1 hour
 				Thread.sleep(0);
 				createLinesIgnored(p, track, linesToBeIgnored, 2);
 				//23 hours
 				Thread.sleep(30000);
 				createLinesIgnored(p, track, linesToBeIgnored, 3);
 			} catch (InterruptedException e) {
 				
 				e.printStackTrace();
 			}
 			
 			p.setChecked(true);
 			pageRepo.save(p);
    		}
    		
        }
    
    public void createLinesIgnored(Page p, Tracking track, ArrayList<Integer> linesToBeIgnored, int version) {
    	
    	//Downloads HTML to new file 
    	track.sourceCodeToFile(p.getUrl(), track.linkToFileFormat(p.getUrl())+"_" + version);
    	
    	//Checks if the two files are different - if so adds lines to linesToBeIgnored
    	linesToBeIgnored.addAll(track.compareFiles("pageDB/"+track.linkToFileFormat(p.getUrl())+"_0",
	    			"pageDB/"+track.linkToFileFormat(p.getUrl())+"_" + version));
    	
    	//Updates lines ignored
    	p.setLinesIgnored(linesToBeIgnored.toString());
    	pageRepo.save(p);
    	
    	
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
    	
    	
    	emailService.sendEmail(email, "Your website " + p.getName() + " has been updated!", "Your website has been updated");
    }
     
     void checkElapsedTime(Page p, String lastUpdated, String now) {
    	 
    	 String currentDate = now.toString();
    	 String lastUpdatedd = lastUpdated.toString();
    	 long lastUpdatedLong = convertDateToLong(lastUpdatedd);
    	 long currentTime = convertDateToLong(currentDate);
    	 
    	 if((currentTime - lastUpdatedLong) > p.getAlertAfter() &&  !p.getWarning()) {
    		 
    		 emailService.sendEmail(p.getEmail(), "Your website " + p.getName() + " needs updating!", "Your website needs updating!");
    		  p.setWarning(true);
    		  pageRepo.save(p);
    	 
     }
    	 if((currentTime - lastUpdatedLong - 100) > p.getAlertAfter() && p.getWarning()) {
    		 emailService.sendEmail(p.getEmail(), "Your website " + p.getName() + " needs updating!", "Your website needs updating! - Reminder!");
    	 }
     }
    	 
    	
     long convertDateToLong(String stringDate) {
    	 
    	 long result=0;
    	 SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
  		try {
  			//Converts String back into date
  			Date date = simpleDateFormat.parse(stringDate);
  	        simpleDateFormat = new SimpleDateFormat("ddMMyyyyHHmm");
  	        //Converts date back into string with new format
  	        String value = simpleDateFormat.format(date);
  	        //Parse String to long
  	        result = Long.parseLong(value);
  	       
      }
  		catch(Exception e) {
  			
  		}
     
  		 return result;
     
 
     
     }
}
