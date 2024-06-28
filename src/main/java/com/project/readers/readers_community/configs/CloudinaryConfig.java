package com.project.readers.readers_community.configs;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig
{
    @Value("${CLOUDINARY_URL}")
    private String CLOUDINARY_URL;

    @Bean
    public Cloudinary cloudinary()
    {
        return new Cloudinary(CLOUDINARY_URL);
    }
}
