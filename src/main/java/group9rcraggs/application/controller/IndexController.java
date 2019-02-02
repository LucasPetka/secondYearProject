package group9rcraggs.application.controller;


import java.security.Principal;

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
import group9rcraggs.application.repository.WebsiteRepository;



@Controller
@RequestMapping("/")
public class IndexController {
	
	
	@Autowired
	WebsiteRepository websiteRepo;

	
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
			User user = new User();
			user.setLogin("");
			user.setPassword("");
			Website w1 = new Website();
			w1.setName("test");
			w1.setOwner(user);
			websiteRepo.save(w1);
			
			return "redirect:/websiteList";
		}
	}
    
    @RequestMapping(value = "addWebsite", params = "cancel", method = RequestMethod.POST)
	public String cancelNewWebsite() {
		return "redirect:/websiteList";
	}
    
    
    @RequestMapping(value = "websiteList")
    public String test() {
    	return "WebList";
    }
}