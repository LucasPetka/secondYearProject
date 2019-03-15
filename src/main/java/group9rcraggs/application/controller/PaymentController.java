package group9rcraggs.application.controller;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
import group9rcraggs.application.domain.User;
import group9rcraggs.application.repository.UserRepository;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;



@Controller
@RequestMapping("/")
public class PaymentController {
	
	@Autowired
	UserRepository userRepo;
	
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
		double price = 0.00;
		if(tier == "tier1") {
			price = 9.99;
		}else if(tier == "tier2") {
			price = 19.99;
		}else {
			price = 29.99;
		}
		try {
			Payment payment = paypalService.createPayment(
					price, 
					"GBP", 
					PaypalPaymentMethod.paypal, 
					PaypalPaymentIntent.order,
					tier+ " purchase", 
					cancelUrl, 
					successUrl);
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
				user.setTier("Standard");
		 		   DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		 		   SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
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