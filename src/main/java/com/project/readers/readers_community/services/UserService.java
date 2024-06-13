package com.project.readers.readers_community.services;


import com.project.readers.readers_community.DTOs.Mapper;
import com.project.readers.readers_community.DTOs.UserDTO;
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
public class UserService {
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final Mapper mapper;

    public UserService(UserRepository userRepository, TokenService tokenService, Mapper mapper) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.mapper = mapper;
    }

    // to let user complete his/her profile by providing additional information like address, dob
    // will also update the role to MEMBER from NEW_MEMBER
    // and so, will need to return updated jwt
    @Transactional
    public String profileComplete(Address address, Date dateOfBirth, User user) {
        // setting new info
        user.setAddress(address);
        user.setDateOfBirth(dateOfBirth);
        // update the role
        user.setUserType(UserType.MEMBER);
        // save
        User updatedUser = userRepository.save(user);
        // new jwt
        return tokenService.encode(user.getEmail(), "ROLE_" + updatedUser.getUserType().toString());
    }

    @Transactional
    public UserDTO getCurrentUserDetail(User current_user) {
        return mapper.userToUserDTO(current_user);
    }

    @Transactional
    public UserDTO updateUserProfile(UpdatableUserPersonalDetails updatedDetails, User currentUser) {
        currentUser.setFullName(updatedDetails.getFullName());
        currentUser.setPhoneNumber(updatedDetails.getPhoneNumber());
        currentUser.setAddress(updatedDetails.getAddress());
        currentUser.setDateOfBirth(updatedDetails.getDateOfBirth());
        User user = userRepository.save(currentUser);

        return mapper.userToUserDTO(user);
    }

    @Transactional
    public String deleteUserProfile(User currentUser) {
        userRepository.delete(currentUser);
        return "Your account has been successfully deleted.";
    }
}
