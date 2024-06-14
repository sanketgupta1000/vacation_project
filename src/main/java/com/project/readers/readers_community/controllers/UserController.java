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
    @PostMapping("/profile-complete")
    public String profileComplete(
            @RequestPart("address") Address address,
            @RequestParam("dateOfBirth") @DateTimeFormat(pattern = "dd-MM-yyyy") Date dateOfBirth,
            @CurrentUser User user)
    {
        return userService.profileComplete(address, dateOfBirth, user);
    }

	@GetMapping("/getUserDetails")
	public UserDTO getUserDetails(@CurrentUser User current_user)
	{
		return userService.getCurrentUserDetail(current_user);
	}

	@PutMapping("/updateUserProfile")
	public UserDTO updateUserProfile(@RequestBody UpdatableUserPersonalDetails updatedDetails, @CurrentUser User currentUser)
	{
		return userService.updateUserProfile(updatedDetails, currentUser);
	}


	@DeleteMapping("/deleteUserProfile")
	public String deleteUserProfile(@CurrentUser User currentUser)
	{
		return userService.deleteUserProfile(currentUser);
	}
	
	//endpoint to see all persons who has registered themselves as his reference
	@GetMapping("/getAllreferences")
	public Map<String, List<MemberApprovalRequestDTO>> seeAllReferences(@CurrentUser User user)
	{
		return userService.getallreference(user);
	}
}
