package com.project.readers.readers_community.controllers;

import com.project.readers.readers_community.DTOs.MemberApprovalRequestDTO;
import com.project.readers.readers_community.DTOs.UserDTO;
import com.project.readers.readers_community.annotations.CurrentUser;

import com.project.readers.readers_community.embeddables.Address;
import com.project.readers.readers_community.entities.User;
import com.project.readers.readers_community.services.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

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


    // endpoint to let user complete his/her profile by providing additional information like address, dob
    @PostMapping("/completeProfile")
    public String completeProfile(
            @RequestPart("address") Address address,
            @RequestParam("dateOfBirth") @DateTimeFormat(pattern = "dd-MM-yyyy") Date dateOfBirth,
            @CurrentUser User user)
    {
        return userService.completeProfile(address, dateOfBirth, user);
    }

	@GetMapping
	public UserDTO getUser(@CurrentUser User currentUser)
	{
		return userService.getUser(currentUser);
	}

	@PutMapping("/updateProfile")
	public UserDTO updateProfile(@RequestBody UpdatableUserPersonalDetails updatedDetails, @CurrentUser User currentUser)
	{
		return userService.updateProfile(updatedDetails, currentUser);
	}


	// TODO: this needs to be updated
//	@DeleteMapping("/deleteUserProfile")
//	public String deleteUserProfile(@CurrentUser User currentUser)
//	{
//		return userService.deleteUserProfile(currentUser);
//	}
	
	//endpoint to see all persons who have mentioned the current user as referrer
	@GetMapping("/referrals")
	public Map<String, List<MemberApprovalRequestDTO>> getAllReferrals(@CurrentUser User user)
	{
		return userService.getAllReferrals(user);
	}
}
