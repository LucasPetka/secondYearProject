package group9rcraggs.application.controller;

import java.security.Principal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import group9rcraggs.application.PaypalPaymentIntent;
import group9rcraggs.application.PaypalPaymentMethod;
import group9rcraggs.application.PaypalService;
import group9rcraggs.application.URLUtils;
import group9rcraggs.application.domain.Plan;
import group9rcraggs.application.domain.User;
import group9rcraggs.application.domain.Website;
import group9rcraggs.application.repository.PlanRepository;
import group9rcraggs.application.repository.UserRepository;
import group9rcraggs.application.repository.WebsiteRepository;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;



@Controller
@RequestMapping("/")
public class PaymentController {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	PlanRepository planRepo;
	
	@Autowired
	WebsiteRepository websiteRepo;
	
	public static final String PAYPAL_SUCCESS_URL = "pay/success";
	public static final String PAYPAL_CANCEL_URL = "pay/cancel";
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PaypalService paypalService;
	
	@RequestMapping(value = "/payment", method = RequestMethod.GET)
    public String test(Principal principal, Model model) {
    	User user = userRepo.findByLogin(principal.getName());
    	List<Website> websites = new ArrayList<>();
    	
    	
    	for (Website w : websiteRepo.findAll()) {
    		if(w.getOwner().equals(user)) {
			websites.add(w);
    		}
		}
    	model.addAttribute("logfirstName", user.getFirstName());
    	model.addAttribute("websites", websites);
    	model.addAttribute("membershiptype", user.getPlan().getTier());
    	if(user.getPlan().getTier().equals("Standard") || user.getPlan().getTier().equals("Pro") || user.getPlan().getTier().equals("Enterprise")) {
    	model.addAttribute("paidMembership", true);
    	model.addAttribute("membershipValidUntil", user.getPlanValidUntil());
    	}
    	return "Membership";
    }
	
	@RequestMapping(method = RequestMethod.POST, value = "pay")
	public String pay(HttpServletRequest request, Principal principal, @RequestParam("tier") String tier){
		String cancelUrl = URLUtils.getBaseURl(request) + "/" + PAYPAL_CANCEL_URL;
		String successUrl = URLUtils.getBaseURl(request) + "/" + PAYPAL_SUCCESS_URL;

		Plan plan = new Plan(tier);
		
		try {
			Payment payment = paypalService.createPayment(
					plan.getPrice(), 
					"GBP", 
					PaypalPaymentMethod.paypal, 
					PaypalPaymentIntent.order,
					tier+ " purchase", 
					cancelUrl, 
					successUrl);
			
			User user = userRepo.findByLogin(principal.getName());
			user.setTempTier(tier);
			userRepo.save(user);
			for(Links links : payment.getLinks()){
				if(links.getRel().equals("approval_url")){
					return "redirect:" + links.getHref();
				}
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		return "redirect:/";
	}

	@RequestMapping(method = RequestMethod.GET, value = PAYPAL_CANCEL_URL)
	public String cancelPay(RedirectAttributes redirectAttrs){
		redirectAttrs.addFlashAttribute("failedPayment", true);
		return "redirect:/payment";
	}

	@RequestMapping(method = RequestMethod.GET, value = PAYPAL_SUCCESS_URL)
	public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId, 
			RedirectAttributes redirectAttrs, Principal principal){
		try {
			Payment payment = paypalService.executePayment(paymentId, payerId);
			if(payment.getState().equals("approved")){
				User user = userRepo.findByLogin(principal.getName());
				
				Plan plan;
				if(user.getTempTier().equals("Standard")) {
					plan = planRepo.findById(1);
				}else if(user.getTempTier().equals("Pro")) {
					plan = planRepo.findById(2);
				}else {
					plan = planRepo.findById(3);
				}
				user.setPlan(plan);
				
				user.setTempTier("");

		 		LocalDateTime nowPlusMonth = LocalDateTime.now().plusMinutes(3);
		 		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
				user.setPlanValidUntil(dtf.format(nowPlusMonth));
				userRepo.save(user);
				redirectAttrs.addFlashAttribute("sucessPayment", true);
				return "redirect:/payment";
			}
		} catch (PayPalRESTException e) {
			log.error(e.getMessage());
		}
		return "redirect:/";
	}
	
}