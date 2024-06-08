package com.project.readers.readers_community.services;

import com.project.readers.readers_community.entities.User;
import com.project.readers.readers_community.repositories.UserRepository;

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



}
