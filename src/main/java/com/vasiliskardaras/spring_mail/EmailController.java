package com.vasiliskardaras.spring_mail;

import jakarta.mail.MessagingException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.Map;

@RestController
public class EmailController {

    private final EmailSender emailSender;

    public EmailController(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @PostMapping("/sendEmail")
    public String sendEmail(@RequestBody Map<String, String> request) {

        // Call the sendEmail method to send an email
        String recipientEmail = request.get("email");;
        String subject = "Hello from Spring Boot";
        String content = "<p>Hello,</p><p>This is a test email sent from Spring Boot.</p>";

        try {
            emailSender.sendEmail(recipientEmail, subject, content);
            return "Email sent successfully.";
        } catch (MessagingException | UnsupportedEncodingException e) {
            return "Failed to send email. Error: " + e.getMessage();
        }
    }
}
