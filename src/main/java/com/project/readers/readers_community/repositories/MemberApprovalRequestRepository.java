package com.project.readers.readers_community.repositories;

import com.project.readers.readers_community.entities.MemberApprovalRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MemberApprovalRequestRepository extends CrudRepository<MemberApprovalRequest, Integer> {
}
