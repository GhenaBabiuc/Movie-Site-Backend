package com.example.service.user.util;

import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Resource
    private JavaMailSender mailSender;

    private static final Logger log = LoggerFactory.getLogger(EmailService.class);

    public void sendActivationEmail(String to, String activationLink) {
        try {
            log.info("Sending activation email to: {}", to);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("egregr.eg@yandex.ru");
            message.setTo(to);
            message.setSubject("Account Activation");
            message.setText("Please activate your account by clicking on the link below:\n" + activationLink);
            mailSender.send(message);
            log.info("Activation email successfully sent to: {}", to);
        } catch (Exception e) {
            log.error("Failed to send activation email to: {}", to, e);
            throw e;
        }
    }
}
