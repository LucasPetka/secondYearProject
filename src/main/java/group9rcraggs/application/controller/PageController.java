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

import group9rcraggs.application.domain.Page;
import group9rcraggs.application.domain.Website;
import group9rcraggs.application.repository.PageRepository;
import group9rcraggs.application.repository.WebsiteRepository;


@Controller
@RequestMapping("/")
public class PageController {
	
	@Autowired
	WebsiteRepository websiteRepo;
	
	@Autowired
	PageRepository pageRepo;
	
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.addValidators(new PageValidator());
	}
	

	///* Returns view createPageTrack *///
	 @RequestMapping(value = "addPage", method = RequestMethod.GET)
	    public String createPage(Model model) {
			model.addAttribute("page", new Page());
			return "createPageTrack";

	}
	 
	  ///* Adds page to database when add is clicked and calls addPage *///
	    @RequestMapping(value = "addPage", params = "add", method = RequestMethod.POST)
		public String addNewPage(@Valid @ModelAttribute("page") Page p, BindingResult result, Model model, Principal principal) {
			
			if (result.hasErrors()) {
				return "createPageTrack";
			} else {
				Website website = websiteRepo.findById(2);
						p.setOwner(website);
						website.addPage(p);
				pageRepo.save(p);
				
				return "redirect:/pageList";
			}
		}
	    
		
	    ///* When cancel is clicked calls addPage - nothing added/deleted to database *///
	    @RequestMapping(value = "addPage", params = "cancel", method = RequestMethod.POST)
		public String cancelNewPage() {
			return "redirect:/pageList";
		}
	    
	    
	 
	    ///* Returns list of pages *///
    @RequestMapping(value = "pageList")
    public String testing2(Model model, Principal principal) {
    	
    	//@RequestParam("id") int id, 
    	List<Page> pages = new ArrayList<>();
    	for (Page p : pageRepo.findAll()) {
    		//if (p.getOwner().getId() == id){
			pages.add(p);
    		}
    	
		if (pages.isEmpty()) {
			return "EmptyPageList";
		} else {
			model.addAttribute("pages", pages);
		}
		return "PagesList";
    }
    
    ///* Deletes page from database*///
    @RequestMapping(value = "deletePage", params = "id", method = RequestMethod.GET)
	public String deleteWebsite(@RequestParam("id") int id, Principal principal) {
		Page p = pageRepo.findById(id);	
		Website website = websiteRepo.findById(2);
		website.deletePage(id);
//		if (p != null) {
//			// deleting the website will fail a foreign key constraint
//			User user = userRepo.findByLogin(principal.getName());
//			for (Website w2 : user.getWebsites()) {
//				if(w2.getId() == id) {
//					user.deleteWebsite(id);
//				}
//			}
//			// delete website as orphan
			websiteRepo.save(website);
		
		return "redirect:/pageList";
}

	 
	    

}
