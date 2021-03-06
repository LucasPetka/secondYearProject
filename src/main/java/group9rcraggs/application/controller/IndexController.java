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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import group9rcraggs.application.domain.Email;
import group9rcraggs.application.domain.Page;
import group9rcraggs.application.domain.User;
import group9rcraggs.application.domain.Website;
import group9rcraggs.application.repository.EmailRepository;
import group9rcraggs.application.repository.PageRepository;
import group9rcraggs.application.repository.PlanRepository;
import group9rcraggs.application.repository.UserRepository;
import group9rcraggs.application.repository.WebsiteRepository;


@Controller
@RequestMapping("/")
public class IndexController {
	
	@Autowired
	WebsiteRepository websiteRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	PageRepository pageRepo;
	
	@Autowired
	PlanRepository planRepo;
	
	@Autowired
	EmailRepository emailRepo;


///* Returns view Index *///
	@RequestMapping("/")
	public String main_page() {
		return "index";
	}


	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(new WebsiteValidator());
	}
    
  ///* Adds website to database when add is clicked and calls addWebsite *///
    @RequestMapping(value = "addWebsite", params = "add", method = RequestMethod.POST)
	public String addNewWebsite(@Valid @ModelAttribute("website") Website w, BindingResult result, Model model, Principal principal
			, RedirectAttributes redirectAttrs) {
    	model.addAttribute("logfirstName", userRepo.findByLogin(principal.getName()).getFirstName());
    	
    	User user = userRepo.findByLogin(principal.getName());
    	List<Website> websites = user.getWebsites();
    	//Checks if number of pages has been equaled or exceeded compared to  their plan
    	int pages = 0;
    	for(Website ww : user.getWebsites()) {
    		pages += ww.getPages().size();
    	}
    	
    	
    	if(pages >= user.getPlan().getNumPages()) {
			redirectAttrs.addFlashAttribute("exceedPageLimit", true);
    		return "redirect:/websiteList";
    	}
    	   	
		if (result.hasErrors()) {
			redirectAttrs.addFlashAttribute("badlink", true);
	    	
	   
			if (!websites.isEmpty()) {
				model.addAttribute("websites", websites);
			}
			return "redirect:/websiteList";
		} else {
			
			
			//Removes extra '/' at the end of URL and adds one 
				w.setUrl(removeSlashes(w.getUrl()) + '/');
				
				//checks if website already exists in users website list
				for(Website ww : user.getWebsites()) {
					if(w.getUrl().equals(ww.getUrl())) {
						redirectAttrs.addFlashAttribute("duplicatewebsite", true);
						redirectAttrs.addFlashAttribute("websites", websites);
						return "redirect:/websiteList";
		    		}
		    	}
						
			//Gets auto added page and sets url to website url (Home page)
		/*	for(Page p : w.getPages()) {
				Tracking track = new Tracking();
				p.setUrl(w.getUrl());
				p.setFileName(track.linkToFileFormat(p.getUrl())+"_0");
				p.setLinesIgnored("[]");
				p.setLastUpdated("Not Yet Tracked/Changed");
				p.setTracking(true);
				p.setFrequency(user.getPlan().getFrequency());
				p.setEmail(user.getLogin());
				p.setAlertAfter(3);
				p.setWarning(false);
				break;
			}*/
					w.setOwner(user);			
					w.setTracking(true);
					user.addWebsite(w);
			websiteRepo.save(w);
			
			return "redirect:/websiteList";
		}
	}
    
    
  ///* Returns list of websites *///
    @RequestMapping(value = "websiteList")
    public String websiteListPage(Model model, Principal principal) {
    	model.addAttribute("logfirstName", userRepo.findByLogin(principal.getName()).getFirstName());
    	model.addAttribute("websites", userRepo.findByLogin(principal.getName()).getWebsites());
    	User user = userRepo.findByLogin(principal.getName());
    	
    	//Adds emails for website addition
    	
    	model.addAttribute("emails", user.getEmails());
    	
    	//Adds email list as model attribute for editing website 
    	List<Email> emails= user.getEmails();
    	List<String> emailName = new ArrayList<String>();
    	//Converts email object list into list of email strings
    	for(Email email : emails) {
    		emailName.add(email.getAddress());
    	}
    	Website w3 = new Website();
    	model.addAttribute("emails", emailName);
    	model.addAttribute("website", w3);
    	
    	List<Website> websites = new ArrayList<>();
    	
    	
    	
    	for (Website w : websiteRepo.findAll()) {
    		if(w.getOwner().equals(user)) {
			websites.add(w);
    		}
		}
    	
    	List<Page> pages = new ArrayList<>();
    	
    	for (Page p : pageRepo.findAll()) {
    		if (p.getOwner().getOwner().getId() == user.getId()){
			pages.add(p);
    		}
    	}
    	
    
    	
    	
    	int count_page = pages.size();
    	model.addAttribute("page_count", count_page);
    	
    	int count_web = websites.size();
    	model.addAttribute("web_count", count_web);
    	
    	model.addAttribute("page_limit", user.getPlan().getNumPages());
    	
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
    

    @RequestMapping(value = "ajaxSendEditWebsite", method = RequestMethod.GET)
    public @ResponseBody
    String ajaxEditWebsite(@RequestParam("page_ID") int id, @RequestParam("name") String name, @RequestParam("email") String email) {
    	Website web = websiteRepo.findById(id);
    	web.setName(name);
    	web.setEmail(email);
    	websiteRepo.save(web);
        return null;
    }
    
    
    //temp until modal complete
    @RequestMapping(value = "editWebsite", method = RequestMethod.POST)
    public String editWebsiteClicked(@ModelAttribute("website") Website w, Principal principal, 
    		BindingResult result, Model model) {
    	
    	//todo Errors need fixing

    	websiteRepo.save(w);

    	return "redirect:/"; 
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
