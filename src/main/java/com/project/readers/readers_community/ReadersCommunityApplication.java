package com.project.readers.readers_community;

import com.project.readers.readers_community.configs.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableConfigurationProperties(RsaKeyProperties.class)
public class ReadersCommunityApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReadersCommunityApplication.class, args);
	}

	@RequestMapping
	public String hello() {
		return "Hello World";
	}
}
