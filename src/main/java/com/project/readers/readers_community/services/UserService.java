package com.project.readers.readers_community.services;

import com.project.readers.readers_community.entities.User;
import com.project.readers.readers_community.repositories.UserRepository;

import com.project.readers.readers_community.utilities.UpdatableUserPersonalDetails;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

@Service
public class UserService
{
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
//        currentUser.setAddress(updatedDetails.getAddress());
//        currentUser.setDateOfBirth(updatedDetails.getDateOfBirth());
        return userRepository.save(currentUser);
    }

    @Transactional
    public String deleteUserProfile(User currentUser)
    {
        userRepository.delete(currentUser);
        return "Your account has been successfully deleted.";
    }
}
