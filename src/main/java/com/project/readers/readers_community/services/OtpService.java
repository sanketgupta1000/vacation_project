package com.project.readers.readers_community.services;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OtpService
{
    public String generateOtp(int length)
    {
        String numbers = "0123456789";
        Random random = new Random();
        StringBuilder otp = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            otp.append(numbers.charAt(random.nextInt(numbers.length())));
        }
        return otp.toString();
    }
}
