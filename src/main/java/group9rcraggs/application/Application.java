package group9rcraggs.application;




import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



import group9rcraggs.application.domain.Page;
import group9rcraggs.application.domain.User;
import group9rcraggs.application.domain.Website;




@SpringBootApplication
public class Application implements CommandLineRunner  { 

	
	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        
    }
	@Autowired 
	private group9rcraggs.application.repository.UserRepository userRepo;
	

	
	@Autowired 
	private group9rcraggs.application.repository.WebsiteRepository websiteRepo;
	
	@Autowired 
	private group9rcraggs.application.repository.PageRepository pageRepo;
	
	@Override
	public void run(String... args) throws Exception {
		Tracking track = new Tracking();
		// create demo user

		User adminUser = new User();
		adminUser.setLogin("admin");
		adminUser.setPassword("password");
		userRepo.save(adminUser);


		// create demo website for demo user
		Website w = new Website();
		w.setName("Demo Website idomu");
		w.setUrl("https://idomu.ax.lt");
		adminUser.addWebsite(w);
		websiteRepo.save(w);
		Page p = new Page("https", "idomu.ax.lt", w);
		p.setTracking(true);
		pageRepo.save(p);
		//get page's ignored lines
		Page ww = new Page();
		ww = pageRepo.findById(3);
		String ignore="";
		

		    while(true) {
	            Thread.sleep(10000);    
	   //         File file = new File("idomu.ax.lt_1");
	    //        if(file.exists()) {
	     //           file.delete();
	     //       }
	            track.sourceCodeToFile("https://idomu.ax.lt", "idomu.ax.lt_1");
	            
	            ww = pageRepo.findById(3);
	            ignore = ww.getLinesIgnored();

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