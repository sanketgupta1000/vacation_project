package com.project.readers.readers_community.services;

import com.project.readers.readers_community.configs.SecurityUser;
import com.project.readers.readers_community.entities.User;
import com.project.readers.readers_community.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// service for letting spring access my custom user
@Service
public class CustomUserDetailsService implements UserDetailsService
{

    private final UserRepository userRepository;

    // DI
    public CustomUserDetailsService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    // method implementation, in our case, email can be considered username
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        User user = userRepository.findByEmail(username);

        // checking if not found
        if(user == null)
        {
            throw new UsernameNotFoundException("User "+ username + " not found");
        }

        return new SecurityUser(user);
    }
}
