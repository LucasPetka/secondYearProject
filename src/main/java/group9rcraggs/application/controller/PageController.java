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

import group9rcraggs.application.Tracking;
import group9rcraggs.application.domain.Email;
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
	
	@Autowired
	UserRepository planRepo;

	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(new PageValidator());
	}
	

	  ///* Adds page to database when add is clicked and calls addPage *///
	    @RequestMapping(value = "addPage", params = "add", method = RequestMethod.POST)
		public String addNewPage(@RequestParam(name="id") int id, @Valid @ModelAttribute("page") Page p, 
				BindingResult result, Model model, Principal principal, RedirectAttributes redirectAttrs) {
	    	model.addAttribute("logfirstName", userRepo.findByLogin(principal.getName()).getFirstName());
	    	Website website = webRepo.findById(id);

	    	 //Removes any excess '/' from end of URL
	    	p.setUrl(removeFrontSlashes(removeSlashes(p.getUrl())));
	    	
	    	User user = userRepo.findByLogin(principal.getName());
	    	
	    	//Checks if number of pages has been equaled or exceeded compared to  their plan
	    	int pages = 0;
	    	for(Website ww : user.getWebsites()) {
	    		pages += ww.getPages().size();
	    	}
	    	//Checks if number of pages is greater than given in plan
	    	if(pages >= user.getPlan().getNumPages()) {
	    		redirectAttrs.addFlashAttribute("exceedPageLimit", true);
	    		return "redirect:/pageList?id="+website.getId();
	    	}
	    	
	    	
	    	
	    	
			
	    	//If website doesn't exist
			if (result.hasErrors()) {
				redirectAttrs.addFlashAttribute("websiteId", id);
				redirectAttrs.addFlashAttribute("websiteUrl", webRepo.findById(id).getUrl());
				redirectAttrs.addFlashAttribute("badlink", true);
				
				if(!website.getPages().isEmpty()) {
					model.addAttribute("pages", website.getPages());
				}
				return "redirect:/pageList?id="+website.getId();			
				
			} else {
				Tracking track = new Tracking();
				p.setOwner(website);
				
				//Sets url to parent URL + page URL 
				p.setUrl(p.getUrlWithParent());
				
				
				//checks if page already exists in users page list
				for(Page pp : website.getPages()) {
					if(pp.getUrl().equals(p.getUrl())) {
						redirectAttrs.addFlashAttribute("duplicatewebsite", true);
						return "redirect:/pageList?id="+website.getId();
		    		}
		    	}
				
				
				p.setFileName(track.linkToFileFormat(p.getUrl())+"_0");
				p.setLinesIgnored("[]");
				p.setLastUpdated("Checking/Not Yet Tracked");
				p.setTracking(true);
				p.setEmail(website.getOwner().getLogin());
				p.setWarning(false);
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
    	
    	//Gets current user who is logged in
    	User user = userRepo.findByLogin(principal.getName());
    	
    	//Adds email list as model attribute for editing website 
    	List<Email> emails= user.getEmails();
    	List<String> emailName = new ArrayList<String>();
    	//Converts email object list into list of email strings
    	for(Email email : emails) {
    		emailName.add(email.getAddress());
    	}
    	//Adds new page to website for testing
    	//Think We need to pass list of pages and select which one from the view using js
    	model.addAttribute("emails", emailName);
    	
    	//Used to set alert after options when adding website - changes depending on plan
    	model.addAttribute("alertAfterList", user.getPlan().getAlertedAfter());
    	
    	Page p2 = new Page();
    	model.addAttribute("page", p2);
    	
    	//Get website from id
    	Website website = webRepo.findById(id);
    	//Here the website url is added to controller to display before the page name input
    	model.addAttribute("websiteUrl", website.getUrl());
    	//
    	model.addAttribute("websiteplan", user.getPlan().getTier());

    	
    	//Checks if website id belongs to list of current users websites
    	if(user.getWebsites().stream().filter(x -> x.getId() == id)
    								  .count() == 0) {
    		return "redirect:/websiteList";
    	}

    	List<Page> pages = new ArrayList<>();
    	model.addAttribute("websitename", website.getName());
    	for (Page p : pageRepo.findAll()) {
    		if (p.getOwner().getId() == id){
			pages.add(p);
    		}
    	}
		if (pages.isEmpty()) {
			return "EmptyPageList";
		} else {
			model.addAttribute("pages", pages);	
	    	//number of pages tracked in this website
	    	model.addAttribute("page_count", pages.size());
		}
		return "PageList";
    }
   
    @RequestMapping(value = "ajaxSendEditPage", method = RequestMethod.GET)
    public @ResponseBody
    String ajaxEditPage(@RequestParam("page_ID") int id, @RequestParam("name") String name, @RequestParam("email") String email) {
    	Page page = pageRepo.findById(id);
    	page.setName(name);
    	page.setEmail(email);
    	pageRepo.save(page);
        return null;
    }
    
    
    
    
    
    //temp until modal complete
    @RequestMapping(value = "editPage", method = RequestMethod.POST)
    public String editWebsiteClicked(@ModelAttribute("page") Page p, Principal principal, BindingResult result, Model model) {
    	
    	//todo add errors

		//p.setOwner(website);
    	pageRepo.save(p);

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
    //Removes any excess '/' from end of URL
    private String removeFrontSlashes(String s) {	
        		if(s.charAt(0)=='/') {
        			s = s.substring(1, s.length());
        		}
        	return s;
        	
        }
	 
	    

}
