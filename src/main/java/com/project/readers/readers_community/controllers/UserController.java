package com.project.readers.readers_community.controllers;

import com.project.readers.readers_community.annotations.CurrentUser;
import com.project.readers.readers_community.entities.User;
import com.project.readers.readers_community.services.UserService;

import com.project.readers.readers_community.utilities.UpdatableUserPersonalDetails;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.IconUIResource;

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
	
	@GetMapping("/getUserDetails")
	public User getUserDetails(@CurrentUser User current_user)
	{
		return userService.getCurrentUserDetail(current_user);
	}

	@PutMapping("/updateUserProfile")
	public User updateUserProfile(UpdatableUserPersonalDetails updatedDetails, @CurrentUser User currentUser)
	{
		return userService.updateUserProfile(updatedDetails, currentUser);
	}

	@DeleteMapping("/deleteUserProfile")
	public String deleteUserProfile(@CurrentUser User currentUser)
	{
		return userService.deleteUserProfile(currentUser);
	}
}
