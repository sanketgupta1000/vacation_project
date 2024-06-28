package com.project.readers.readers_community.controllers;

import com.project.readers.readers_community.services.EmailService;
import com.project.readers.readers_community.services.FileService;
import com.project.readers.readers_community.services.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/demo")
public class DemoController {


    private final EmailService emailService;
    private final OtpService otpService;
    private final FileService fileService;


    @Autowired
    public DemoController(EmailService emailService, OtpService otpService, FileService fileService)
    {
        this.emailService = emailService;
        this.otpService = otpService;
        this.fileService = fileService;
    }

    @GetMapping("/")
    public String sendotp()
    {
        String otp = otpService.generateOtp(4);
        emailService.sendEmail("zalaharshpalsinh511@gmail.com", "OTP", otp);

        return "success";
    }

    @PostMapping("/fileUpload")
    public String fileUpload(@RequestParam(name = "file", required = false) MultipartFile file) throws IOException {
        String url = fileService.uploadFile(file,"tmp", null);
        String[] parts = url.split("/");
        parts = parts[parts.length-1].split("\\.");
        return url + "\n" + parts[0];
    }

}
