package com.project.readers.readers_community.controllers;

import com.project.readers.readers_community.annotations.CurrentUser;
import com.project.readers.readers_community.embeddables.Address;
import com.project.readers.readers_community.entities.User;
import com.project.readers.readers_community.services.UserService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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

}
