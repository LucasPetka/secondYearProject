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
import org.springframework.web.bind.annotation.RequestParam;

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

	///* Returns view CreateWebTrack *///
    @RequestMapping(value = "addWebsite", method = RequestMethod.GET)
    public String create(Model model) {
		model.addAttribute("website", new Website());
		return "CreateWebTrack";

}
  ///* Adds website to database when add is clicked and calls addWebsite *///
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
    
    ///* When cancel is clicked calls addWebsite - nothing added/deleted to database *///
    @RequestMapping(value = "addWebsite", params = "cancel", method = RequestMethod.POST)
	public String cancelNewWebsite() {
		return "redirect:/websiteList";
	}
    
  ///* Returns list of websites *///
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

    
    
  
  ///* Deletes website from database*///
    @RequestMapping(value = "deleteWebsite", params = "id", method = RequestMethod.GET)
	public String deleteWebsite(@RequestParam("id") int id, Principal principal) {
		Website w = websiteRepo.findById(id);	
		User user = userRepo.findById(1);
		user.deleteWebsite(id);
//		if (w != null) {
//			// deleting the website will fail a foreign key constraint
//			User user = userRepo.findByLogin(principal.getName());
//			for (Website w2 : user.getWebsites()) {
//				if(w2.getId() == id) {
//					user.deleteWebsite(id);
//				}
//			}
//			// delete website as orphan
			userRepo.save(user);
		
		return "redirect:/websiteList";
}
    
    
}