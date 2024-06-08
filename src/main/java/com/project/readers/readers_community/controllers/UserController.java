package com.project.readers.readers_community.controllers;

import com.project.readers.readers_community.annotations.CurrentUser;
import com.project.readers.readers_community.entities.User;
import com.project.readers.readers_community.services.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController
{
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

	public UserService getUserService() {
		return userService;
	}
	
	@GetMapping("/showAllDetails")
	public User getUserDetails(@CurrentUser User current_user)
	{
		return userService.getCurrentUserDetail(current_user);
	}



}
