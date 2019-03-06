package group9rcraggs.application.controller;

import java.security.Principal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import group9rcraggs.application.EmailService;
import group9rcraggs.application.domain.Role;
import group9rcraggs.application.domain.User;
import group9rcraggs.application.domain.VerificationToken;
import group9rcraggs.application.repository.UserRepository;
import group9rcraggs.application.repository.VerificationTokenRepository;


@Controller
public class AuthenticationController {

	@Autowired
	UserRepository userRepo;
	@Autowired
	VerificationTokenRepository verificationRepo;
	@Autowired 
	private group9rcraggs.application.repository.RoleRepository roleRepo;
    @Autowired
    private EmailService emailService;
	
	@RequestMapping(value = "login_register", method = RequestMethod.GET)
	public String log_reg(Principal principal) {
		String name = null;
		try {
		name = principal.getName();
		}catch(Exception e) {}
		if(name != null) {
			return "redirect:/websiteList";
		}
		return "log_reg";
	}
	
	@RequestMapping("password_reset")
	public String forgotPass(Model model, Principal principal) {
		
		return "forgotPass";
	}

	@RequestMapping(value = "/error-login", method = RequestMethod.GET)
	public String invalidLogin(Model model) {
		model.addAttribute("error", true);
		return "log_reg";
	}

	@RequestMapping(value = "/success-login", method = RequestMethod.GET)
	public String successLogin(Principal principal) {
		

		return "redirect:/websiteList";
	}

	@RequestMapping(value = "/user-logout", method = RequestMethod.GET)
	public String logout(Model model) {
		model.addAttribute("logout", true);
		return "log_reg";
	}

	@RequestMapping(value = "/access-denied", method = RequestMethod.GET)
	public String error() {
		return "NoPermission";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerpost(@ModelAttribute("user") User user, Model model) {
		if (userRepo.existsByLogin(user.getLogin())) {
			model.addAttribute("exists", true);
			return "log_reg";
		} else {
		if(user.getPassword().equals(user.getPassword2())) {
			BCryptPasswordEncoder pe = new  BCryptPasswordEncoder();
			user.setPassword(pe.encode(user.getPassword()));
			Role role = roleRepo.findByRole("USER");
			user.setRole(role);
			String token = UUID.randomUUID().toString();
			VerificationToken verify = new VerificationToken(user, token);
			
			userRepo.save(user);
			verificationRepo.save(verify);
			emailService.sendEmail(user.getLogin(), 
					"Hello, you have created account in NetNag, please follow  this https://localhost:8090/regis"
					+ "trationConfirm?token="+token+""
							+ " link to finish your registration", 
					"NetNag Email Verification");
			model.addAttribute("register", true);
			return "log_reg";
		}else {
			model.addAttribute("nomatch", true);
			return "log_reg";
		}
		}
		
		
	}
	@RequestMapping(value = "/registrationConfirm", method = RequestMethod.GET)
	public String confirmRegistration
	  (Model model, @RequestParam("token") String token) {

	    User user = verificationRepo.findByToken(token).getUser();
	    model.addAttribute("successreg", true);
	    user.setEnabled(true);
	    userRepo.save(user); 
	    return "log_reg"; 
	}

}