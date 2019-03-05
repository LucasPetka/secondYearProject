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
import group9rcraggs.application.repository.PageRepository;
import group9rcraggs.application.repository.UserRepository;
import group9rcraggs.application.repository.WebsiteRepository;


@Controller
@RequestMapping("/")
public class PageController {
	
	@Autowired
	WebsiteRepository webRepo;
	
	@Autowired
	PageRepository pageRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(new PageValidator());
	}
	

//	///* Returns view createPageTrack *///
//	 @RequestMapping(value = "addPage", method = RequestMethod.GET)
//	public String createPage(@RequestParam(name="id") int id, Model model) {
//		 Website website = webRepo.findById(id);
//		 model.addAttribute("websiteUrl", website.getUrl());
//		 model.addAttribute("websiteId", id);
//			model.addAttribute("page", new Page());
//			return "createPageTrack";
//
//	}
	 
	  ///* Adds page to database when add is clicked and calls addPage *///
	    @RequestMapping(value = "addPage", params = "add", method = RequestMethod.POST)
		public String addNewPage(@RequestParam(name="id") int id, @Valid @ModelAttribute("page") Page p, 
				BindingResult result, Model model, Principal principal) {
	    	model.addAttribute("logfirstName", userRepo.findByLogin(principal.getName()).getFirstName());
	    	Website website = webRepo.findById(id);

	    	 //Removes any excess '/' from end of URL
	    	p.setUrl(removeSlashes(p.getUrl()));
	    	
			if (result.hasErrors()) {
				model.addAttribute("websiteId", id);
				model.addAttribute("websiteUrl", webRepo.findById(id).getUrl());
				model.addAttribute("badlink", true);
				
				if(website.getPages().isEmpty()) {
					return "EmptyPageList";
				} else {
					
					model.addAttribute("pages", website.getPages());
					return "PageList";
				}
			} else {
				Tracking track = new Tracking();
				p.setOwner(website);
				
				//Sets url to parent URL + page URL 
				p.setUrl(p.getUrlWithParent());
				
				p.setFileName(track.linkToFileFormat(p.getUrl())+"_0");
				p.setLinesIgnored("[]");
				p.setLastUpdated("Checking/Not Yet Tracked");
				p.setTracking(true);
				pageRepo.save(p);
				
				return "redirect:/pageList?id="+website.getId();
			}
		}

	    
		
	    ///* When cancel is clicked calls addPage - nothing added/deleted to database *///
	    @RequestMapping(value = "addPage", params = "cancel", method = RequestMethod.POST)
		public String cancelNewPage(@RequestParam(name="id") int id) {
	    	Website website = webRepo.findById(id);
			return "redirect:/pageList?id="+website.getId();
		}
	    
	    @RequestMapping(value = "removePage")
		public String RemovePage(@RequestParam(name="id") int id, @RequestParam(name="websiteid") int websiteid) {
			Page w = pageRepo.findById(id);	
			pageRepo.delete(w);
			return "redirect:/pageList?id="+webRepo.findById(websiteid).getId()+"";
		}
	    
	    
	 
	    ///* Returns list of pages *///
    @RequestMapping(value = "pageList")
    public String GetPageList(@RequestParam("id") int id, Model model, Principal principal) {
    	model.addAttribute("logfirstName", userRepo.findByLogin(principal.getName()).getFirstName());
    	model.addAttribute("websiteId", id);
    	model.addAttribute("websites", userRepo.findByLogin(principal.getName()).getWebsites());
    	//Here the website url is added to controller to display before the page name input
    	model.addAttribute("websiteUrl", webRepo.findById(id).getUrl());
    	//Gets current users Id who is logged in
    	User user = userRepo.findByLogin(principal.getName());
    	
    	//Checks if website id belongs to list of current users websites
    	if(user.getWebsites().stream().filter(x -> x.getId() == id)
    								  .count() == 0) {
    		return "redirect:/websiteList";
    	}
    	
    	
    	
    	List<Website> websites = new ArrayList<>();
    	for (Website w : webRepo.findAll()) {
    		if(w.getOwner().equals(user)) {
			websites.add(w);
    		}
		}
    	
		if (websites.isEmpty()) {
			return "EmptyWebList";
		} else {
			model.addAttribute("websites", websites);
		}
    	
    	

    	List<Page> pages = new ArrayList<>();
    	String websitename = webRepo.findById(id).getName();
    	model.addAttribute("websitename", websitename);
    	for (Page p : pageRepo.findAll()) {
    		if (p.getOwner().getId() == id){
			pages.add(p);
    		}
    	}
		if (pages.isEmpty()) {
			return "EmptyPageList";
		} else {
			model.addAttribute("pages", pages);	
		}
		return "PageList";
    }
   

    
    
    //Passes current website into model and will display the form to update website
    @RequestMapping(value = "editPage", params = "id")
 public String editWebsite(@ModelAttribute("page") Page  page , @RequestParam("id") int id, @RequestParam(name="websiteid") int websiteid , 
		 Principal principal, BindingResult result, Model model) {
    	
    	User user = userRepo.findByLogin(principal.getName());

    	page = pageRepo.findById(id);
    	model.addAttribute("websiteId", websiteid);
    	model.addAttribute("page", page);

    	return "createPageTrack";
    }
    
    
    
    
    //temp until modal complete
    @RequestMapping(value = "editPageClicked", method = RequestMethod.POST)
    public String editWebsiteClicked(@ModelAttribute("page") Page p, Principal principal, BindingResult result, Model model
    		, @RequestParam(name="id") int id, @RequestParam(name="websiteid") int websiteid) {
    	
    	//Errors need fixing
    	if (result.hasErrors()) {
    		return "createPageTrack";
    	}
    	else {
    	Website website = webRepo.findById(websiteid);
		p.setOwner(website);
    	pageRepo.save(p);

    	return "redirect:/pageList?id="+websiteid;
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
