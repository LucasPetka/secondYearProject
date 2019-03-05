package group9rcraggs.application.controller;


import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import group9rcraggs.application.Tracking;
import group9rcraggs.application.domain.Page;
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


///* Returns view Index *///
	@RequestMapping("/")
	public String main_page() {
		return "index";
	}


	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(new WebsiteValidator());
	}
	

//	///* Returns view CreateWebTrack *///
//    @RequestMapping(value = "addWebsite", method = RequestMethod.GET)
//    public String create(Model model) {
//		model.addAttribute("website", new Website());
//		return "CreateWebTrack";
//
//}
    
  ///* Adds website to database when add is clicked and calls addWebsite *///
    @RequestMapping(value = "addWebsite", params = "add", method = RequestMethod.POST)
	public String addNewWebsite(@Valid @ModelAttribute("website") Website w, BindingResult result, Model model, Principal principal) {
    	model.addAttribute("logfirstName", userRepo.findByLogin(principal.getName()).getFirstName());
		if (result.hasErrors()) {
			model.addAttribute("badlink", true);
	    	User user = userRepo.findByLogin(principal.getName());
	    	
	    	List<Website> websites = new ArrayList<>();
	    	for (Website ww : websiteRepo.findAll()) {
	    		if(ww.getOwner().equals(user)) {
				websites.add(ww);
	    		}
			}
	    	
			if (websites.isEmpty()) {
				return "EmptyWebList";
			} else {
				model.addAttribute("websites", websites);
			}
			return "WebList";
		} else {
			
			User user = userRepo.findByLogin(principal.getName());
			
			//Removes extra '/' at the end of URL and adds one 
				w.setUrl(removeSlashes(w.getUrl()) + '/');
			//Gets auto added page and sets url to website url (Home page)
			for(Page p : w.getPages()) {
				Tracking track = new Tracking();
				p.setUrl(w.getUrl());
				p.setFileName(track.linkToFileFormat(p.getUrl())+"_0");
				p.setLinesIgnored("[]");
				p.setLastUpdated("Not Yet Tracked/Changed");
				p.setTracking(true);
				p.setFrequency("0");
				break;
			}
					w.setOwner(user);
					w.setTracking(true);
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
    public String websiteListPage(Model model, Principal principal) {
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
		return "WebList";
    }

    
    
  
  ///* Deletes website from database*///
    @RequestMapping(value = "deleteWebsite", params = "id", method = RequestMethod.GET)
	public String deleteWebsite(@RequestParam("id") int id, Principal principal) {
		Website w = websiteRepo.findById(id);	
		websiteRepo.delete(w);
		
		return "redirect:/websiteList";
}
    
    //Passes current website into model and will display the form to update website
    @RequestMapping(value = "editWebsite", params = "id")
 public String editWebsite(@ModelAttribute("website") Website  website , @RequestParam("id") int id, Principal principal, 
		 BindingResult result, Model model) {
    	
    	User user = userRepo.findByLogin(principal.getName());

    	website = websiteRepo.findById(id);
    	
    	model.addAttribute("website", website);

    	return "CreateWebTrack";
    }
    
    
    
    
    //temp until modal complete
    @RequestMapping(value = "editWebsiteClicked", method = RequestMethod.POST)
    public String editWebsiteClicked(@ModelAttribute("website") Website w, Principal principal, BindingResult result, Model model) {
    	
    	//Errors need fixing
    	if (result.hasErrors()) {
    		return "CreateWebTrack";
    	}
    	else {
    	User user = userRepo.findByLogin(principal.getName());
    	
		w.setOwner(user);
		w.setTracking(true);
    	websiteRepo.save(w);

    	return "redirect:/websiteList"; 
    }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    //Removes any excess '/' from end of URL
private String removeSlashes(String s) {
        
    	for(int i=s.length(); i >=  0; i--) {
    		
    		if(s.endsWith("/")) {
    			s = s.substring(0, s.length()-1);
    		}
    	}
    	return s;
    	
    }
    
    
}
