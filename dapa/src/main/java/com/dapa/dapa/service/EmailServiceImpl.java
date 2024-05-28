package com.dapa.dapa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@Service
public class EmailServiceImpl implements EmailService{
    @Autowired
    JavaMailSender emailSender;

    @Override
    public void sendSimpleMessage(String to,String subject,String text){
        try {
            SimpleMailMessage messsage = new SimpleMailMessage();
            messsage.setFrom("noreply@sisd.com");
            messsage.setTo(to);
            messsage.setSubject(subject);
            messsage.setText(text);
            emailSender.send(messsage);
        } catch (MailException  me) {
            me.printStackTrace();
        }
    }
}
