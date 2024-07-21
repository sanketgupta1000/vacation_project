package com.project.readers.readers_community.repositories;
import java.util.*;

import com.project.readers.readers_community.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.readers.readers_community.entities.Book;
import com.project.readers.readers_community.enums.Approval;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface BookRepository extends JpaRepository<Book,Long>, JpaSpecificationExecutor<Book>
{
	Page<Book> findByAdminApproval(Approval status, Pageable pageable);
	Page<Book> findByOwnerAndAdminApproval(User owner, Approval adminApproval, Pageable pageable);
}
