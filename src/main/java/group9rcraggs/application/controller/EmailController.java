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

import group9rcraggs.application.domain.Email;
import group9rcraggs.application.domain.User;
import group9rcraggs.application.repository.EmailRepository;
import group9rcraggs.application.repository.UserRepository;

@Controller
@RequestMapping("/")
public class EmailController {
	
	@Autowired
	EmailRepository emailRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@RequestMapping(value = "emailList")
    public String websiteListPage(Model model, Principal principal) {
    	model.addAttribute("logfirstName", userRepo.findByLogin(principal.getName()).getFirstName());
    	model.addAttribute("websites", userRepo.findByLogin(principal.getName()).getWebsites());
    	User user = userRepo.findByLogin(principal.getName());
    	
    	//Gets list of emails for user
    	List<Email> emails = user.getEmails();
    	//Removes first (their sign up email) so they cannot delete it
    	emails.remove(0);
    	
    	
		if (emails.isEmpty()) {
			return "EmptyEmailList";
		} else {
			model.addAttribute("emails", emails);
		}
		return "EmailList";
    }
	
	
	//Error detection needs implementing
	
	///* Adds website to database when add is clicked and calls addWebsite *///
    @RequestMapping(value = "addEmail", params = "add", method = RequestMethod.POST)
	public String addNewWebsite(@Valid @ModelAttribute("email") Email e, BindingResult result, Model model, Principal principal) {
    	model.addAttribute("logfirstName", userRepo.findByLogin(principal.getName()).getFirstName());
    	
		if (result.hasErrors()) {
			model.addAttribute("badlink", true);
	    	User user = userRepo.findByLogin(principal.getName());
	    	
	    	List<Email> emails = new ArrayList<>();
	    	//Creates list of emails for user
	    	for (Email email : emailRepo.findAll()) {
	    		if(email.getOwner().equals(user)) {
				emails.add(email);
	    		}
			}
	    	//if error & list empty
			if (emails.isEmpty()) {
				return "EmptyEmailList";
				
			} else {
				
				model.addAttribute("emails", emails);
			}
			return "EmailList";
			//No errors
		} else {
			
			User user = userRepo.findByLogin(principal.getName());
			
					e.setOwner(user);
					user.addEmail(e);
			emailRepo.save(e);
			
			return "redirect:/emailList";
		}
	}
    
    
    ///* Deletes email from database*///
    @RequestMapping(value = "deleteEmail", params = "id", method = RequestMethod.GET)
	public String deleteWebsite(@RequestParam("id") int id, Principal principal) {
    	Email e = emailRepo.findById(id);
		emailRepo.delete(e);
		
		return "redirect:/emailList";
}
	
	

	
	

}
