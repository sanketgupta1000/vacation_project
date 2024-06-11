package com.project.readers.readers_community.repositories;

import com.project.readers.readers_community.entities.BorrowRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRequestRepository extends JpaRepository<BorrowRequest, Integer>
{
}
