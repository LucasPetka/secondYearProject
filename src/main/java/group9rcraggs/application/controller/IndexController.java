package group9rcraggs.application.controller;


import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import group9rcraggs.application.domain.User;
import group9rcraggs.application.domain.Website;
import group9rcraggs.application.repository.UserRepository;
import group9rcraggs.application.repository.WebsiteRepository;


@Controller
@RequestMapping("/")
public class IndexController {
	
	@Autowired
	WebsiteRepository websiteRepo;
	
	@Autowired
	UserRepository userRepo;

	
    @RequestMapping(value = "addWebsite", method = RequestMethod.GET)
    public String create(Model model) {
		model.addAttribute("website", new Website());
		return "CreateWebTrack";

}
    
    @RequestMapping(value = "addWebsite", params = "add", method = RequestMethod.POST)
	public String addNewWebsite(@Valid @ModelAttribute("website") Website w, BindingResult result, Model model, Principal principal) {
		
		if (result.hasErrors()) {
			return "index";
		} else {
			
			User user = userRepo.findById(1);
					w.setOwner(user);
					user.addWebsite(w);
			websiteRepo.save(w);
			
			return "redirect:/websiteList";
		}
	}
    
    
    @RequestMapping(value = "websiteList")
    public String testing(Model model, Principal principal) {
    	
    	User user = userRepo.findById(1);
    	
    	List<Website> websites = new ArrayList<>();
    	for (Website w : websiteRepo.findAll()) {
			websites.add(w);
		}
    	
		if (websites.isEmpty()) {
			return "EmptyWebList";
		} else {
			model.addAttribute("websites", websites);
		}
		return "WebList";
    }
    
    @RequestMapping(value = "addWebsite", params = "cancel", method = RequestMethod.POST)
	public String cancelNewWebsite() {
		return "redirect:/websiteList";
	}
    
}