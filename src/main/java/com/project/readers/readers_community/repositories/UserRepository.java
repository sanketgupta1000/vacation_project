package com.project.readers.readers_community.repositories;

import java.util.*;
import com.project.readers.readers_community.entities.User;
import com.project.readers.readers_community.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>
{
    User findByEmail(String email);
    List<User> findByUserTypeAndFullNameContainingIgnoreCaseOrUserTypeAndEmailContainingIgnoreCase(UserType userType, String name, UserType userType2, String email);
}
