package com.pccwexam.user.infrastructure.email;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class EmailService {

    private static final String FROM = "welcome@pccwexam.com";
    private static final String REGISTER_SUBJECT = "Congratulations for register pccwexam";
    private static final String REGISTER_CONTENT = "Welcome to pccwexam";

    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void sendRegisterEmail(String to) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(FROM);
        simpleMailMessage.setSubject(REGISTER_SUBJECT);
        simpleMailMessage.setText(REGISTER_CONTENT);
        simpleMailMessage.setTo(to);
        try {
            mailSender.send(simpleMailMessage);
        } catch (Exception e) {
            log.error("Register email send {} fail", to, e);
        }
    }

}
