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

import group9rcraggs.application.Tracking;
import group9rcraggs.application.domain.Page;
import group9rcraggs.application.domain.Website;
import group9rcraggs.application.repository.PageRepository;
import group9rcraggs.application.repository.WebsiteRepository;


@Controller
@RequestMapping("/")
public class PageController {
	
	@Autowired
	WebsiteRepository webRepo;
	
	@Autowired
	PageRepository pageRepo;
	

	///* Returns view createPageTrack *///
	 @RequestMapping(value = "addPage", method = RequestMethod.GET)
	public String createPage(@RequestParam(name="id") int id, Model model) {
		 model.addAttribute("websiteId", id);
			model.addAttribute("page", new Page());
			return "createPageTrack";

	}
	 
	  ///* Adds page to database when add is clicked and calls addPage *///
	    @RequestMapping(value = "addPage", params = "add", method = RequestMethod.POST)
		public String addNewPage(@RequestParam(name="id") int id, @Valid @ModelAttribute("page") Page p2, 
				BindingResult result, Model model, Principal principal) {
			
			if (result.hasErrors()) {
				return "index";
			} else {
				Tracking track = new Tracking();
				Website website = webRepo.findById(id);
				p2.setOwner(website);
				p2.setFileName(track.linkToFileFormat(p2.getUrl())+"_0");
				p2.setLinesIgnored("[]");
				p2.setLastUpdated("Checking/Not Yet Tracked");
				p2.setTracking(true);
				pageRepo.save(p2);
				
				return "redirect:/pageList?id="+website.getId()+"";
			}
		}
	    
		
	    ///* When cancel is clicked calls addPage - nothing added/deleted to database *///
	    @RequestMapping(value = "addPage", params = "cancel", method = RequestMethod.POST)
		public String cancelNewPage() {
			return "redirect:/pageList";
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
    	model.addAttribute("websiteId", id);
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
    

	 
	    

}
