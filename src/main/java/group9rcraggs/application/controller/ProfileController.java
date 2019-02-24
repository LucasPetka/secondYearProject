package group9rcraggs.application.controller;


import java.security.Principal;
import java.util.ArrayList;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.ModelAttribute;
=======

>>>>>>> ddff6e937bded3e6fcc0f1cd86bc69436b9cd2cf
import org.springframework.web.bind.annotation.RequestMapping;

<<<<<<< HEAD
=======

>>>>>>> ddff6e937bded3e6fcc0f1cd86bc69436b9cd2cf
import group9rcraggs.application.domain.User;
import group9rcraggs.application.domain.Website;
import group9rcraggs.application.repository.UserRepository;
import group9rcraggs.application.repository.WebsiteRepository;


@Controller
@RequestMapping("/")
public class ProfileController {
	
	@Autowired
	WebsiteRepository websiteRepo;
	
	@Autowired
	UserRepository userRepo;


///* Returns view Index *///
	@RequestMapping("profile")
	public String profile_page(Model model, Principal principal) {
		//model.addAttribute("logfirstName", userRepo.findByLogin(principal.getName()).getFirstName());
		model.addAttribute("loglastName", userRepo.findByLogin(principal.getName()).getLastName());
		model.addAttribute("loglogin", userRepo.findByLogin(principal.getName()).getLogin());
		model.addAttribute("logfirstName", userRepo.findByLogin(principal.getName()).getFirstName());
    	
		
//		User user = userRepo.findByLogin(principal.getName());
//    	List<Website> websites = new ArrayList<>();
//    	for (Website w : websiteRepo.findAll()) {
//    		if(w.getOwner().equals(user)) {
//			websites.add(w);
//    		}
//		}
//    	
//		if (websites.isEmpty()) {
//			return "EmptyWebList";
//		} else {
//			model.addAttribute("websites", websites);
//		}
		return "Profile";
    }
<<<<<<< HEAD
	
	@RequestMapping(value = "updateUser", method = RequestMethod.POST)
	public String profile_update(@ModelAttribute("user") User user, Model model, Principal principal) {
		
			User userToDb = userRepo.findByLogin(principal.getName());
			userToDb.setfirstName(user.getFirstName());
			userToDb.setLastName(user.getLastName());
			userRepo.save(userToDb);
			
		//	model.addAttribute("loglastName", userRepo.findByLogin(principal.getName()).getLastName());
		//	model.addAttribute("loglogin", userRepo.findByLogin(principal.getName()).getLogin());
		//	model.addAttribute("logfirstName", userRepo.findByLogin(principal.getName()).getFirstName());

		return "redirect:/profile";
    }
	
	
	
	@RequestMapping(value = "changed", method = RequestMethod.POST)
	public String changed(@Valid @ModelAttribute("user") User user, 
			Model model, Principal principal, 
			@RequestParam("new_pass") String newPassword,
			@RequestParam("old_pass") String oldPassword) {
		
		model.addAttribute("loglastName", userRepo.findByLogin(principal.getName()).getLastName());
		model.addAttribute("loglogin", userRepo.findByLogin(principal.getName()).getLogin());
		model.addAttribute("logfirstName", userRepo.findByLogin(principal.getName()).getFirstName());

		user = userRepo.findByLogin(principal.getName());
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		String existingPassword = oldPassword;
		String dbPassword       = user.getPassword();

		if (passwordEncoder.matches(existingPassword, dbPassword)) {
			user.setPassword(passwordEncoder.encode(newPassword));
			userRepo.save(user);
			
		} else {
		    System.out.println("");
		}
		
		
		
		return "redirect:/changePassword";
    }
	
	@RequestMapping("/changePassword")
	public String changedPass(Model model, Principal principal) {
		model.addAttribute("logfirstName", userRepo.findByLogin(principal.getName()).getFirstName());
		
		return "PassChange";
    }





    
=======
  
>>>>>>> ddff6e937bded3e6fcc0f1cd86bc69436b9cd2cf
    
}