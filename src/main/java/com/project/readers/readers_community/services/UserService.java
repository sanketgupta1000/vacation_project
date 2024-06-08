package com.project.readers.readers_community.services;


import com.project.readers.readers_community.embeddables.Address;
import com.project.readers.readers_community.entities.User;
import com.project.readers.readers_community.enums.UserType;
import com.project.readers.readers_community.repositories.UserRepository;
import jakarta.transaction.Transactional;

import com.project.readers.readers_community.entities.User;
import com.project.readers.readers_community.repositories.UserRepository;

import com.project.readers.readers_community.utilities.UpdatableUserPersonalDetails;
import jakarta.transaction.Transactional;


import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService
{
    private final UserRepository userRepository;
    private final TokenService tokenService;

    public UserService(UserRepository userRepository, TokenService tokenService) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    // to let user complete his/her profile by providing additional information like address, dob
    // will also update the role to MEMBER from NEW_MEMBER
    // and so, will need to return updated jwt
    @Transactional
    public String profileComplete(Address address, Date dateOfBirth, User user)
    {
        // setting new info
        user.setAddress(address);
        user.setDateOfBirth(dateOfBirth);
        // update the role
        user.setUserType(UserType.MEMBER);
        // save
        User updatedUser = userRepository.save(user);
        // new jwt
        return tokenService.encode(user.getEmail(), "ROLE_"+updatedUser.getUserType().toString());
    }
  
  @Transactional
	public User getCurrentUserDetail(User current_user) {
		
    	
		String cemail=current_user.getEmail();
		
		return userRepository.findByEmail(cemail);

	}

    @Transactional
    public User updateUserProfile(UpdatableUserPersonalDetails updatedDetails, User currentUser)
    {
        currentUser.setFullName(updatedDetails.getFullName());
        currentUser.setPhoneNumber(updatedDetails.getPhoneNumber());
        currentUser.setAddress(updatedDetails.getAddress());
        currentUser.setDateOfBirth(updatedDetails.getDateOfBirth());
        return userRepository.save(currentUser);
    }

    @Transactional
    public String deleteUserProfile(User currentUser)
    {
        userRepository.delete(currentUser);
        return "Your account has been successfully deleted.";
    }
}
