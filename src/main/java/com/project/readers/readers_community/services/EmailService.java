package com.project.readers.readers_community.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Autowired
    public EmailService(JavaMailSender mailSender)
    {
        this.mailSender = mailSender;
    }

    public void sendEmail(String to, String subject, String message)
    {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(to);
        mail.setSubject(subject);
        mail.setText(message);

        mailSender.send(mail);
        
    }

    // method to send batch mail with a bcc list
    public void sendBatchMail(String[] bccList, String subject, String message)
    {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setBcc(bccList);
        mail.setSubject(subject);
        mail.setText(message);

        mailSender.send(mail);
    }

}
