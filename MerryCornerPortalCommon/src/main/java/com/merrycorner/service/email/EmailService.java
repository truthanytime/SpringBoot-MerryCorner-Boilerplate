package com.merrycorner.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void sendEmail(String recipient, String subject, String resetPasswordLink) throws UnsupportedEncodingException, MessagingException {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("contact@merrycorner.com");
        mailMessage.setTo(recipient);
        mailMessage.setSubject(subject);
        String content="<p>Hello,</p>"
        + "<p>You have requested to reset your password.</p>"
        + "<p>Click the link below to change your password:</p>"
        + "<p><a href=\"" + resetPasswordLink + "\">Change my password</a></p>"
        + "<br>"
        + "<p>Ignore this email if you do remember your password, "
        + "or you have not made the request.</p>";
        mailMessage.setText(content);

        mailSender.send(mailMessage);
    }

}