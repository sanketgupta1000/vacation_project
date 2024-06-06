package com.project.readers.readers_community.controllers;

import com.project.readers.readers_community.services.EmailService;
import com.project.readers.readers_community.services.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {


    private final EmailService emailService;
    private final OtpService otpService;

    @Autowired
    public DemoController(EmailService emailService, OtpService otpService)
    {
        this.emailService = emailService;
        this.otpService = otpService;
    }

    @GetMapping("/")
    public String sendotp()
    {
        String otp = otpService.generateOtp(4);
        emailService.sendEmail("zalaharshpalsinh511@gmail.com", "OTP", otp);

        return "success";
    }

}
