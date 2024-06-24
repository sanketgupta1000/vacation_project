package com.project.readers.readers_community.services;


import com.project.readers.readers_community.DTOs.Mapper;
import com.project.readers.readers_community.DTOs.MemberApprovalRequestDTO;
import com.project.readers.readers_community.DTOs.MemberSearchDTO;
import com.project.readers.readers_community.DTOs.UserDTO;
import com.project.readers.readers_community.embeddables.Address;
import com.project.readers.readers_community.entities.User;
import com.project.readers.readers_community.enums.Approval;
import com.project.readers.readers_community.enums.UserType;
import com.project.readers.readers_community.repositories.MemberApprovalRequestRepository;
import com.project.readers.readers_community.repositories.UserRepository;
import jakarta.transaction.Transactional;

import com.project.readers.readers_community.utilities.UpdatableUserPersonalDetails;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final Mapper mapper;
    private final MemberApprovalRequestRepository memberApprovalRequestRepository;

    public UserService(UserRepository userRepository, TokenService tokenService, Mapper mapper, MemberApprovalRequestRepository memberApprovalRequestRepository) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.mapper = mapper;
        this.memberApprovalRequestRepository = memberApprovalRequestRepository;
    }

    // to let user complete his/her profile by providing additional information like address, dob
    // will also update the role to MEMBER from NEW_MEMBER
    // and so, will need to return updated jwt
    @Transactional
    public String completeProfile(Address address, Date dateOfBirth, User user) {
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
    public UserDTO getUser(int userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isPresent())
        {
            return mapper.userToUserDTO(userOptional.get());
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
    }

    @Transactional
    public UserDTO updateProfile(String fullName, String phoneNumber, Address address, Date dateOfBirth, User currentUser) {
        currentUser.setFullName(fullName);
        currentUser.setPhoneNumber(phoneNumber);
        currentUser.setAddress(address);
        currentUser.setDateOfBirth(dateOfBirth);
        User user = userRepository.save(currentUser);

        return mapper.userToUserDTO(user);
    }

//    @Transactional
//    public String deleteUserProfile(User currentUser) {
//        userRepository.delete(currentUser);
//        return "Your account has been successfully deleted.";
//    }

	public Map<String, List<MemberApprovalRequestDTO>> getAllReferrals(User user) {
		
	    // create a new hashmap
        Map<String, List<MemberApprovalRequestDTO>> map = new HashMap<>();

        map.put("unresponded",
                memberApprovalRequestRepository.findByMember_ReferrerAndReferrerApproval(user, Approval.UNRESPONDED)
                        .stream()
                        .map(mapper::memberApprovalRequestToMemberApprovalRequestDTO)
                        .toList()
                );

        map.put("approved",
                memberApprovalRequestRepository.findByMember_ReferrerAndReferrerApproval(user, Approval.APPROVED)
                        .stream()
                        .map(mapper::memberApprovalRequestToMemberApprovalRequestDTO)
                        .toList()
        );

        map.put("rejected",
                memberApprovalRequestRepository.findByMember_ReferrerAndReferrerApproval(user, Approval.REJECTED)
                        .stream()
                        .map(mapper::memberApprovalRequestToMemberApprovalRequestDTO)
                        .toList()
        );

        return map;
		
	}

    // method to search members by name or email containing, useful to get a list while selecting referrer
    public List<MemberSearchDTO> searchMembers(String searchStr)
    {
        return userRepository.findByUserTypeAndFullNameContainingIgnoreCaseOrUserTypeAndEmailContainingIgnoreCase
                (
                        UserType.MEMBER, searchStr,
                        UserType.MEMBER, searchStr
                )
                .stream()
                .map(mapper::userToMemberSearchDTO)
                .toList();
    }
}
