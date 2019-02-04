package group9rcraggs.application;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import group9rcraggs.application.domain.User;



@SpringBootApplication
public class Application implements CommandLineRunner  { 

	
	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        
    }
	@Autowired 
	private group9rcraggs.application.repository.UserRepository userRepo;
	
	@Override
	public void run(String... args) throws Exception {
		User adminUser = new User();
		adminUser.setLogin("admin");
		adminUser.setPassword("password");
		userRepo.save(adminUser);
		
	}
}