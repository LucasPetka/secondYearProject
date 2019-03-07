package group9rcraggs.application;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    
    public void sendEmail(String to, String text, String subject) {
        try {

            MimeMessage message = emailSender.createMimeMessage();

            message.setSubject(subject);
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "utf-8");
            helper = new MimeMessageHelper(message, true);
            message.setContent(text, "text/html");
            helper.setTo(to);
            
            emailSender.send(message);
        } catch (MessagingException ex) {
            
        }
    }
    
 

}
