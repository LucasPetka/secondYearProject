package group9rcraggs.application;


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
		// create demo user
		User adminUser = new User();
		adminUser.setLogin("admin");
		adminUser.setPassword("password");
		userRepo.save(adminUser);
		// create demo website for demo user
		Website w = new Website();
		w.setName("Demo Website Genesis");
		w.setUrl("https://genesistechsys.com");
		adminUser.addWebsite(w);
		websiteRepo.save(w);
		Page p = new Page("https", "www.genesistechsys.com", w);
		p.setTracking(true);
		pageRepo.save(p);
	}
}