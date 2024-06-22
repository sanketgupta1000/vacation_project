package com.project.readers.readers_community.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.readers.readers_community.DTOs.MemberApprovalRequestDTO;
import com.project.readers.readers_community.DTOs.MemberSearchDTO;
import com.project.readers.readers_community.DTOs.UserDTO;
import com.project.readers.readers_community.annotations.CurrentUser;

import com.project.readers.readers_community.embeddables.Address;
import com.project.readers.readers_community.entities.User;
import com.project.readers.readers_community.services.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.project.readers.readers_community.utilities.UpdatableUserPersonalDetails;

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
            @RequestParam("address") String addressStr,
            @RequestParam("dateOfBirth") @DateTimeFormat(pattern = "dd-MM-yyyy") Date dateOfBirth,
            @CurrentUser User user) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		Address address = objectMapper.readValue(addressStr, Address.class);
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

	// endpoint to search members by name or email containing, useful to get a list while selecting referrer
	@GetMapping("/members/search")
	public List<MemberSearchDTO> searchMembers(@RequestParam("searchStr") String searchStr)
	{
		return userService.searchMembers(searchStr);
	}

}
