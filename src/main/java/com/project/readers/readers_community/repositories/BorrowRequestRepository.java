package com.project.readers.readers_community.repositories;

import com.project.readers.readers_community.entities.BorrowRequest;
import com.project.readers.readers_community.entities.User;
import com.project.readers.readers_community.enums.BorrowRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface BorrowRequestRepository extends JpaRepository<BorrowRequest, Integer>
{
    List<BorrowRequest> findByBookCopy_Book_OwnerAndStatusIn(User owner, List<BorrowRequestStatus> borrowRequestStatuses);

    List<BorrowRequest> findByRequesterAndStatusIn(User user, List<BorrowRequestStatus> unresponded);
}
