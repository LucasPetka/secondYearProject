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
        	 
        	 
        	 track.createcomparedFile("pageDB/"+track.linkToFileFormat(ww.getUrl())+"_0", "pageDB/"+track.linkToFileFormat(ww.getUrl())+"_1");
        	 

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
    	
    	String mail = "\n" + 
    			"<!DOCTYPE html>\n" + 
    			"<html lang=\"en\" >\n" + 
    			"\n" + 
    			"<head>\n" + 
    			"  <meta charset=\"UTF-8\">\n" + 
    			"  <title>NetNag update alert</title>\n" + 
    			"  \n" + 
    			"  \n" + 
    			"  \n" + 
    			"  \n" + 
    			"  \n" + 
    			"</head>\n" + 
    			"\n" + 
    			"<body>\n" + 
    			"\n" + 
    			"  <head>\n" + 
    			"  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" + 
    			"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" + 
    			"  <title>Revue</title>\n" + 
    			"  <style type=\"text/css\">\n" + 
    			"    #outlook a {padding:0;}\n" + 
    			"    body{width:100% !important; -webkit-text-size-adjust:100%; "
    			+ "-ms-text-size-adjust:100%; margin:0; padding:0;-webkit-font-"
    			+ "smoothing: antialiased;-moz-osx-font-smoothing: grayscale;} \n" + 
    			"    .ExternalClass {width:100%;}\n" + 
    			"    .ExternalClass, .ExternalClass p, .ExternalClass span, "
    			+ ".ExternalClass font, .ExternalClass td, .ExternalClass div, ."
    			+ "ExternalClass blockquote {line-height: 100%;}\n" + 
    			"    .ExternalClass p, .ExternalClass blockquote {margin-bottom: 0; margin: 0;}\n" + 
    			"    #backgroundTable {margin:0; padding:0; width:100% "
    			+ "!important; line-height: 100% !important;}\n" + 
    			"    \n" + 
    			"    img {outline:none; text-decoration:none; -ms-interpolation-mode: bicubic;} \n" + 
    			"    a img {border:none;} \n" + 
    			"    .image_fix {display:block;}\n" + 
    			"\n" + 
    			"    p {margin: 1em 0;}\n" + 
    			"\n" + 
    			"    h1, h2, h3, h4, h5, h6 {color: black !important;}\n" + 
    			"    h1 a, h2 a, h3 a, h4 a, h5 a, h6 a {color: black;}\n" + 
    			"    h1 a:active, h2 a:active,  h3 a:active, h4 a:active, h5 "
    			+ "a:active, h6 a:active {color: black;}\n" + 
    			"    h1 a:visited, h2 a:visited,  h3 a:visited, h4 a:visited, "
    			+ "h5 a:visited, h6 a:visited {color: black;}\n" + 
    			"\n" + 
    			"    table td {border-collapse: collapse;}\n" + 
    			"    table { border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt; }\n" + 
    			"\n" + 
    			"    a {color: #3498db;}\n" + 
    			"    #yes{\n" + 
    			"\n" + 
    			"    }\n" + 
    			"    #no{\n" + 
    			"\n" + 
    			"    }\n" + 
    			"    p.domain a{color: black;}\n" + 
    			"\n" + 
    			"    hr {border: 0; background-color: #d8d8d8; margin: 0; margin-bottom: 0; height: 1px;}\n" + 
    			"\n" + 
    			"    @media (max-device-width: 667px) {\n" + 
    			"      a[href^=\"tel\"], a[href^=\"sms\"] {\n" + 
    			"        text-decoration: none;\n" + 
    			"        color: blue;\n" + 
    			"        pointer-events: none;\n" + 
    			"        cursor: default;\n" + 
    			"      }\n" + 
    			"\n" + 
    			"      .mobile_link a[href^=\"tel\"], .mobile_link a[href^=\"sms\"] {\n" + 
    			"        text-decoration: default;\n" + 
    			"        color: orange !important;\n" + 
    			"        pointer-events: auto;\n" + 
    			"        cursor: default;\n" + 
    			"      }\n" + 
    			"\n" + 
    			"      h1[class=\"profile-name\"], h1[class=\"profile-name\"] a {\n" + 
    			"        font-size: 32px !important;\n" + 
    			"        line-height: 38px !important;\n" + 
    			"        margin-bottom: 14px !important;\n" + 
    			"      }\n" + 
    			"\n" + 
    			"      span[class=\"issue-date\"], span[class=\"issue-date\"] a {\n" + 
    			"        font-size: 14px !important;\n" + 
    			"        line-height: 22px !important;\n" + 
    			"      }\n" + 
    			"\n" + 
    			"      td[class=\"description-before\"] {\n" + 
    			"        padding-bottom: 28px !important;\n" + 
    			"      }\n" + 
    			"      td[class=\"description\"] {\n" + 
    			"        padding-bottom: 14px !important;\n" + 
    			"      }\n" + 
    			"      td[class=\"description\"] span, span[class=\"item-text\"], span[class=\"item-text\"] span {\n" + 
    			"        font-size: 16px !important;\n" + 
    			"        line-height: 24px !important;\n" + 
    			"      }\n" + 
    			"\n" + 
    			"      span[class=\"item-link-title\"] {\n" + 
    			"        font-size: 18px !important;\n" + 
    			"        line-height: 24px !important;\n" + 
    			"      }\n" + 
    			"\n" + 
    			"      span[class=\"item-header\"] {\n" + 
    			"        font-size: 22px !important;\n" + 
    			"      }\n" + 
    			"\n" + 
    			"      span[class=\"item-link-description\"], span[class=\"item-link-description\"] span {\n" + 
    			"        font-size: 14px !important;\n" + 
    			"        line-height: 22px !important;\n" + 
    			"      }\n" + 
    			"\n" + 
    			"      .link-image {\n" + 
    			"        width: 84px !important;\n" + 
    			"        height: 84px !important;\n" + 
    			"      }\n" + 
    			"\n" + 
    			"      .link-image img {\n" + 
    			"        max-width: 100% !important;\n" + 
    			"        max-height: 100% !important;\n" + 
    			"      }\n" + 
    			"    }\n" + 
    			"\n" + 
    			"    @media (max-width: 500px) {\n" + 
    			"      .column {\n" + 
    			"        display: block !important;\n" + 
    			"        width: 100% !important;\n" + 
    			"        padding-bottom: 8px !important;\n" + 
    			"      }\n" + 
    			"    }\n" + 
    			"\n" + 
    			"    @media only screen and (min-device-width: 768px) and (max-device-width: 1024px) {\n" + 
    			"      a[href^=\"tel\"], a[href^=\"sms\"] {\n" + 
    			"        text-decoration: none;\n" + 
    			"        color: blue;\n" + 
    			"        pointer-events: none;\n" + 
    			"        cursor: default;\n" + 
    			"      }\n" + 
    			"\n" + 
    			"      .mobile_link a[href^=\"tel\"], .mobile_link a[href^=\"sms\"] {\n" + 
    			"        text-decoration: default;\n" + 
    			"        color: orange !important;\n" + 
    			"        pointer-events: auto;\n" + 
    			"        cursor: default;\n" + 
    			"      }\n" + 
    			"    }\n" + 
    			"\n" + 
    			"  </style>\n" + 
    			"  <!--[if gte mso 9]>\n" + 
    			"    <style type=\"text/css\">\n" + 
    			"      #contentTable {\n" + 
    			"        width: 600px;\n" + 
    			"      }\n" + 
    			"    </style>\n" + 
    			"  <![endif]-->\n" + 
    			"</head>\n" + 
    			"<body style=\"width:100% !important;-webkit-text-size-adjust:100%;-ms-te"
    			+ "xt-size-adjust:100%;margin-top:0;margin-bottom:0;margin-right:0;marg"
    			+ "in-left:0;padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\">\n" + 
    			"<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" id=\"backgrou"
    			+ "ndTable\" style=\"margin:0; padding:0; width:100% !important; line-he"
    			+ "ight: 100% !important; border-collapse:collapse; mso-table-lspace:0pt; mso"
    			+ "-table-rspace:0pt;background-color: #F9FAFB;\" width=\"100%\">\n" + 
    			"  <tbody><tr>\n" + 
    			"    <td width=\"10\" valign=\"top\">&nbsp;</td>\n" + 
    			"    <td valign=\"top\" align=\"center\">\n" + 
    			"      <!--[if (gte mso 9)|(IE)]>\n" + 
    			"      <table width=\"600\" align=\"center\" cellpadding=\"0\" cellspac"
    			+ "ing=\"0\" border=\"0\" style=\"background-color: #FFF; border-collapse:coll"
    			+ "apse;mso-table-lspace:0pt;mso-table-rspace:0pt;\">\n" + 
    			"        <tr>\n" + 
    			"          <td>\n" + 
    			"      <![endif]-->\n" + 
    			"      <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" "
    			+ "style=\"width: 100%; max-width: 600px; background-color: #FFF; border"
    			+ "-collapse:collapse;mso-table-lspace:0pt;mso-table-rspace:0pt;\" id=\"contentTable\">\n" + 
    			"        <tbody><tr>\n" + 
    			"          <td width=\"600\" valign=\"top\" align=\"center\" "
    			+ "style=\"border-collapse:collapse;\">\n" + 
    			"            <table align=\"center\" border=\"0\" cellpadding=\"0\" "
    			+ "cellspacing=\"0\" style=\"background: #F9FAFB;\" width=\"100%\">\n" + 
    			"<tbody><tr>\n" + 
    			"<td align=\"center\" valign=\"top\">\n" + 
    			"<div style=\"font-family: &quot;lato&quot;, &quot;Helvetica Neue&quot;, "
    			+ "Helvetica, Arial, sans-serif; line-height: 28px;\">&nbsp;</div>\n" + 
    			"</td>\n" + 
    			"</tr>\n" + 
    			"</tbody></table>\n" + 
    			"<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" "
    			+ "style=\"border: 1px solid #E0E4E8;\" width=\"100%\">\n" + 
    			"<tbody><tr>\n" + 
    			"<td align=\"center\" style=\"padding: 56px 56px 28px 56px;\" valign=\"top\">\n" + 
    			"<a style=\"color: #3498DB; text-decoration: none;\" href=\"\" target=\"_blank\">"
    			+ "<img style=\"width: 56px; height: 56px; border: 0;\" alt=\"NetNag Logo\" src=\"https:"
    			+ "//localhost:8090/img/logo.png\">\n" + 
    			"</a></td>\n" + 
    			"</tr>\n" + 
    			"<tr>\n" + 
    			"<td align=\"center\" style=\"padding: 0 56px 28px 56px;\" valign=\"top\">\n" + 
    			"<div style=\"font-family: &quot;lato&quot;, &quot;Helvetica Neue&quot;,"
    			+ " Helvetica, Arial, sans-serif; line-height: 28px;font-size: 20px; "
    			+ "color: #333;\"><strong> "+ p.getName()  +" from " + p.getOwnerUrl() + "</st"
    					+ "rong> was updated take a look at changes and confirm or decline them.</div>\n" + 
    			"</td>\n" + 
    			"</tr>\n" + 
    			"<tr>\n" + 
    			"<td align=\"center\" style=\"padding: 0 56px; padding-bottom: 56px;\" valign=\"top\">\n" + 
    			"<div>\n" + 
    			"      <a href=\"#\" style=\"background-color:#E15718;border-radius:50px;"
    			+ "color:#ffffff;display:inline-block;font-family: 'lato', 'Helvetica Neue'"
    			+ ", Helvetica, Arial, sans-serif;font-size:18px;font-weight: bold;line-height"
    			+ ":40px;text-align:center;text-decoration:none;width:270px;-webkit-text-size-"
    			+ "adjust:none;\" target=\"_blank\">Confirm subscription now</a><br>\n" + 
    			"\n" + 
    			"      <a href=\"#\" style=\"background-color:#00D687; margin-top:20px; "
    			+ "border-radius:50px;color:#ffffff;display:inline-block;font-family: "
    			+ "'lato', 'Helvetica Neue', Helvetica, Arial, sans-serif;font-size:18px;"
    			+ "font-weight: bold;line-height:40px;text-align:center;text-decoration:"
    			+ "none;width:200px; float:left; margin-right: 80px; -webkit-text-size-adjust"
    			+ ":none;\" target=\"_blank\">Accept changes</a>\n" + 
    			"      <a href=\"#\" style=\"background-color:#F22733; margin-top:20px; "
    			+ "border-radius:50px;color:#ffffff;display:inline-block; float:left; "
    			+ "font-family: 'lato', 'Helvetica Neue', Helvetica, Arial, sans-serif;font-"
    			+ "size:18px;font-weight: bold;line-height:40px;text-align:center;text-decoration"
    			+ ":none;width:200px;-webkit-text-size-adjust:none;\" target=\"_blank\">Decline "
    			+ "changes</a>\n" + 
    			"</div>\n" + 
    			"\n" + 
    			"</td>\n" + 
    			"</tr>\n" + 
    			"\n" + 
    			"</tbody></table>\n" + 
    			"\n" + 
    			"\n" + 
    			"          </td>\n" + 
    			"        </tr>\n" + 
    			"      </tbody></table>\n" + 
    			"\n" + 
    			"    </td>\n" + 
    			"    <td width=\"10\" valign=\"top\">&nbsp;</td>\n" + 
    			"  </tr>\n" + 
    			"</tbody></table> \n" + 
    			"\n" + 
    			"\n" + 
    			"\n" + 
    			"</body>\n" + 
    			"  \n" + 
    			"  \n" + 
    			"\n" + 
    			"</body>\n" + 
    			"\n" + 
    			"</html>\n" + 
    			"";
    	
    	
    	emailService.sendEmail(email, mail, "Your website has been updated");
    }
     
     void checkElapsedTime(Page p, String lastUpdated, String now) {
    	 
    	 String currentDate = now.toString();
    	 String lastUpdatedd = lastUpdated.toString();
    	 long lastUpdatedLong = convertDateToLong(lastUpdatedd);
    	 long currentTime = convertDateToLong(currentDate);
    	 
    	 String mail = "\n" + 
    	 		"<!DOCTYPE html>\n" + 
    	 		"<html lang=\"en\" >\n" + 
    	 		"\n" + 
    	 		"<head>\n" + 
    	 		"  <meta charset=\"UTF-8\">\n" + 
    	 		"  <title>NetNag update alert</title>\n" + 
    	 		"  \n" + 
    	 		"  \n" + 
    	 		"  \n" + 
    	 		"  \n" + 
    	 		"  \n" + 
    	 		"</head>\n" + 
    	 		"\n" + 
    	 		"<body>\n" + 
    	 		"\n" + 
    	 		"  <head>\n" + 
    	 		"  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">\n" + 
    	 		"  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" + 
    	 		"  <title>Revue</title>\n" + 
    	 		"  <style type=\"text/css\">\n" + 
    	 		"    #outlook a {padding:0;}\n" + 
    	 		"    body{width:100% !important; -webkit-text-size-adjust:100%; -ms"
    	 		+ "-text-size-adjust:100%; margin:0; padding:0;-webkit-font-smoothing: "
    	 		+ "antialiased;-moz-osx-font-smoothing: grayscale;} \n" + 
    	 		"    .ExternalClass {width:100%;}\n" + 
    	 		"    .ExternalClass, .ExternalClass p, .ExternalClass span, .External"
    	 		+ "Class font, .ExternalClass td, .ExternalClass div, .ExternalClass blockquote {line-height: 100%;}\n" + 
    	 		"    .ExternalClass p, .ExternalClass blockquote {margin-bottom: 0; margin: 0;}\n" + 
    	 		"    #backgroundTable {margin:0; padding:0; width:100% !important; line-height: 100% !important;}\n" + 
    	 		"    \n" + 
    	 		"    img {outline:none; text-decoration:none; -ms-interpolation-mode: bicubic;} \n" + 
    	 		"    a img {border:none;} \n" + 
    	 		"    .image_fix {display:block;}\n" + 
    	 		"\n" + 
    	 		"    p {margin: 1em 0;}\n" + 
    	 		"\n" + 
    	 		"    h1, h2, h3, h4, h5, h6 {color: black !important;}\n" + 
    	 		"    h1 a, h2 a, h3 a, h4 a, h5 a, h6 a {color: black;}\n" + 
    	 		"    h1 a:active, h2 a:active,  h3 a:active, h4 a:active, h5 a:active, h6 a:active {color: black;}\n" + 
    	 		"    h1 a:visited, h2 a:visited,  h3 a:visited, h4 a:visited, h5 a:visited, h6 a:visited {color: black;}\n" + 
    	 		"\n" + 
    	 		"    table td {border-collapse: collapse;}\n" + 
    	 		"    table { border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt; }\n" + 
    	 		"\n" + 
    	 		"    a {color: #3498db;}\n" + 
    	 		"    p.domain a{color: black;}\n" + 
    	 		"\n" + 
    	 		"    hr {border: 0; background-color: #d8d8d8; margin: 0; margin-bottom: 0; height: 1px;}\n" + 
    	 		"\n" + 
    	 		"    @media (max-device-width: 667px) {\n" + 
    	 		"      a[href^=\"tel\"], a[href^=\"sms\"] {\n" + 
    	 		"        text-decoration: none;\n" + 
    	 		"        color: blue;\n" + 
    	 		"        pointer-events: none;\n" + 
    	 		"        cursor: default;\n" + 
    	 		"      }\n" + 
    	 		"\n" + 
    	 		"      .mobile_link a[href^=\"tel\"], .mobile_link a[href^=\"sms\"] {\n" + 
    	 		"        text-decoration: default;\n" + 
    	 		"        color: orange !important;\n" + 
    	 		"        pointer-events: auto;\n" + 
    	 		"        cursor: default;\n" + 
    	 		"      }\n" + 
    	 		"\n" + 
    	 		"      h1[class=\"profile-name\"], h1[class=\"profile-name\"] a {\n" + 
    	 		"        font-size: 32px !important;\n" + 
    	 		"        line-height: 38px !important;\n" + 
    	 		"        margin-bottom: 14px !important;\n" + 
    	 		"      }\n" + 
    	 		"\n" + 
    	 		"      span[class=\"issue-date\"], span[class=\"issue-date\"] a {\n" + 
    	 		"        font-size: 14px !important;\n" + 
    	 		"        line-height: 22px !important;\n" + 
    	 		"      }\n" + 
    	 		"\n" + 
    	 		"      td[class=\"description-before\"] {\n" + 
    	 		"        padding-bottom: 28px !important;\n" + 
    	 		"      }\n" + 
    	 		"      td[class=\"description\"] {\n" + 
    	 		"        padding-bottom: 14px !important;\n" + 
    	 		"      }\n" + 
    	 		"      td[class=\"description\"] span, span[class=\"item-text\"], span[class=\"item-text\"] span {\n" + 
    	 		"        font-size: 16px !important;\n" + 
    	 		"        line-height: 24px !important;\n" + 
    	 		"      }\n" + 
    	 		"\n" + 
    	 		"      span[class=\"item-link-title\"] {\n" + 
    	 		"        font-size: 18px !important;\n" + 
    	 		"        line-height: 24px !important;\n" + 
    	 		"      }\n" + 
    	 		"\n" + 
    	 		"      span[class=\"item-header\"] {\n" + 
    	 		"        font-size: 22px !important;\n" + 
    	 		"      }\n" + 
    	 		"\n" + 
    	 		"      span[class=\"item-link-description\"], span[class=\"item-link-description\"] span {\n" + 
    	 		"        font-size: 14px !important;\n" + 
    	 		"        line-height: 22px !important;\n" + 
    	 		"      }\n" + 
    	 		"\n" + 
    	 		"      .link-image {\n" + 
    	 		"        width: 84px !important;\n" + 
    	 		"        height: 84px !important;\n" + 
    	 		"      }\n" + 
    	 		"\n" + 
    	 		"      .link-image img {\n" + 
    	 		"        max-width: 100% !important;\n" + 
    	 		"        max-height: 100% !important;\n" + 
    	 		"      }\n" + 
    	 		"    }\n" + 
    	 		"\n" + 
    	 		"    @media (max-width: 500px) {\n" + 
    	 		"      .column {\n" + 
    	 		"        display: block !important;\n" + 
    	 		"        width: 100% !important;\n" + 
    	 		"        padding-bottom: 8px !important;\n" + 
    	 		"      }\n" + 
    	 		"    }\n" + 
    	 		"\n" + 
    	 		"    @media only screen and (min-device-width: 768px) and (max-device-width: 1024px) {\n" + 
    	 		"      a[href^=\"tel\"], a[href^=\"sms\"] {\n" + 
    	 		"        text-decoration: none;\n" + 
    	 		"        color: blue;\n" + 
    	 		"        pointer-events: none;\n" + 
    	 		"        cursor: default;\n" + 
    	 		"      }\n" + 
    	 		"\n" + 
    	 		"      .mobile_link a[href^=\"tel\"], .mobile_link a[href^=\"sms\"] {\n" + 
    	 		"        text-decoration: default;\n" + 
    	 		"        color: orange !important;\n" + 
    	 		"        pointer-events: auto;\n" + 
    	 		"        cursor: default;\n" + 
    	 		"      }\n" + 
    	 		"    }\n" + 
    	 		"\n" + 
    	 		"  </style>\n" + 
    	 		"  <!--[if gte mso 9]>\n" + 
    	 		"    <style type=\"text/css\">\n" + 
    	 		"      #contentTable {\n" + 
    	 		"        width: 600px;\n" + 
    	 		"      }\n" + 
    	 		"    </style>\n" + 
    	 		"  <![endif]-->\n" + 
    	 		"</head>\n" + 
    	 		"<body style=\"width:100% !important;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;"
    	 		+ "margin-top:0;margin-bottom:0;margin-right:0;margin-left:0;padding-top:0;padding-bottom:0;"
    	 		+ "padding-right:0;padding-left:0;\">\n" + 
    	 		"<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" id=\"backgroundTable\" "
    	 		+ "style=\"margin:0; padding:0; width:100% !important; line-height: 100% !important; "
    	 		+ "border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt;background-color:"
    	 		+ " #F9FAFB;\" width=\"100%\">\n" + 
    	 		"  <tbody><tr>\n" + 
    	 		"    <td width=\"10\" valign=\"top\">&nbsp;</td>\n" + 
    	 		"    <td valign=\"top\" align=\"center\">\n" + 
    	 		"      <!--[if (gte mso 9)|(IE)]>\n" + 
    	 		"      <table width=\"600\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" "
    	 		+ "border=\"0\" style=\"background-color: #FFF; border-collapse:collapse;mso-table-"
    	 		+ "lspace:0pt;mso-table-rspace:0pt;\">\n" + 
    	 		"        <tr>\n" + 
    	 		"          <td>\n" + 
    	 		"      <![endif]-->\n" + 
    	 		"      <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\" "
    	 		+ "style=\"width: 100%; max-width: 600px; background-color: #FFF; border-collapse:"
    	 		+ "collapse;mso-table-lspace:0pt;mso-table-rspace:0pt;\" id=\"contentTable\">\n" + 
    	 		"        <tbody><tr>\n" + 
    	 		"          <td width=\"600\" valign=\"top\" align=\"center\" style=\"border-coll"
    	 		+ "apse:collapse;\">\n" + 
    	 		"            <table align=\"center\" border=\"0\" cellpadding=\"0\" "
    	 		+ "cellspacing=\"0\" style=\"background: #F9FAFB;\" width=\"100%\">\n" + 
    	 		"<tbody><tr>\n" + 
    	 		"<td align=\"center\" valign=\"top\">\n" + 
    	 		"<div style=\"font-family: &quot;lato&quot;, &quot;Helvetica Neue&quot;, "
    	 		+ "Helvetica, Arial, sans-serif; line-height: 28px;\">&nbsp;</div>\n" + 
    	 		"</td>\n" + 
    	 		"</tr>\n" + 
    	 		"</tbody></table>\n" + 
    	 		"<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" "
    	 		+ "style=\"border: 1px solid #E0E4E8;\" width=\"100%\">\n" + 
    	 		"<tbody><tr>\n" + 
    	 		"<td align=\"center\" style=\"padding: 56px 56px 28px 56px;\" valign=\"top\">\n" +
    	 		"</a></td>\n" + 
    	 		"</tr>\n" + 
    	 		"<tr>\n" + 
    	 		"<td align=\"center\" style=\"padding: 0 56px 28px 56px;\" valign=\"top\">\n" + 
    	 		"<div style=\"font-family: &quot;lato&quot;, &quot;Helvetica Neue&quot;, "
    	 		+ "Helvetica, Arial, sans-serif; line-height: 28px;font-size: 20px; color: "
    	 		+ "#333;\"><strong> "+ p.getName()  +" from " + p.getOwnerUrl() + "</strong> was "
    	 				+ "not updated and needs updating!</div>\n" + 
    	 		"</td>\n" + 
    	 		"</tr>\n" + 
    	 		"<tr>\n" + 
    	 		"<td align=\"center\" style=\"padding: 0 56px; padding-bottom: 56px;\" "
    	 		+ "valign=\"top\">\n" + 
    	 		"<div>\n" + 
    	 		"      <a href=\"#\" style=\"background-color:#E15718;border-radius:50px;"
    	 		+ "color:#ffffff;display:inline-block;font-family: 'lato', 'Helvetica Neue', "
    	 		+ "Helvetica, Arial, sans-serif;font-size:18px;font-weight: bold;line-height:"
    	 		+ "40px;text-align:center;text-decoration:none;width:270px;-webkit-text-size-"
    	 		+ "adjust:none;\" target=\"_blank\">Check your page</a>\n" + 
    	 		"</div>\n" + 
    	 		"\n" + 
    	 		"</td>\n" + 
    	 		"</tr>\n" + 
    	 		"\n" + 
    	 		"</tbody></table>\n" + 
    	 		"<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" "
    	 		+ "style=\"background: #F9FAFB;\" width=\"100%\">\n" + 
    	 		"<tbody>\n" + 
    	 		"\n" + 
    	 		"<tr>\n" + 
    	 		"<td align=\"center\" style=\"padding: 20px 56px 28px 56px;\" valign=\"middle\">\n" + 
    	 		"<span style=\"font-family: &quot;lato&quot;, &quot;Helvetica Neue&quot;, "
    	 		+ "Helvetica, Arial, sans-serif; line-height: 28px;font-size: 16px; color: "
    	 		+ "#A7ADB5; vertical-align: middle;\">Powered by</span>\n" + 
    	 		"&nbsp;\n" + 
    	 		"<a style=\"border: 0;\" href=\"https://localhost:8090/\"><img alt=\"NetNag\" "
    	 		+ "width=\"70\" height=\"28\" style=\"vertical-align: middle;\" "
    	 		+ "src=\"https://localhost:8090/img/logo.png\">\n" + 
    	 		"</a></td>\n" + 
    	 		"</tr>\n" + 
    	 		"</tbody></table>\n" + 
    	 		"\n" + 
    	 		"          </td>\n" + 
    	 		"        </tr>\n" + 
    	 		"      </tbody></table>\n" + 
    	 		"\n" + 
    	 		"    </td>\n" + 
    	 		"    <td width=\"10\" valign=\"top\">&nbsp;</td>\n" + 
    	 		"  </tr>\n" + 
    	 		"</tbody></table> \n" + 
    	 		"\n" + 
    	 		"\n" + 
    	 		"\n" + 
    	 		"</body>\n" + 
    	 		"  \n" + 
    	 		"  \n" + 
    	 		"\n" + 
    	 		"</body>\n" + 
    	 		"\n" + 
    	 		"</html>\n" + 
    	 		"";
    	 
    	 
    	 
    	 
    	 
    	 
    	 if((currentTime - lastUpdatedLong) > p.getAlertAfter() &&  !p.getWarning()) {
    		 
    		 emailService.sendEmail(p.getEmail(), mail, "Your website needs updating!");
    		  p.setWarning(true);
    		  pageRepo.save(p);
    	 
     }
    	 if((currentTime - lastUpdatedLong - 100) > p.getAlertAfter() && p.getWarning()) {
    		 emailService.sendEmail(p.getEmail(), mail, "Your website needs updating! - Reminder!");
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
