package group9rcraggs.application.controller;


import java.security.Principal;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import group9rcraggs.application.domain.Email;
import group9rcraggs.application.domain.User;
import group9rcraggs.application.repository.EmailRepository;
import group9rcraggs.application.repository.UserRepository;
import group9rcraggs.application.repository.WebsiteRepository;


@Controller
@RequestMapping("/")
public class ProfileController {
	
	@Autowired
	WebsiteRepository websiteRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	EmailRepository emailRepo;


///* Returns view Index *///
	@RequestMapping("profile")
	public String profile_page(Model model, Principal principal) {
		User user = userRepo.findByLogin(principal.getName());
		model.addAttribute("loglastName", user.getLastName());
		model.addAttribute("loglogin", user.getLogin());
		model.addAttribute("logfirstName", user.getFirstName());
    	model.addAttribute("websites", user.getWebsites());
    	
    	//membership details model
    	model.addAttribute("membershiptype", user.getPlan().getTier());
    	if(!user.getPlan().getTier().equals("Free")) {
    	model.addAttribute("paidMembership", true);
    	model.addAttribute("membershipValidUntil", user.getPlanValidUntil());
    	}
		return "Profile";
    }
	
	@RequestMapping(value = "updateUser", method = RequestMethod.POST)
	public String profile_update(@ModelAttribute("user") User user, Model model, Principal principal) {
		
			User userToDb = userRepo.findByLogin(principal.getName());
			userToDb.setfirstName(user.getFirstName());
			userToDb.setLastName(user.getLastName());
			userRepo.save(userToDb);
			
		return "redirect:/profile";
    }
	
	
	
	@RequestMapping(value = "changed", method = RequestMethod.POST)
	public String changed(@Valid @ModelAttribute("user") User user, 
			Model model, Principal principal, 
			@RequestParam("password") String newPassword,
			@RequestParam("old_pass") String oldPassword) {
		User user2 = userRepo.findByLogin(principal.getName());
		model.addAttribute("loglastName", user2.getLastName());
		model.addAttribute("loglogin", user2.getLogin());
		model.addAttribute("logfirstName", user2.getFirstName());

		User userr = userRepo.findByLogin(principal.getName());
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		String existingPassword = oldPassword;
		String dbPassword       = userr.getPassword();

		if (passwordEncoder.matches(existingPassword, dbPassword)) {
			userr.setPassword(passwordEncoder.encode(newPassword));
			userRepo.save(userr);
			
		}
		else {
			model.addAttribute("error", true);
			return "PassChange";
		}

		return "redirect:/changePassword";
    }
	

	@RequestMapping("changePassword")
	public String changedPass(Model model, Principal principal) {
		model.addAttribute("logfirstName", userRepo.findByLogin(principal.getName()).getFirstName());
    	model.addAttribute("websites", userRepo.findByLogin(principal.getName()).getWebsites());
		
		return "PassChange";
    }
	
	
	@RequestMapping(value = "addEmail", method = RequestMethod.POST)
	public String addEmail(@ModelAttribute("email") Email email, Model model, Principal principal) {
		
		User user = userRepo.findByLogin(principal.getName());
		email.setOwner(user);
		user.addEmail(email);
		emailRepo.save(email);
		
		
		return "redirect:/profile";
	}
	





    
    
}