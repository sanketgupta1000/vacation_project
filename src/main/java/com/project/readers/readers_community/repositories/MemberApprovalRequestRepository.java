package com.project.readers.readers_community.repositories;

import com.project.readers.readers_community.DTOs.MemberApprovalRequestDTO;
import com.project.readers.readers_community.entities.MemberApprovalRequest;
import com.project.readers.readers_community.entities.User;
import com.project.readers.readers_community.enums.Approval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MemberApprovalRequestRepository extends CrudRepository<MemberApprovalRequest, Integer>
{
    List<MemberApprovalRequest> findByAdminApproval(Approval adminApproval);

    List<MemberApprovalRequest> findByReferrerApproval(Approval approval);

    List<MemberApprovalRequest> findByMember_ReferrerAndReferrerApproval(User referrer, Approval referrerApproval);
}
