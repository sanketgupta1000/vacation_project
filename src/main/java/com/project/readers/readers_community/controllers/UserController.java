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
import org.springframework.web.multipart.MultipartFile;

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
			@RequestParam("profilePhoto") MultipartFile profilePhoto,
            @RequestParam("address") String addressStr,
            @RequestParam("dateOfBirth") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth,
            @CurrentUser User user) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		Address address = objectMapper.readValue(addressStr, Address.class);
        return userService.completeProfile(address, dateOfBirth, profilePhoto, user);
    }

	@GetMapping("/{userId}")
	public UserDTO getUser(@PathVariable int userId)
	{
		return userService.getUser(userId);
	}

	@PutMapping("/updateProfile")
	public UserDTO updateProfile(
			@RequestParam(name="profilePhoto", required = false) MultipartFile profilePhoto,
			@RequestParam("address") String addressJSON,
			@RequestParam("fullName") String fullName,
			@RequestParam("phoneNumber") String phoneNumber,
			@RequestParam("dateOfBirth") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth,
			@CurrentUser User currentUser) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		Address address = objectMapper.readValue(addressJSON, Address.class);
		return userService.updateProfile(fullName, phoneNumber, address, dateOfBirth, profilePhoto, currentUser);
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
