package group9rcraggs.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import group9rcraggs.application.domain.AlertAfter;
import group9rcraggs.application.domain.Email;
import group9rcraggs.application.domain.Plan;
import group9rcraggs.application.domain.Role;
import group9rcraggs.application.domain.User;
import group9rcraggs.application.repository.AlertAfterRepository;
import group9rcraggs.application.repository.PlanRepository;
import group9rcraggs.application.repository.RoleRepository;
import group9rcraggs.application.repository.UserRepository;




@SpringBootApplication
@EnableScheduling
public class Application implements CommandLineRunner  { 

	
	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        
    }
	@Autowired 
	UserRepository userRepo;
	
	@Autowired 
	RoleRepository roleRepo;
	
	@Autowired 
	PlanRepository planRepo;
	
	@Autowired 
	AlertAfterRepository alertAfterRepo;
	
	@Override
	public void run(String... args) throws Exception {
		
		///* Set alert after times *///
		//weekly alerts
		AlertAfter alert1week = new AlertAfter(new Long(10080), "1 Week");
		AlertAfter alert2week = new AlertAfter(new Long(20160), "2 Weeks");
		AlertAfter alert3week = new AlertAfter(new Long(30240), "3 Weeks");
		
		//monthly alerts
		AlertAfter month1alert = new AlertAfter(new Long(40320*1), "1 Month");
		AlertAfter month2alert = new AlertAfter(new Long(40320*2), "2 Months");
		AlertAfter month3alert = new AlertAfter(new Long(40320*3), "3 Months");
		AlertAfter month4alert = new AlertAfter(new Long(40320*4), "4 Months");
		AlertAfter month5alert = new AlertAfter(new Long(40320*5), "5 Months");
		AlertAfter month6alert = new AlertAfter(new Long(40320*6), "6 Months");
		AlertAfter month7alert = new AlertAfter(new Long(40320*7), "7 Months");
		AlertAfter month8alert = new AlertAfter(new Long(40320*8), "8 Months");
		AlertAfter month9alert = new AlertAfter(new Long(40320*9), "9 Months");
		AlertAfter month10alert = new AlertAfter(new Long(40320*10), "10 Months");
		AlertAfter month11alert = new AlertAfter(new Long(40320*11), "11 Months");
		AlertAfter month12alert = new AlertAfter(new Long(40320*12), "12 Months");
		
		alertAfterRepo.save(alert1week);
		alertAfterRepo.save(alert2week);
		alertAfterRepo.save(alert3week);
		alertAfterRepo.save(month1alert);
		alertAfterRepo.save(month2alert);
		alertAfterRepo.save(month3alert);
		alertAfterRepo.save(month4alert);
		alertAfterRepo.save(month5alert);
		alertAfterRepo.save(month6alert);
		alertAfterRepo.save(month7alert);
		alertAfterRepo.save(month8alert);
		alertAfterRepo.save(month9alert);
		alertAfterRepo.save(month10alert);
		alertAfterRepo.save(month11alert);
		alertAfterRepo.save(month12alert);
		
		
		///* Create plans for users*///
		
		Plan freeplan = new Plan();
		Plan plan1 = new Plan();
		Plan plan2 = new Plan();
		Plan plan3 = new Plan();
		
		freeplan.setId(0);
		freeplan.setTier("Free");
		freeplan.setFrequency(1440);
		freeplan.setNumPages(1);
		plan1.setId(1);
		plan1.setNumPages(20);
		plan1.setPrice(9.99);
		plan1.setTier("Standard");
		plan1.setFrequency(1440);
		plan2.setId(2);
		plan2.setPrice(19.99);
		plan2.setNumPages(50);
		plan2.setFrequency(360);
		plan2.setTier("Pro");
		plan3.setId(3);
		plan3.setPrice(29.99);
		plan3.setFrequency(60);
		plan3.setNumPages(100);
		plan3.setTier("Enterprise");
		
		//weekly plans only for enterprise
		plan3.addAlertAfter(alert1week);
		plan3.addAlertAfter(alert2week);
		plan3.addAlertAfter(alert3week);
		
		//monthly plans
		
		//enterprise
		plan3.addAlertAfter(month1alert);
		plan3.addAlertAfter(month2alert);
		plan3.addAlertAfter(month3alert);
		plan3.addAlertAfter(month4alert);
		plan3.addAlertAfter(month5alert);
		plan3.addAlertAfter(month6alert);
		plan3.addAlertAfter(month7alert);
		plan3.addAlertAfter(month8alert);
		plan3.addAlertAfter(month9alert);
		plan3.addAlertAfter(month10alert);
		plan3.addAlertAfter(month11alert);
		plan3.addAlertAfter(month12alert);
		
		//pro
		plan2.addAlertAfter(month1alert);
		plan2.addAlertAfter(month2alert);
		plan2.addAlertAfter(month3alert);
		plan2.addAlertAfter(month4alert);
		plan2.addAlertAfter(month5alert);
		plan2.addAlertAfter(month6alert);
		plan2.addAlertAfter(month7alert);
		plan2.addAlertAfter(month8alert);
		plan2.addAlertAfter(month9alert);
		plan2.addAlertAfter(month10alert);
		plan2.addAlertAfter(month11alert);
		plan2.addAlertAfter(month12alert);
		
		//Standard
		plan1.addAlertAfter(month3alert);
		plan1.addAlertAfter(month6alert);
		plan1.addAlertAfter(month9alert);
		plan1.addAlertAfter(month12alert);
		
		//free plan
		freeplan.addAlertAfter(month12alert);
		
		//save plans
		planRepo.save(freeplan);
		planRepo.save(plan1);
		planRepo.save(plan2);
		planRepo.save(plan3);
		

		
		
		//Create admin and user roles
		
		Role adminr = new Role();
		adminr.setId(0);
		adminr.setRole("ADMIN");
		roleRepo.save(adminr);
		
		Role userr = new Role();
		userr.setId(1);
		userr.setRole("USER");
		roleRepo.save(userr);
		
		//end of roles
		
		///* Create demo user for testing*///
		BCryptPasswordEncoder pe = new  BCryptPasswordEncoder();
		User adminUser = new User();
		adminUser.setRole(adminr);
		adminUser.setfirstName("John");
		adminUser.setLastName("Balden");
		adminUser.setLogin("netnag.t@gmail.com");
		adminUser.setPassword(pe.encode("password"));
		adminUser.setEnabled(true);
		adminUser.setPlan(freeplan);
		adminUser.getEmails().add(new Email(adminUser.getLogin(), adminUser));
		///* Saves to database *///
		userRepo.save(adminUser);
		
		User adminUse = new User();
		adminUse.setRole(adminr);
		adminUse.setLogin("admin2@gmail.com");
		adminUse.setPassword(pe.encode("password"));
		adminUse.setEnabled(true);
		adminUse.setPlan(freeplan);
		adminUse.getEmails().add(new Email(adminUse.getLogin(), adminUse));

		///* Saves to database *///
		userRepo.save(adminUse);


		
		
	

		
	}
}
