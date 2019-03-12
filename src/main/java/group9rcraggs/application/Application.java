package group9rcraggs.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import group9rcraggs.application.domain.Plan;
import group9rcraggs.application.domain.Role;
import group9rcraggs.application.domain.User;
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
	
	@Override
	public void run(String... args) throws Exception {
		
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
		///* Saves to database *///
		userRepo.save(adminUser);
		
		User adminUse = new User();
		adminUse.setRole(adminr);
		adminUse.setLogin("admin2@gmail.com");
		adminUse.setPassword(pe.encode("password"));
		///* Saves to database *///
		userRepo.save(adminUse);

		///* Create plans for websites*///
		
		Plan planbasic = new Plan();
		Plan plan1 = new Plan();
		Plan plan2 = new Plan();
		Plan plan3 = new Plan();
		
		planbasic.setId(0);
		plan1.setId(1);
		plan2.setId(2);
		plan3.setId(3);
		
		planbasic.setName("Basic");
		plan1.setName("Tier 1");
		plan2.setName("Tier 2");
		plan3.setName("Tier 3");
		
		planbasic.setNumPages(1);
		plan1.setNumPages(20);
		plan2.setNumPages(50);
		plan3.setNumPages(100);
		
		planbasic.setPrice(0);
		plan1.setPrice(9.99);
		plan2.setPrice(19.99);
		plan3.setPrice(29.99);
		
		planRepo.save(planbasic);
		planRepo.save(plan1);
		planRepo.save(plan2);
		planRepo.save(plan3);
		
		
		
	

		
	}
}
