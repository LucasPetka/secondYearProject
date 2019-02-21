package group9rcraggs.application.controller;


import java.security.Principal;
import java.util.ArrayList;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;


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
	@RequestMapping("/profile")
	public String profile_page(Model model, Principal principal) {
		//model.addAttribute("logfirstName", userRepo.findByLogin(principal.getName()).getFirstName());
		model.addAttribute("loglastName", userRepo.findByLogin(principal.getName()).getLastName());
		model.addAttribute("loglogin", userRepo.findByLogin(principal.getName()).getLogin());
		model.addAttribute("logfirstName", userRepo.findByLogin(principal.getName()).getFirstName());
    	
		
		User user = userRepo.findByLogin(principal.getName());
    	List<Website> websites = new ArrayList<>();
    	for (Website w : websiteRepo.findAll()) {
    		if(w.getOwner().equals(user)) {
			websites.add(w);
    		}
		}
    	
		if (websites.isEmpty()) {
			return "EmptyWebList";
		} else {
			model.addAttribute("websites", websites);
		}
		return "Profile";
    }
  
    
}
