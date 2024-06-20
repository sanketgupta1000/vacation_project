package com.project.readers.readers_community.services;


import com.project.readers.readers_community.DTOs.Mapper;
import com.project.readers.readers_community.DTOs.MemberApprovalRequestDTO;
import com.project.readers.readers_community.DTOs.UserDTO;
import com.project.readers.readers_community.embeddables.Address;
import com.project.readers.readers_community.entities.User;
import com.project.readers.readers_community.enums.Approval;
import com.project.readers.readers_community.enums.UserType;
import com.project.readers.readers_community.repositories.MemberApprovalRequestRepository;
import com.project.readers.readers_community.repositories.UserRepository;
import jakarta.transaction.Transactional;

import com.project.readers.readers_community.utilities.UpdatableUserPersonalDetails;


import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public UserDTO getUser(User currentUser) {
        return mapper.userToUserDTO(currentUser);
    }

    @Transactional
    public UserDTO updateProfile(UpdatableUserPersonalDetails updatedDetails, User currentUser) {
        currentUser.setFullName(updatedDetails.getFullName());
        currentUser.setPhoneNumber(updatedDetails.getPhoneNumber());
        currentUser.setAddress(updatedDetails.getAddress());
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
}
