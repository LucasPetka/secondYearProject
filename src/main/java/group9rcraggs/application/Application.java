package group9rcraggs.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import group9rcraggs.application.domain.Role;
import group9rcraggs.application.domain.User;




@SpringBootApplication
@EnableScheduling
public class Application implements CommandLineRunner  { 

	
	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        
    }
	@Autowired 
	private group9rcraggs.application.repository.UserRepository userRepo;
	
	@Autowired 
	private group9rcraggs.application.repository.RoleRepository roleRepo;
	
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
		adminUser.setLogin("admin@gmail.com");
		adminUser.setPassword(pe.encode("password"));
		///* Saves to database *///
		userRepo.save(adminUser);
		
		User adminUse = new User();
		adminUse.setRole(adminr);
		adminUse.setLogin("admin2@gmail.com");
		adminUse.setPassword(pe.encode("password"));
		///* Saves to database *///
		userRepo.save(adminUse);

		///* Create demo website for user for testing*///
		

	

		
	}
}
