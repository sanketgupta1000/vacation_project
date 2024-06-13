package com.project.readers.readers_community.repositories;

import java.util.*;
import com.project.readers.readers_community.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User, Integer>
{
    User findByEmail(String email);

	List<User> findByReferrer(User user);
    
    
    
}
