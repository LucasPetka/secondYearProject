package group9rcraggs.application.controller;

import java.security.Principal;

import java.time.LocalDateTime;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
import group9rcraggs.application.repository.PlanRepository;
import group9rcraggs.application.repository.UserRepository;

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
	
	public static final String PAYPAL_SUCCESS_URL = "pay/success";
	public static final String PAYPAL_CANCEL_URL = "pay/cancel";
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private PaypalService paypalService;
	
	@RequestMapping(method = RequestMethod.GET)
	public String index(){
		return "index";
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
			user.setTier(tier);
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
				if(user.getTier().equals("Basic")) {
					plan = planRepo.findById(1);
				}else if(user.getTier().equals("Pro")) {
					plan = planRepo.findById(2);
				}else {
					plan = planRepo.findById(3);
				}
				user.setPlan(plan);
				
				user.setTier("");

		 		LocalDateTime now = LocalDateTime.now();
				user.setTierValidUntil(now.toString());
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