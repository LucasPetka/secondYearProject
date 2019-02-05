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
	

	
	 @RequestMapping(value = "addPage", method = RequestMethod.GET)
	    public String createPage(Model model) {
			model.addAttribute("page", new Page());
			return "createPageTrack";

	}
	    
	    @RequestMapping(value = "addPage", params = "add", method = RequestMethod.POST)
		public String addNewPage(@Valid @ModelAttribute("page") Page p, BindingResult result, Model model, Principal principal) {
			
			if (result.hasErrors()) {
				return "index";
			} else {
				
				Website website = websiteRepo.findById(2);
						p.setOwner(website);
						website.addPage(p);
				pageRepo.save(p);
				
				return "redirect:/pageList";
			}
		}
	    
		
	    @RequestMapping(value = "pageList")
	    public String testing2(Model model, Principal principal) {
	    	
	    	Website website = websiteRepo.findById(2);
	    	
	    	List<Page> pages = new ArrayList<>();
	    	for (Page p : pageRepo.findAll()) {
				pages.add(p);
			}
	    	
			if (pages.isEmpty()) {
				return "EmptyPageList";
			} else {
				model.addAttribute("pages", pages);
			}
			return "PagesList";
	    }
	    
	    @RequestMapping(value = "addPage", params = "cancel", method = RequestMethod.POST)
		public String cancelNewPage() {
			return "redirect:/pageList";
		}
	    
	    
	    

}
