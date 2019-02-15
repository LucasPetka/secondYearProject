package group9rcraggs.application.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import group9rcraggs.application.repository.UserRepository;



@Controller
public class AuthenticationController {

	@Autowired
	UserRepository userRepo;

	@RequestMapping(value = "login_register", method = RequestMethod.GET)
	public String log_reg() {
		return "log_reg";
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
		return "security/error-message";
	}
}