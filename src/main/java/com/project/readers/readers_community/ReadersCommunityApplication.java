package com.project.readers.readers_community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class ReadersCommunityApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReadersCommunityApplication.class, args);
	}

	@RequestMapping
	public String hello() {
		return "Hello World";
	}
}
