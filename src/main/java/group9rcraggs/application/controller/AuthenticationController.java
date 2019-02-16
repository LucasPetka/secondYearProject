package group9rcraggs.application.controller;

import java.security.Principal;

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

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import group9rcraggs.application.domain.Role;
import group9rcraggs.application.domain.User;
import group9rcraggs.application.domain.Website;
import group9rcraggs.application.repository.UserRepository;


@Controller
public class AuthenticationController {

	@Autowired
	UserRepository userRepo;
	@Autowired 
	private group9rcraggs.application.repository.RoleRepository roleRepo;
	
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
			userRepo.save(user);
			model.addAttribute("register", true);
			return "log_reg";
		}else {
			model.addAttribute("nomatch", true);
			return "log_reg";
		}
		}
		
		
	}

}