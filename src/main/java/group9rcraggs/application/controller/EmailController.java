package group9rcraggs.application.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
    	User user = userRepo.findByLogin(principal.getName());
    	
    	List<Email> emails = new ArrayList<>();
    	for (Email email : emailRepo.findAll()) {
    		if(email.getOwner().equals(user)) {
			emails.add(email);
    		}
		}
    	
		if (emails.isEmpty()) {
			return "EmptyEmailList";
		} else {
			model.addAttribute("emails", emails);
		}
		return "EmailList";
    }

	
	

}
