package group9rcraggs.application.controller;

import java.security.Principal;
import java.security.SecureRandom;
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
			
			String emaill = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" "
					+ "\"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\r\n" + 
					"<html xmlns=\"http://www.w3.org/1999/xhtml\">\r\n" + 
					"  \r\n" + 
					"  <head>\r\n" + 
					"    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n" + 
					"    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />\r\n" + 
					"    <title>Revue</title>\r\n" + 
					"    <style type=\"text/css\">\r\n" + 
					"      #outlook a {padding:0;}\r\n" + 
					"      body{width:100% !important; -webkit-text-size-adjust:100%; "
					+ "-ms-text-size-adjust:100%; margin:0; padding:0;-webkit-font-smoothing: antialiased;"
					+ "-moz-osx-font-smoothing: grayscale;} \r\n" + 
					"      .ExternalClass {width:100%;}\r\n" + 
					"      .ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font, "
					+ ".ExternalClass td, .ExternalClass div, .ExternalClass blockquote {line-height: 100%;}\r\n" + 
					"      .ExternalClass p, .ExternalClass blockquote {margin-bottom: 0; margin: 0;}\r\n" + 
					"      #backgroundTable {margin:0; padding:0; width:100% !important; line-height: 100% !important;}\r\n" + 
					"      \r\n" + 
					"      img {outline:none; text-decoration:none; -ms-interpolation-mode: bicubic;} \r\n" + 
					"      a img {border:none;} \r\n" + 
					"      .image_fix {display:block;}\r\n" + 
					"  \r\n" + 
					"      p {margin: 1em 0;}\r\n" + 
					"  \r\n" + 
					"      h1, h2, h3, h4, h5, h6 {color: black !important;}\r\n" + 
					"      h1 a, h2 a, h3 a, h4 a, h5 a, h6 a {color: black;}\r\n" + 
					"      h1 a:active, h2 a:active,  h3 a:active, h4 a:active, h5 a:active, h6 a:active {color: black;}\r\n" + 
					"      h1 a:visited, h2 a:visited,  h3 a:visited, h4 a:visited, h5 a:visited,"
					+ " h6 a:visited {color: black;}\r\n" + 
					"  \r\n" + 
					"      table td {border-collapse: collapse;}\r\n" + 
					"      table { border-collapse:collapse; mso-table-lspace:0pt; mso-table-rspace:0pt; }\r\n" + 
					"  \r\n" + 
					"      a {color: #3498db;}\r\n" + 
					"      p.domain a{color: black;}\r\n" + 
					"  \r\n" + 
					"      hr {border: 0; background-color: #d8d8d8; margin: 0; margin-bottom: 0; height: 1px;}\r\n" + 
					"  \r\n" + 
					"      @media (max-device-width: 667px) {\r\n" + 
					"        a[href^=\"tel\"], a[href^=\"sms\"] {\r\n" + 
					"          text-decoration: none;\r\n" + 
					"          color: blue;\r\n" + 
					"          pointer-events: none;\r\n" + 
					"          cursor: default;\r\n" + 
					"        }\r\n" + 
					"  \r\n" + 
					"        .mobile_link a[href^=\"tel\"], .mobile_link a[href^=\"sms\"] {\r\n" + 
					"          text-decoration: default;\r\n" + 
					"          color: orange !important;\r\n" + 
					"          pointer-events: auto;\r\n" + 
					"          cursor: default;\r\n" + 
					"        }\r\n" + 
					"  \r\n" + 
					"        h1[class=\"profile-name\"], h1[class=\"profile-name\"] a {\r\n" + 
					"          font-size: 32px !important;\r\n" + 
					"          line-height: 38px !important;\r\n" + 
					"          margin-bottom: 14px !important;\r\n" + 
					"        }\r\n" + 
					"  \r\n" + 
					"        span[class=\"issue-date\"], span[class=\"issue-date\"] a {\r\n" + 
					"          font-size: 14px !important;\r\n" + 
					"          line-height: 22px !important;\r\n" + 
					"        }\r\n" + 
					"  \r\n" + 
					"        td[class=\"description-before\"] {\r\n" + 
					"          padding-bottom: 28px !important;\r\n" + 
					"        }\r\n" + 
					"        td[class=\"description\"] {\r\n" + 
					"          padding-bottom: 14px !important;\r\n" + 
					"        }\r\n" + 
					"        td[class=\"description\"] span, span[class=\"item-text\"], span[class=\"item-text\"] span {\r\n" + 
					"          font-size: 16px !important;\r\n" + 
					"          line-height: 24px !important;\r\n" + 
					"        }\r\n" + 
					"  \r\n" + 
					"        span[class=\"item-link-title\"] {\r\n" + 
					"          font-size: 18px !important;\r\n" + 
					"          line-height: 24px !important;\r\n" + 
					"        }\r\n" + 
					"  \r\n" + 
					"        span[class=\"item-header\"] {\r\n" + 
					"          font-size: 22px !important;\r\n" + 
					"        }\r\n" + 
					"  \r\n" + 
					"        span[class=\"item-link-description\"], span[class=\"item-link-description\"] span {\r\n" + 
					"          font-size: 14px !important;\r\n" + 
					"          line-height: 22px !important;\r\n" + 
					"        }\r\n" + 
					"  \r\n" + 
					"        .link-image {\r\n" + 
					"          width: 84px !important;\r\n" + 
					"          height: 84px !important;\r\n" + 
					"        }\r\n" + 
					"  \r\n" + 
					"        .link-image img {\r\n" + 
					"          max-width: 100% !important;\r\n" + 
					"          max-height: 100% !important;\r\n" + 
					"        }\r\n" + 
					"  \r\n" + 
					"      }\r\n" + 
					"  \r\n" + 
					"      @media only screen and (min-device-width: 768px) and (max-device-width: 1024px) {\r\n" + 
					"        a[href^=\"tel\"], a[href^=\"sms\"] {\r\n" + 
					"          text-decoration: none;\r\n" + 
					"          color: blue;\r\n" + 
					"          pointer-events: none;\r\n" + 
					"          cursor: default;\r\n" + 
					"        }\r\n" + 
					"  \r\n" + 
					"        .mobile_link a[href^=\"tel\"], .mobile_link a[href^=\"sms\"] {\r\n" + 
					"          text-decoration: default;\r\n" + 
					"          color: orange !important;\r\n" + 
					"          pointer-events: auto;\r\n" + 
					"          cursor: default;\r\n" + 
					"        }\r\n" + 
					"      }\r\n" + 
					"    </style>\r\n" + 
					"    <!--[if gte mso 9]>\r\n" + 
					"      <style type=\"text/css\">\r\n" + 
					"        #contentTable {\r\n" + 
					"          width: 600px;\r\n" + 
					"        }\r\n" + 
					"      </style>\r\n" + 
					"    <![endif]-->\r\n" + 
					"  </head>\r\n" + 
					"  \r\n" + 
					"  <body style=\"width:100% !important;-webkit-text-size-adjust:100%;"
					+ "-ms-text-size-adjust:100%;margin-top:0;margin-bottom:0;margin-right:0;"
					+ "margin-left:0;padding-top:0;padding-bottom:0;padding-right:0;padding-left:0;\">\r\n" + 
					"    <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" "
					+ "id=\"backgroundTable\" style=\"margin:0; padding:0; width:100% !important; "
					+ "line-height: 100% !important; border-collapse:collapse; mso-table-lspace:0pt; "
					+ "mso-table-rspace:0pt;\"\r\n" + 
					"    width=\"100%\">\r\n" + 
					"      <tr>\r\n" + 
					"        <td width=\"10\" valign=\"top\">&nbsp;</td>\r\n" + 
					"        <td valign=\"top\" align=\"center\">\r\n" + 
					"          <!--[if (gte mso 9)|(IE)]>\r\n" + 
					"            <table width=\"600\" align=\"center\" cellpadding=\"0\" "
					+ "cellspacing=\"0\" border=\"0\" style=\"background-color: #FFF; "
					+ "border-collapse:collapse;mso-table-lspace:0pt;mso-table-rspace:0pt;\">\r\n" + 
					"              <tr>\r\n" + 
					"                <td>\r\n" + 
					"                <![endif]-->\r\n" + 
					"                <table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" "
					+ "align=\"center\" style=\"width: 100%; max-width: 600px; background-color: #FFF;"
					+ " border-collapse:collapse;mso-table-lspace:0pt;mso-table-rspace:0pt;\"\r\n" + 
					"                id=\"contentTable\">\r\n" + 
					"                  <tr>\r\n" + 
					"                    <td width=\"600\" valign=\"top\" align=\"center\" "
					+ "style=\"border-collapse:collapse;\">\r\n" + 
					"                      <table align='center' border='0' cellpadding='0' "
					+ "cellspacing='0' style='border: 1px solid #E0E4E8;'\r\n" + 
					"                      width='100%'>\r\n" + 
					"                        <tr>\r\n" + 
					"                          <td align='left' style='padding: 56px 56px 28px 56px;' "
					+ "valign='top'>\r\n" + 
					"                            <div style='font-family: \"lato\", \"Helvetica Neue\","
					+ " Helvetica, Arial, sans-serif; line-height: 28px;font-size: 18px; color: #333;"
					+ "font-weight:bold;'>Hey there!</div>\r\n" + 
					"                          </td>\r\n" + 
					"                        </tr>\r\n" + 
					"                        <tr>\r\n" + 
					"                          <td align='left' style='padding: 0 56px 28px 56px;' valign='top'>\r\n" + 
					"                            <div style='font-family: \"lato\", \"Helvetica Neue\", "
					+ "Helvetica, Arial, sans-serif; line-height: 28px;font-size: 18px; color: #333;'>"
					+ "Please click the following link to confirm that <strong>"+ user.getLogin() +"</strong> is\r\n" + 
					"                              your email address where you will receive replies to your issues:</div>\r\n" + 
					"                          </td>\r\n" + 
					"                        </tr>\r\n" + 
					"                        <tr>\r\n" + 
					"                          <td align='left' style='padding: 0 56px;' valign='top'>\r\n" + 
					"                            <div>\r\n" + 
					"                              <!--[if mso]>\r\n" + 
					"                                <v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" "
					+ "xmlns:w=\"urn:schemas-microsoft-com:office:word\"\r\n" + 
					"                                href=\"#\"\r\n" + 
					"                                style=\"height:44px;v-text-anchor:middle;width:250px;\" "
					+ "arcsize=\"114%\" stroke=\"f\"\r\n" + 
					"                                fillcolor=\"#E15718\">\r\n" + 
					"                                  <w:anchorlock/>\r\n" + 
					"                                <![endif]-->\r\n" + 
					"                                <a style=\"background-color:#E15718;border-radius:50px;"
					+ "color:#ffffff;display:inline-block;font-family: &#39;lato&#39;, &#39;Helvetica Neue&#39;, "
					+ "Helvetica, Arial, sans-serif;font-size:18px;line-height:44px;text-align:center;text-decoration:none;"
					+ "width:250px;-webkit-text-size-adjust:none;\"\r\n" + 
					"                                href='https://localhost:8090/registrationConfirm?token="+token+"'>"
							+ "Confirm reply address</a>\r\n" + 
					"                                <!--[if mso]>\r\n" + 
					"                                </v:roundrect>\r\n" + 
					"                              <![endif]-->\r\n" + 
					"                            </div>\r\n" + 
					"                          </td>\r\n" + 
					"                          <tr>\r\n" + 
					"                            <td align='left' style='padding: 28px 56px 28px 56px;' valign='top'></td>\r\n" + 
					"                          </tr>\r\n" + 
					"                        </tr>\r\n" + 
					"                      </table>\r\n" + 
					"                      <table align='center' border='0' cellpadding='0' cellspacing='0' width='100%'>\r\n" + 
					"                        <tr>\r\n" + 
					"                          <td align='center' style='padding: 30px 56px 28px 56px;' valign='middle'>\r\n" + 
					"<span style='font-family: \"lato\", \"Helvetica Neue\", Helvetica, Arial, sans-serif;"
					+ " line-height: 28px;font-size: 16px; color: #A7ADB5; vertical-align: middle;'>If this"
					+ " email doesn't make any sense, please <a href=\"mailto:support@getrevue.co\">let us know"
					+ "</a>!</span>\r\n" + 
					"\r\n" + 
					"                          </td>\r\n" + 
					"                        </tr>\r\n" + 
					"                        <tr>\r\n" + 
					"                          <td align='center' style='padding: 0 56px 28px 56px;' valign='middle'>\r\n" + 
					"                            <a style=\"border: 0;\" href=\"https://localhost:8090/\">\r\n" + 
					"                              NetNag" +
					"                            </a>\r\n" + 
					"                          </td>\r\n" + 
					"                        </tr>\r\n" + 
					"                      </table>\r\n" + 
					"                    </td>\r\n" + 
					"                  </tr>\r\n" + 
					"                </table>\r\n" + 
					"                <!--[if (gte mso 9)|(IE)]>\r\n" + 
					"                </td>\r\n" + 
					"              </tr>\r\n" + 
					"            </table>\r\n" + 
					"          <![endif]-->\r\n" + 
					"        </td>\r\n" + 
					"        <td width=\"10\" valign=\"top\">&nbsp;</td>\r\n" + 
					"      </tr>\r\n" + 
					"    </table>\r\n" + 
					"    \r\n" + 
					"  </body>\r\n" + 
					"\r\n" + 
					"</html>";
			
			
			userRepo.save(user);
			verificationRepo.save(verify);
			emailService.sendEmail(user.getLogin(), emaill, "NetNag Email Verification");
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
	
	//Password reset controllers
	@RequestMapping(value = "password_reset", method = RequestMethod.GET)
	public String forgotPass(Model model, Principal principal) {
		
		return "forgotPass";
	}
	
	@RequestMapping(value = "password_reset", method = RequestMethod.POST)
	public String sendEmailForPass(Model model, @RequestParam(name = "email") String email) {
		if(userRepo.existsByLogin(email)) {
			BCryptPasswordEncoder pe = new  BCryptPasswordEncoder();
		    final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_";
		    final SecureRandom RANDOM = new SecureRandom();
	        StringBuilder sb = new StringBuilder();
	        for (int i = 0; i < 8; ++i) {
	            sb.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
	        }
	        String newPass = sb.toString();
		String emailMsg = "Your new password: "+newPass+" we advise changing it after you log in,"
				+ " you can do it in your profile";
		User user = userRepo.findByLogin(email);
		user.setPassword(pe.encode(newPass));
		userRepo.save(user);
		emailService.sendEmail(email, emailMsg, "NetNag password reset");
		model.addAttribute("resetEmailSent", true);
		}else {
		model.addAttribute("noEmailMatch", true);
		}
		return "forgotPass";
	}

}